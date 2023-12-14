package everyos.nerimity.nerimity4j.gateway;

public record GatewayClientConfigs(String endpoint, String token) {

	public static final String DEFAULT_ENDPOINT = "https://nerimity.com/";
	
}
