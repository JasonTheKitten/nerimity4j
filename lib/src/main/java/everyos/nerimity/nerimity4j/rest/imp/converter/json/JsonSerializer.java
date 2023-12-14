package everyos.nerimity.nerimity4j.rest.imp.converter.json;

import dev.mccue.json.Json;

public interface JsonSerializer<T> {
	
	Json serialize(T object, JsonConverterContext context);

}
