package everyos.nerimity.nerimity4j.rest.imp;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.jakewharton.retrofit2.adapter.reactor.ReactorCallAdapterFactory;

import everyos.nerimity.nerimity4j.json.entity.LoginRequestData;
import everyos.nerimity.nerimity4j.json.entity.LoginResponseData;
import everyos.nerimity.nerimity4j.rest.RestClient;
import everyos.nerimity.nerimity4j.rest.RestClientConfigs;
import everyos.nerimity.nerimity4j.rest.imp.adapter.LoginRequestDataAdapter;
import everyos.nerimity.nerimity4j.rest.imp.adapter.LoginResponseDataAdapter;
import everyos.nerimity.nerimity4j.rest.imp.converter.json.JsonConverterFactoryBuilder;
import everyos.nerimity.nerimity4j.rest.service.Service;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter.Factory;
import retrofit2.Retrofit;

public class RestClientImp implements RestClient {

	private final Retrofit retrofit;
	private final Map<Class<? extends Service>, Service> loadedServices = new HashMap<>();

	public RestClientImp(RestClientConfigs configs) {
		this.retrofit = createRetrofitInstance(configs);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends Service> T getService(Class<T> serviceClass) {
		return (T) loadedServices.computeIfAbsent(serviceClass, key -> {
			try {
				return retrofit.create(serviceClass);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	private Retrofit createRetrofitInstance(RestClientConfigs configs) {
		return new Retrofit.Builder()
			.baseUrl(configs.endpoint())
			.client(createOkHttpClientInstance(configs.token()))
			.addConverterFactory(createJsonConverterFactory())
			.addCallAdapterFactory(ReactorCallAdapterFactory.create())
			.build();
	}

	private OkHttpClient createOkHttpClientInstance(Optional<String> token) {
		HttpLoggingInterceptor loggingInterceptor =
			new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
		
		Builder httpBuilder = new OkHttpClient.Builder()
			.addInterceptor(loggingInterceptor);

		if (token.isPresent()) {
			httpBuilder.addInterceptor(chain -> {
				Request request = chain.request().newBuilder()
					.addHeader("Authorization", token.get())
					.build();
				return chain.proceed(request);
			});
		}
		
		return httpBuilder.build();
	}

	private Factory createJsonConverterFactory() {
		return new JsonConverterFactoryBuilder()
			.registerTypeAdapter(LoginRequestData.class, new LoginRequestDataAdapter())
			.registerTypeAdapter(LoginResponseData.class, new LoginResponseDataAdapter())
			.build();
	}
	
}
