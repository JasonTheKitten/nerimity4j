package everyos.nerimity.nerimity4j.rest;

import java.util.Optional;

public record RestClientConfigs(String endpoint, Optional<String> token) {
	
	public static String DEFAULT_ENDPOINT = "https://nerimity.com/api/";

}
