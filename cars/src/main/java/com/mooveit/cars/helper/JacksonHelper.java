package com.mooveit.cars.helper;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JacksonHelper {

	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	public static final ObjectMapper OBJECT_NON_NULL_MAPPER = new ObjectMapper()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
			.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true)
			.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true)
			.enable(SerializationFeature.INDENT_OUTPUT)
			.setSerializationInclusion(JsonInclude.Include.NON_NULL);

	private JacksonHelper() {
	}

	public static <T> T fromString(String string, Class<T> clazz) throws IOException {
		return OBJECT_MAPPER.readerFor(clazz).readValue(string);
	}

	public static String toString(Object value)  {
		try {
			return OBJECT_MAPPER.writer().writeValueAsString(value);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String prettyPrintString(Object value)  {
		try {
			return OBJECT_NON_NULL_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(value);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "";
	}

}
