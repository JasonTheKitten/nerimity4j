package everyos.nerimity.nerimity4j.gateway.imp;

import java.net.URISyntaxException;

import dev.mccue.json.Json;
import everyos.nerimity.nerimity4j.gateway.GatewayClient;
import everyos.nerimity.nerimity4j.gateway.GatewayClientConfigs;
import everyos.nerimity.nerimity4j.gateway.GatewayEventDispatcher;
import everyos.nerimity.nerimity4j.gateway.event.GatewayConnectEvent;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.engineio.client.transports.WebSocket;

public class GatewayClientImp implements GatewayClient {

	private final Socket socket;
	private final GatewayEventDispatcherImp eventDispatcher;

	public GatewayClientImp(GatewayClientConfigs configs) throws URISyntaxException {
		this.socket = createSocket(configs.endpoint());
		this.eventDispatcher = new GatewayEventDispatcherImp(socket);
		addAuthenticationListener(configs.token());
	}

	@Override
	public void login() {
		socket.connect();
	}

	@Override
	public void logout() {
		socket.disconnect();
	}

	@Override
	public GatewayEventDispatcher getEventDispatcher() {
		return eventDispatcher;
	}

	private Socket createSocket(String gatewayURL) throws URISyntaxException {
		IO.Options options = IO.Options.builder()
			.setTransports(new String[] { WebSocket.NAME })
			.build();

		return IO.socket(gatewayURL, options);
	}

	private void addAuthenticationListener(String token) {
		Json json = Json.objectBuilder()
			.put("token", token)
			.build();
		eventDispatcher.on(GatewayConnectEvent.class)
			.subscribe(event -> {
				socket.emit("user:authenticate", json);
			});
	}
	
}
