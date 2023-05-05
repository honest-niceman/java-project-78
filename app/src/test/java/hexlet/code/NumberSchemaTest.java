package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Assertions;
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
    public void testRange() {
        // Test range disabled
        Validator v = new Validator();
        NumberSchema schema = v.number();
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
        Validator v = new Validator();
        NumberSchema schema = v.number();
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
    public void nullTest() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        Assertions.assertTrue(schema.isValid(null));
        schema.required();
        Assertions.assertFalse(schema.isValid(null));
    }

}
