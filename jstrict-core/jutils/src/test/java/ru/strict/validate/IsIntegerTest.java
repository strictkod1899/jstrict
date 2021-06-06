package ru.strict.validate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IsIntegerTest {

    @Test
    public void testIsInteger_common1_success() {
        Assertions.assertTrue(CommonValidate.isInteger("1"));
    }

    @Test
    public void testIsInteger_common2_success() {
        Assertions.assertTrue(CommonValidate.isInteger("-1"));
    }

    @Test
    public void testIsInteger_double_false() {
        Assertions.assertFalse(CommonValidate.isInteger("1.0"));
    }

    @Test
    public void testIsInteger_space_false() {
        Assertions.assertFalse(CommonValidate.isInteger(" "));
    }

    @Test
    public void testIsInteger_empty_false() {
        Assertions.assertFalse(CommonValidate.isInteger(""));
    }

    @Test
    public void testIsInteger_null_false() {
        Assertions.assertFalse(CommonValidate.isInteger(null));
    }
}
