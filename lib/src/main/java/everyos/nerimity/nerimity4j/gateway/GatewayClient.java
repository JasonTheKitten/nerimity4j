package everyos.nerimity.nerimity4j.gateway;

import java.net.URISyntaxException;

import everyos.nerimity.nerimity4j.gateway.imp.GatewayClientImp;

public interface GatewayClient {
	
	void login();

	void logout();

	GatewayEventDispatcher getEventDispatcher();

	static GatewayClient create(GatewayClientConfigs gatewayClientConfigs) throws URISyntaxException {
		return new GatewayClientImp(gatewayClientConfigs);
	}

}
