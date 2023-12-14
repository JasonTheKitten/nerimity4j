package everyos.nerimity.nerimity4j.rest.imp.converter.json;

import dev.mccue.json.Json;

public interface JsonDeserializer<T> {
	
	T deserialize(Json json, JsonConverterContext context);

}
