package everyos.nerimity.nerimity4j.rest.imp.converter.json;

public record ParameterizedType(Class<?> cls, ParameterizedType... children) {

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(cls.getCanonicalName());
		if (children.length > 0) {
			builder.append('<');
			for (int i = 0; i < children.length; i++) {
				if (i != 0) {
					builder.append(",");
				}
				builder.append(children[i].toString());
			}
			builder.append('>');
		}
		
		return builder.toString();
	}
	
	public static ParameterizedType of(Class<?> cls, Class<?>... children) {
		ParameterizedType[] types = new ParameterizedType[children.length];
		for (int i = 0; i < children.length; i++) {
			types[i] = new ParameterizedType(children[i]);
		}
		return new ParameterizedType(cls, types);
	}
	
}
