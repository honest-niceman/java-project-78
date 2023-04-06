package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("checkstyle:magicnumber")
class StringSchemaTest {

    public static final int MIN_LENGTH_3 = 3;
    public static final int MIN_LENGTH_5 = 5;

    @Test
    public void testIsValidWithNull() {
        StringSchema schema = new StringSchema();
        assertTrue(schema.isValid(null));
    }

    @Test
    public void testIsValidWithNonString() {
        StringSchema schema = new StringSchema();
        assertFalse(schema.isValid(Integer.MAX_VALUE));
    }

    @Test
    public void testIsValidWithEmptyString() {
        StringSchema schema = new StringSchema();
        assertTrue(schema.isValid(""));
    }

    @Test
    public void testIsValidWithRequiredAndEmptyString() {
        StringSchema schema = new StringSchema();
        schema.required();
        assertFalse(schema.isValid(""));
    }

    @Test
    public void testIsValidWithMinLength() {
        StringSchema schema = new StringSchema();
        schema.minLength(MIN_LENGTH_3);
        assertFalse(schema.isValid("ab"));
        assertTrue(schema.isValid("abc"));
    }

    @Test
    public void testIsValidWithContains() {
        StringSchema schema = new StringSchema();
        schema.contains("world").contains("hello");
        assertFalse(schema.isValid("goodbye"));
        assertFalse(schema.isValid("hello"));
        assertFalse(schema.isValid("world"));
        assertTrue(schema.isValid("hello world"));
    }

    @Test
    public void testIsValidWithAllChecks() {
        StringSchema schema = new StringSchema();
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
}
