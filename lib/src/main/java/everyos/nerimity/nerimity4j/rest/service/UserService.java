package everyos.nerimity.nerimity4j.rest.service;

import everyos.nerimity.nerimity4j.json.entity.LoginRequestData;
import everyos.nerimity.nerimity4j.json.entity.LoginResponseData;
import reactor.core.publisher.Mono;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService extends Service {

	@POST("users/login")
	public Mono<LoginResponseData> login(@Body LoginRequestData body);

}
