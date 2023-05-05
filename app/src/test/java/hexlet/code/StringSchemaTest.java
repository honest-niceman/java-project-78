package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringSchemaTest {

    public static final int MIN_LENGTH_3 = 3;
    public static final int MIN_LENGTH_5 = 5;

    @Test
    public void testIsValidWithEmptyString() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertTrue(schema.isValid(""));
    }

    @Test
    public void testIsValidWithRequiredAndEmptyString() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        schema.required();
        assertFalse(schema.isValid(""));
    }

    @Test
    public void testIsValidWithMinLength() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        schema.minLength(MIN_LENGTH_3);
        assertFalse(schema.isValid("ab"));
        assertTrue(schema.isValid("abc"));
    }

    @Test
    public void testIsValidWithAllChecks() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        schema.required();
        schema.minLength(MIN_LENGTH_5);
        schema.contains("world");
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid("hello"));
        assertFalse(schema.isValid("worli"));
        assertFalse(schema.isValid("hello "));
        assertFalse(schema.isValid("helwo"));
        assertTrue(schema.isValid("hello world"));
    }

    @Test
    public void nullTest() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        Assertions.assertTrue(schema.isValid(null));
        schema.required();
        Assertions.assertFalse(schema.isValid(null));
    }
}
