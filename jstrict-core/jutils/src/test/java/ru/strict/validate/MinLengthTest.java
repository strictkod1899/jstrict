package ru.strict.validate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinLengthTest {

    @Test
    void testIsMinLength_common_success() {
        assertTrue(CommonValidator.isMinLength("123456", 6));
    }

    @Test
    void testIsMinLength_common_false() {
        assertFalse(CommonValidator.isMinLength("123456", 7));
    }

    @Test
    void testIsMinLength_empty_false() {
        assertFalse(CommonValidator.isMinLength("", 1));
    }

    @Test
    void testIsMinLength_space_success() {
        assertTrue(CommonValidator.isMinLength(" ", 0));
    }

    @Test
    void testIsMinLength_null_false() {
        assertFalse(CommonValidator.isMinLength(null, 0));
    }
}
