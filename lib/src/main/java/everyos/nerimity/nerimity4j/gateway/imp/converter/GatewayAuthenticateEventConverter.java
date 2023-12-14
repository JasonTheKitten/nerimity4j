package everyos.nerimity.nerimity4j.gateway.imp.converter;

import dev.mccue.json.Json;
import everyos.nerimity.nerimity4j.gateway.event.GatewayAuthenticateEvent;
import everyos.nerimity.nerimity4j.gateway.event.GatewayEvent;
import everyos.nerimity.nerimity4j.gateway.imp.GatewayEventConverter;

public class GatewayAuthenticateEventConverter implements GatewayEventConverter {

	@Override
	public GatewayEvent accept(Json json) {
		System.out.println(json);
		return new GatewayAuthenticateEvent();
	}
	
}
