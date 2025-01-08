package yuriy.weiss.javafx.training.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import javafx.scene.paint.Color;

import java.io.IOException;

public class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER = createObjectMapper();

    public static final String DRAG_INFO_SEPARATOR = "%SEPARATOR%";

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

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addSerializer( Color.class, new StdSerializer<>( Color.class ) {
            @Override
            public void serialize( Color color, JsonGenerator jsonGenerator, SerializerProvider serializerProvider ) throws IOException {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeStringField( "colorName", ColorNames.getColorName( color ) );
                jsonGenerator.writeEndObject();
            }
        } );
        module.addDeserializer( Color.class, new StdDeserializer<>( Color.class ) {
            @Override
            public Color deserialize( JsonParser jsonParser, DeserializationContext deserializationContext ) throws IOException {
                JsonNode node = jsonParser.getCodec().readTree(jsonParser);
                String colorName = node.get("colorName").asText();
                return ColorNames.getColorByName(colorName);
            }
        } );
        mapper.registerModule( module );
        return mapper;
    }
}
