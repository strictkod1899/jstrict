package ru.strict.validate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IsEmptySpaceOrNullTest {

    @Test
    void testIsEmptySpaceOrNull_empty_success() {
        assertTrue(CommonValidate.isEmptySpaceOrNull(""));
    }

    @Test
    void testIsEmptySpaceOrNull_null_success() {
        assertTrue(CommonValidate.isEmptySpaceOrNull(null));
    }

    @Test
    void testIsEmptySpaceOrNull_space_success() {
        assertTrue(CommonValidate.isEmptySpaceOrNull(" "));
    }

    @Test
    void testIsEmptySpaceOrNull_notEmpty_false() {
        assertFalse(CommonValidate.isEmptySpaceOrNull("not empty"));
    }
}
