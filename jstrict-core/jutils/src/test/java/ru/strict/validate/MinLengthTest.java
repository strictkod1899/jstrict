package ru.strict.validate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MinLengthTest {

    @Test
    public void testIsMinLength_common_success() {
        Assertions.assertTrue(CommonValidate.isMinLength("123456", 6));
    }

    @Test
    public void testIsMinLength_common_false() {
        Assertions.assertFalse(CommonValidate.isMinLength("123456", 7));
    }

    @Test
    public void testIsMinLength_empty_false() {
        Assertions.assertFalse(CommonValidate.isMinLength("", 1));
    }

    @Test
    public void testIsMinLength_space_success() {
        Assertions.assertTrue(CommonValidate.isMinLength(" ", 0));
    }

    @Test
    public void testIsMinLength_null_false() {
        Assertions.assertFalse(CommonValidate.isMinLength(null, 0));
    }
}
