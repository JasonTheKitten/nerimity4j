package everyos.nerimity.nerimity4j.rest.imp.converter.json;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Converter.Factory;
import retrofit2.Retrofit;

public class JsonConverterFactory extends Factory {

	private JsonConverterContext context;

	public JsonConverterFactory(JsonConverterContext context) {
		this.context = context;
	}
	
	@Override
	public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
		return new JsonResponseBodyConverter<>(context.getDeserializer(type), context);
	}

	@Override
	public Converter<?, RequestBody> requestBodyConverter(
		Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit
	) {
		return new JsonRequestBodyConverter<>(context.getSerializer(type), context);
	}

}
