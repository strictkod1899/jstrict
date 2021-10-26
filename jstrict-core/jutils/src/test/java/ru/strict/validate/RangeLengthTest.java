package ru.strict.validate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RangeLengthTest {

    @Test
    void testIsRangeLength_edge_success() {
        assertTrue(CommonValidate.isRangeLength("123456", 6, 6));
    }

    @Test
    void testIsRangeLength_empty_success() {
        assertTrue(CommonValidate.isRangeLength("", 0, 1));
    }

    @Test
    void testIsRangeLength_space_success() {
        assertTrue(CommonValidate.isRangeLength(" ", 0, 2));
    }

    @Test
    void testIsRangeLength_moreThenRange_false() {
        assertFalse(CommonValidate.isRangeLength("123456", 0, 5));
    }

    @Test
    void testIsRangeLength_null_false() {
        assertFalse(CommonValidate.isRangeLength(null, 0, 2));
    }
}
