package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class MapSchemaTest {

    @Test
    void testIsValidWithValidMapObject() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        boolean isValid = schema.isValid(map);
        Assertions.assertTrue(isValid);
    }

    @Test
    void testIsValidWithMapObjectAndRequired() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        schema.required();
        boolean isValid = schema.isValid(null);
        Assertions.assertFalse(isValid);
    }

    @Test
    void testIsValidWithMapObjectAndSizeOf() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        schema.sizeof(2);
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        boolean isValid = schema.isValid(map);
        Assertions.assertTrue(isValid);
    }

    @Test
    void testIsValidWithMapObjectAndInvalidSizeOf() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        schema.sizeof(2);
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        boolean isValid = schema.isValid(map);
        Assertions.assertFalse(isValid);
    }

    @Test
    public void testShapeValid() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        StringSchema stringSchema = v.string();
        stringSchema.required();
        schemas.put("name", stringSchema);
        schemas.put("age", v.number().positive());
        schema.shape(schemas);

        Map<String, Object> human = new HashMap<>();
        human.put("name", "John");
        human.put("age", Integer.MAX_VALUE);
        Assertions.assertTrue(schema.isValid(human));
    }

    @Test
    public void testShapeInvalidValue() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        StringSchema stringSchema = v.string();
        stringSchema.required();
        schemas.put("name", stringSchema);
        schemas.put("age", v.number().positive());
        schema.shape(schemas);

        Map<String, Object> human = new HashMap<>();
        human.put("name", "");
        human.put("age", -1);
        Assertions.assertFalse(schema.isValid(human));
    }

    @Test
    public void testShapeEmptyMap() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schema.shape(schemas);
        Assertions.assertTrue(schema.isValid(new HashMap<>()));
    }

    @Test
    public void nullTest() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Assertions.assertTrue(schema.isValid(null));
        schema.required();
        Assertions.assertFalse(schema.isValid(null));
    }
}
