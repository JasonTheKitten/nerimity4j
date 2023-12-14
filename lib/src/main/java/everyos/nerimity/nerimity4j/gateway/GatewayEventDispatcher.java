package everyos.nerimity.nerimity4j.gateway;

import everyos.nerimity.nerimity4j.gateway.event.GatewayEvent;
import reactor.core.publisher.Flux;

public interface GatewayEventDispatcher {

	<T extends GatewayEvent> Flux<T> on(Class<T> eventCls);

}
