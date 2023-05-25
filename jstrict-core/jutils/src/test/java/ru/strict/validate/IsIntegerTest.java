package ru.strict.validate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IsIntegerTest {

    @Test
    void testIsInteger_common1_success() {
        assertTrue(CommonValidator.isInteger("1"));
    }

    @Test
    void testIsInteger_common2_success() {
        assertTrue(CommonValidator.isInteger("-1"));
    }

    @Test
    void testIsInteger_double_false() {
        assertFalse(CommonValidator.isInteger("1.0"));
    }

    @Test
    void testIsInteger_space_false() {
        assertFalse(CommonValidator.isInteger(" "));
    }

    @Test
    void testIsInteger_empty_false() {
        assertFalse(CommonValidator.isInteger(""));
    }

    @Test
    void testIsInteger_null_false() {
        assertFalse(CommonValidator.isInteger(null));
    }
}
