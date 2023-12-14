package everyos.nerimity.nerimity4j.rest.imp.adapter;

import dev.mccue.json.Json;
import everyos.nerimity.nerimity4j.json.entity.LoginRequestData;
import everyos.nerimity.nerimity4j.rest.imp.converter.json.JsonConverterContext;
import everyos.nerimity.nerimity4j.rest.imp.converter.json.JsonSerializer;

public class LoginRequestDataAdapter implements JsonSerializer<LoginRequestData> {

	@Override
	public Json serialize(LoginRequestData object, JsonConverterContext context) {
		return Json.objectBuilder()
			.put("email", object.email())
			.put("password", object.password())
			.build();
	}
	
}
