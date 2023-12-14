package everyos.nerimity.nerimity4j.rest.imp.converter.json;

import java.lang.reflect.Type;
import java.util.Map;

public interface JsonConverterContext {
	
	<T> JsonDeserializer<T> getDeserializer(Type type);

	<T> JsonSerializer<T> getSerializer(Type type);

	public static JsonConverterContext create(Map<String, ?> adapters) {
		return new JsonConverterContext() {

			@Override
			@SuppressWarnings("unchecked")
			public <T> JsonDeserializer<T> getDeserializer(Type type) {
				Object adapter = adapters.get(type.getTypeName());
				if (adapter instanceof JsonDeserializer) {
					return (JsonDeserializer<T>) adapter;
				}

				throw new UnsupportedOperationException("No deserializer found for type " + type);
			}

			@Override
			@SuppressWarnings("unchecked")
			public <T> JsonSerializer<T> getSerializer(Type type) {
				Object adapter = adapters.get(type.getTypeName());
				if (adapter instanceof JsonSerializer) {
					return (JsonSerializer<T>) adapter;
				}

				throw new UnsupportedOperationException("No serializer found for type " + type);
			}
			
		};
	}

}
