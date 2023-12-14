package everyos.nerimity.nerimity4j.gateway.imp;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.mccue.json.Json;
import everyos.nerimity.nerimity4j.gateway.GatewayEventDispatcher;
import everyos.nerimity.nerimity4j.gateway.event.GatewayConnectErrorEvent;
import everyos.nerimity.nerimity4j.gateway.event.GatewayConnectEvent;
import everyos.nerimity.nerimity4j.gateway.event.GatewayDisconnectEvent;
import everyos.nerimity.nerimity4j.gateway.event.GatewayEvent;
import everyos.nerimity.nerimity4j.gateway.imp.converter.GatewayAuthenticateEventConverter;
import io.socket.client.Socket;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.EmitFailureHandler;
import reactor.core.publisher.Sinks.Many;
import reactor.core.scheduler.Schedulers;

public class GatewayEventDispatcherImp implements GatewayEventDispatcher {

	private final Logger logger = LoggerFactory.getLogger(GatewayEventDispatcherImp.class);
	private final Many<GatewayEvent> sink = Sinks.many().multicast().<GatewayEvent>onBackpressureBuffer();
	private final Map<String, GatewayEventConverter> converters;
	private final EmitFailureHandler failureHandler = (_1, _2) -> {
		logger.debug("Failed to push an event!");
		return false;
	};

	public GatewayEventDispatcherImp(Socket socket) {
		this.converters = createConverters();
		registerListeners(socket);
	}

	@Override
	public <T extends GatewayEvent> Flux<T> on(Class<T> eventCls) {
		Flux<T> flux = onInternal(eventCls);
		return flux
			.publishOn(Schedulers.boundedElastic());
	}

	private <T extends GatewayEvent> Flux<T> onInternal(Class<T> eventCls) {
		return sink.asFlux()
			.filter(it -> eventCls.isInstance(it))
			.cast(eventCls);
	}

	private Map<String, GatewayEventConverter> createConverters() {
		Map<String, GatewayEventConverter> converters = new HashMap<>();
		converters.put(Socket.EVENT_CONNECT, json -> new GatewayConnectEvent());
		converters.put(Socket.EVENT_DISCONNECT, json -> new GatewayDisconnectEvent());
		converters.put(Socket.EVENT_CONNECT_ERROR, json -> new GatewayConnectErrorEvent());
		converters.put("user:authenticated", new GatewayAuthenticateEventConverter());

		return converters;
	}

	private void registerListeners(Socket socket) {
		for (Map.Entry<String, GatewayEventConverter> entry : converters.entrySet()) {
			socket.on(entry.getKey(), args -> {
				GatewayEvent event = entry.getValue().accept(
					args.length == 0 ? null : Json.readString(args[0].toString()));
				sink.emitNext(event, failureHandler);
			});
		}
	}

}
