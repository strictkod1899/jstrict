package ru.strict.validate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IsEmptyOrNullTest {

    @Test
    public void testIsEmptyOrNull_empty_success() {
        Assertions.assertTrue(CommonValidate.isEmptyOrNull(""));
    }

    @Test
    public void testIsEmptyOrNull_null_success() {
        Assertions.assertTrue(CommonValidate.isEmptyOrNull((String) null));
    }

    @Test
    public void testIsEmptyOrNull_space_false() {
        Assertions.assertFalse(CommonValidate.isEmptyOrNull(" "));
    }

    @Test
    public void testIsEmptyOrNull_notEmpty_false() {
        Assertions.assertFalse(CommonValidate.isEmptyOrNull("not empty"));
    }
}
