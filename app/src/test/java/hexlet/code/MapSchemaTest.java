package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class MapSchemaTest {
    @Test
    void testIsValidWithNullObject() {
        MapSchema schema = new MapSchema();
        boolean isValid = schema.isValid(null);
        Assertions.assertTrue(isValid);
    }

    @Test
    void testIsValidWithNonMapObject() {
        MapSchema schema = new MapSchema();
        boolean isValid = schema.isValid("not a map");
        Assertions.assertFalse(isValid);
    }

    @Test
    void testIsValidWithValidMapObject() {
        MapSchema schema = new MapSchema();
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        boolean isValid = schema.isValid(map);
        Assertions.assertTrue(isValid);
    }

    @Test
    void testIsValidWithMapObjectAndRequired() {
        MapSchema schema = new MapSchema();
        schema.required();
        boolean isValid = schema.isValid(null);
        Assertions.assertFalse(isValid);
    }

    @Test
    void testIsValidWithMapObjectAndSizeOf() {
        MapSchema schema = new MapSchema().sizeof(2);
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        boolean isValid = schema.isValid(map);
        Assertions.assertTrue(isValid);
    }

    @Test
    void testIsValidWithMapObjectAndInvalidSizeOf() {
        MapSchema schema = new MapSchema().sizeof(2);
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        boolean isValid = schema.isValid(map);
        Assertions.assertFalse(isValid);
    }
}
