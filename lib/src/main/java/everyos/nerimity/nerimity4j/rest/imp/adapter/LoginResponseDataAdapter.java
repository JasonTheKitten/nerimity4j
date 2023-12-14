package everyos.nerimity.nerimity4j.rest.imp.adapter;

import dev.mccue.json.Json;
import everyos.nerimity.nerimity4j.json.entity.LoginResponseData;
import everyos.nerimity.nerimity4j.json.parser.LoginResponseDataParser;
import everyos.nerimity.nerimity4j.rest.imp.converter.json.JsonConverterContext;
import everyos.nerimity.nerimity4j.rest.imp.converter.json.JsonDeserializer;

public class LoginResponseDataAdapter implements JsonDeserializer<LoginResponseData> {

	@Override
	public LoginResponseData deserialize(Json json, JsonConverterContext context) {
		return LoginResponseDataParser.parse(json);
	}
	
}
