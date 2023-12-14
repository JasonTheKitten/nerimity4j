package everyos.nerimity.nerimity4j.rest;

import everyos.nerimity.nerimity4j.rest.imp.RestClientImp;
import everyos.nerimity.nerimity4j.rest.service.Service;

public interface RestClient {
	
	<T extends Service> T getService(Class<T> serviceClass);

	public static RestClient create(RestClientConfigs configs) {
		return new RestClientImp(configs);
	}

}
