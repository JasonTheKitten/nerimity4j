package everyos.nerimity.nerimity4j.rest.imp.converter.json;

import java.io.IOException;

import dev.mccue.json.Json;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

public class JsonRequestBodyConverter<T> implements Converter<T, RequestBody> {

	private static final MediaType MEDIA_TYPE = MediaType.get("application/json");
	
	private final JsonSerializer<T> serializer;
	private final JsonConverterContext context;

	public JsonRequestBodyConverter(JsonSerializer<T> serializer, JsonConverterContext context) {
		this.serializer = serializer;
		this.context = context;
	}
	
	@Override
	public RequestBody convert(T value) throws IOException {
		Json json = serializer.serialize(value, context);
		return RequestBody.create(Json.writeString(json), MEDIA_TYPE);
	}

}
