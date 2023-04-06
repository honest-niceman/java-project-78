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

    @Test
    public void testShapeValid() {
        Validator validator = new Validator();
        MapSchema schema = validator.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", validator.string().required());
        schemas.put("age", validator.number().positive());
        schema.shape(schemas);

        Map<String, Object> human = new HashMap<>();
        human.put("name", "John");
        human.put("age", Integer.MAX_VALUE);
        Assertions.assertTrue(schema.isValid(human));
    }

    @Test
    public void testShapeInvalidValue() {
        Validator validator = new Validator();
        MapSchema schema = validator.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", validator.string().required());
        schemas.put("age", validator.number().positive());
        schema.shape(schemas);

        Map<String, Object> human = new HashMap<>();
        human.put("name", "");
        human.put("age", -1);
        Assertions.assertFalse(schema.isValid(human));
    }

    @Test
    public void testShapeNullValue() {
        Validator validator = new Validator();
        MapSchema schema = validator.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", validator.string().required());
        schemas.put("age", validator.number().positive());
        schema.shape(schemas);

        Map<String, Object> human = new HashMap<>();
        human.put("name", "John");
        human.put("age", null);
        Assertions.assertTrue((schema.isValid(human)));
    }

    @Test
    public void testShapeEmptyMap() {
        Validator validator = new Validator();
        MapSchema schema = validator.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schema.shape(schemas);
        Assertions.assertTrue(schema.isValid(new HashMap<>()));
    }
}
