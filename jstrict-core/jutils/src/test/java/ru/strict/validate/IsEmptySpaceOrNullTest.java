package ru.strict.validate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IsEmptySpaceOrNullTest {

    @Test
    public void testIsEmptySpaceOrNull_empty_success() {
        Assertions.assertTrue(CommonValidate.isEmptySpaceOrNull(""));
    }

    @Test
    public void testIsEmptySpaceOrNull_null_success() {
        Assertions.assertTrue(CommonValidate.isEmptySpaceOrNull(null));
    }

    @Test
    public void testIsEmptySpaceOrNull_space_success() {
        Assertions.assertTrue(CommonValidate.isEmptySpaceOrNull(" "));
    }

    @Test
    public void testIsEmptySpaceOrNull_notEmpty_false() {
        Assertions.assertFalse(CommonValidate.isEmptySpaceOrNull("not empty"));
    }
}
