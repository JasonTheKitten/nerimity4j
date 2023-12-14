package everyos.nerimity.nerimity4j.gateway.imp;

import dev.mccue.json.Json;
import everyos.nerimity.nerimity4j.gateway.event.GatewayEvent;

public interface GatewayEventConverter {
	
	GatewayEvent accept(Json json);

}
