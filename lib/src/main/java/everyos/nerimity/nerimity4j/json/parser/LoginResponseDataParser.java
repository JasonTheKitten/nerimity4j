package everyos.nerimity.nerimity4j.json.parser;

import dev.mccue.json.Json;
import dev.mccue.json.JsonDecoder;
import everyos.nerimity.nerimity4j.json.entity.LoginResponseData;

public final class LoginResponseDataParser {
	
	private LoginResponseDataParser() {}
	
	public static LoginResponseData parse(Json json) {
		String token = JsonDecoder.field(json, "token", JsonDecoder::string);
		return new LoginResponseData(token);
	}

}
