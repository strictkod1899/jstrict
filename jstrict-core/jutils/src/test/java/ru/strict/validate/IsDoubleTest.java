package ru.strict.validate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IsDoubleTest {

    @Test
    void testIsDouble_common1_success() {
        assertTrue(CommonValidate.isDouble("1.0"));
    }

    @Test
    void testIsDouble_common2_success() {
        assertTrue(CommonValidate.isDouble("-1.0"));
    }

    @Test
    void testIsDouble_common3_success() {
        assertTrue(CommonValidate.isDouble("1"));
    }

    @Test
    void testIsDouble_space_false() {
        assertFalse(CommonValidate.isDouble(" "));
    }

    @Test
    void testIsDouble_empty_false() {
        assertFalse(CommonValidate.isDouble(""));
    }

    @Test
    void testIsDouble_null_false() {
        assertFalse(CommonValidate.isDouble(null));
    }
}
