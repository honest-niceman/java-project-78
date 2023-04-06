package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringSchemaTest {
    @Test
    public void testIsValidWithNull() {
        StringSchema schema = new StringSchema();
        assertTrue(schema.isValid(null));
    }

    @Test
    public void testIsValidWithNonString() {
        StringSchema schema = new StringSchema();
        assertFalse(schema.isValid(123));
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
        schema.minLength(3);
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
        schema.minLength(5);
        schema.contains("world");
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid("hello"));
        assertFalse(schema.isValid("worli"));
        assertFalse(schema.isValid("hello "));
        assertFalse(schema.isValid("helwo"));
        assertTrue(schema.isValid("hello world"));
    }
}