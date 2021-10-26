package ru.strict.validate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IsEmptyOrNullTest {

    @Test
    void testIsEmptyOrNull_empty_success() {
        assertTrue(CommonValidate.isEmptyOrNull(""));
    }

    @Test
    void testIsEmptyOrNull_null_success() {
        assertTrue(CommonValidate.isEmptyOrNull((String) null));
    }

    @Test
    void testIsEmptyOrNull_space_false() {
        assertFalse(CommonValidate.isEmptyOrNull(" "));
    }

    @Test
    void testIsEmptyOrNull_notEmpty_false() {
        assertFalse(CommonValidate.isEmptyOrNull("not empty"));
    }
}
