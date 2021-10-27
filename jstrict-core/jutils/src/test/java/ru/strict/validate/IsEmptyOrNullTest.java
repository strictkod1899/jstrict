package ru.strict.validate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IsEmptyOrNullTest {

    @Test
    void testIsEmptyOrNull_empty_success() {
        assertTrue(CommonValidate.isNullOrEmpty(""));
    }

    @Test
    void testIsEmptyOrNull_null_success() {
        assertTrue(CommonValidate.isNullOrEmpty((String) null));
    }

    @Test
    void testIsEmptyOrNull_space_false() {
        assertFalse(CommonValidate.isNullOrEmpty(" "));
    }

    @Test
    void testIsEmptyOrNull_notEmpty_false() {
        assertFalse(CommonValidate.isNullOrEmpty("not empty"));
    }
}
