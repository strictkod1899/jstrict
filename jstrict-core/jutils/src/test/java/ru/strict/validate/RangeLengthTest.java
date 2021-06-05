package ru.strict.validate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RangeLengthTest {

    @Test
    public void testIsRangeLength_edge_success() {
        Assertions.assertTrue(CommonValidate.isRangeLength("123456", 6, 6));
    }

    @Test
    public void testIsRangeLength_empty_success() {
        Assertions.assertTrue(CommonValidate.isRangeLength("", 0, 1));
    }

    @Test
    public void testIsRangeLength_space_success() {
        Assertions.assertTrue(CommonValidate.isRangeLength(" ", 0, 2));
    }

    @Test
    public void testIsRangeLength_moreThenRange_false() {
        Assertions.assertFalse(CommonValidate.isRangeLength("123456", 0, 5));
    }

    @Test
    public void testIsRangeLength_null_false() {
        Assertions.assertFalse(CommonValidate.isRangeLength(null, 0, 2));
    }
}
