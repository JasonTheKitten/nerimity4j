package everyos.nerimity.nerimity4j.rest.imp.converter.json;

import java.util.HashMap;
import java.util.Map;

public class JsonConverterFactoryBuilder {

	private final Map<String, Object> adapters = new HashMap<>();
	
	public JsonConverterFactoryBuilder registerTypeAdapter(Class<?> cls, Object adapter) {
		adapters.put(cls.getCanonicalName(), adapter);
		return this;
	}

	public JsonConverterFactoryBuilder registerTypeAdapter(ParameterizedType type, Object adapter) {
		adapters.put(type.toString(), adapter);
		
		return this;
	}

	public JsonConverterFactory build() {
		return new JsonConverterFactory(JsonConverterContext.create(adapters));
	}

}
