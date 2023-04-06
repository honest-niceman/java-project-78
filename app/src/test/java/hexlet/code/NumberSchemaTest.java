package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberSchemaTest {

    public static final int ZERO = 0;
    public static final int TEN = 10;
    public static final int FIVE = 5;
    public static final int ONE = 1;
    public static final int MINUS_ONE = -ONE;
    public static final int ELEVEN = 11;

    @Test
    public void testIsValid() {
        // Test null value when not required
        NumberSchema schema = new NumberSchema();
        schema.positive().range(ZERO, TEN);
        assertTrue(schema.isValid(null));

        // Test null value when required
        schema.required();
        assertFalse(schema.isValid(null));

        // Test invalid type
        assertFalse(schema.isValid("not a number"));

        // Test invalid value for positive number
        assertFalse(schema.isValid(MINUS_ONE));

        // Test invalid value for range
        assertFalse(schema.isValid(ELEVEN));

        // Test valid value
        assertTrue(schema.isValid(FIVE));
    }

    @Test
    public void testRange() {
        // Test range disabled
        NumberSchema schema = new NumberSchema();
        assertTrue(schema.isValid(ZERO));
        assertTrue(schema.isValid(TEN));

        // Test valid range
        schema.range(ZERO, TEN);
        assertTrue(schema.isValid(ZERO));
        assertTrue(schema.isValid(FIVE));
        assertTrue(schema.isValid(TEN));

        // Test invalid range
        assertFalse(schema.isValid(MINUS_ONE));
        assertFalse(schema.isValid(ELEVEN));
    }

    @Test
    public void testPositive() {
        // Test positive disabled
        NumberSchema schema = new NumberSchema();
        assertTrue(schema.isValid(ZERO));
        assertTrue(schema.isValid(-TEN));

        // Test valid positive
        schema.positive();
        assertTrue(schema.isValid(ONE));
        assertTrue(schema.isValid(TEN));

        // Test invalid positive
        assertFalse(schema.isValid(ZERO));
        assertFalse(schema.isValid(MINUS_ONE));
    }

    @Test
    public void testRequired() {
        // Test required is disabled
        NumberSchema schema = new NumberSchema();
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(ZERO));

        // Test required is enabled
        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(ZERO));
    }
}
