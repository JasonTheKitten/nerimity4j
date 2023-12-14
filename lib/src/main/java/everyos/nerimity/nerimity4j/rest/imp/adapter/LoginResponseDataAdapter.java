package everyos.nerimity.nerimity4j.rest.imp.adapter;

import dev.mccue.json.Json;
import dev.mccue.json.JsonDecoder;
import everyos.nerimity.nerimity4j.json.entity.LoginResponseData;
import everyos.nerimity.nerimity4j.rest.imp.converter.json.JsonConverterContext;
import everyos.nerimity.nerimity4j.rest.imp.converter.json.JsonDeserializer;

public class LoginResponseDataAdapter implements JsonDeserializer<LoginResponseData> {

	@Override
	public LoginResponseData deserialize(Json json, JsonConverterContext context) {
		String token = JsonDecoder.field(json, "token", JsonDecoder::string);
		return new LoginResponseData(token);
	}
	
}
