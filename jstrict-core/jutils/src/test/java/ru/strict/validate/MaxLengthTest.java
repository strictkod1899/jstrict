package ru.strict.validate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MaxLengthTest {

    @Test
    public void testIsMaxLength_edge_success() {
        Assertions.assertTrue(CommonValidate.isMaxLength("123456", 6));
    }

    @Test
    public void testIsMaxLength_moreEdge_success() {
        Assertions.assertTrue(CommonValidate.isMaxLength("123456", 7));
    }

    @Test
    public void testIsMaxLength_empty_success() {
        Assertions.assertTrue(CommonValidate.isMaxLength("", 0));
    }

    @Test
    public void testIsMaxLength_space_success() {
        Assertions.assertTrue(CommonValidate.isMaxLength(" ", 1));
    }

    @Test
    public void testIsMaxLength_lessThenMax_fail() {
        Assertions.assertFalse(CommonValidate.isMaxLength("123456", 2));
    }

    @Test
    public void testIsMaxLength_null_fail() {
        Assertions.assertFalse(CommonValidate.isMaxLength(null, 0));
    }
}
