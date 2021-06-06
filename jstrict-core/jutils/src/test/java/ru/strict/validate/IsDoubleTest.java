package ru.strict.validate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IsDoubleTest {

    @Test
    public void testIsDouble_common1_success() {
        Assertions.assertTrue(CommonValidate.isDouble("1.0"));
    }

    @Test
    public void testIsDouble_common2_success() {
        Assertions.assertTrue(CommonValidate.isDouble("-1.0"));
    }

    @Test
    public void testIsDouble_common3_success() {
        Assertions.assertTrue(CommonValidate.isDouble("1"));
    }

    @Test
    public void testIsDouble_space_false() {
        Assertions.assertFalse(CommonValidate.isDouble(" "));
    }

    @Test
    public void testIsDouble_empty_false() {
        Assertions.assertFalse(CommonValidate.isDouble(""));
    }

    @Test
    public void testIsDouble_null_false() {
        Assertions.assertFalse(CommonValidate.isDouble(null));
    }
}
