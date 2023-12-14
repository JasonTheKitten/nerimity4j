package everyos.nerimity.nerimity4j.rest.imp.converter.json;

import java.io.IOException;

import dev.mccue.json.Json;
import okhttp3.ResponseBody;
import retrofit2.Converter;

public class JsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

	private JsonDeserializer<T> deserializer;
	private JsonConverterContext context;

	public JsonResponseBodyConverter(JsonDeserializer<T> deserializer, JsonConverterContext context) {
		this.deserializer = deserializer;
		this.context = context;
	}

	@Override
	public T convert(ResponseBody response) throws IOException {
		Json body = Json.readString(response.string());
		return deserializer.deserialize(body, context);
	}

}
