package yuriy.weiss.javafx.training.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private JsonUtils() {
    }

    public static <T> String objectToJsonString( T object ) {
        try {
            return OBJECT_MAPPER.writeValueAsString( object );
        } catch ( JsonProcessingException e ) {
            throw new RuntimeException( e );
        }
    }

    public static <T> T jsonStringToObject( String jsonString, Class<T> objectClass ) {
        try {
            return OBJECT_MAPPER.readValue( jsonString, objectClass );
        } catch ( JsonProcessingException e ) {
            throw new RuntimeException( e );
        }
    }
}
