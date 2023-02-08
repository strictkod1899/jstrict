package ru.strict.validate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaxLengthTest {

    @Test
    void testIsMaxLength_edge_success() {
        assertTrue(CommonValidator.isMaxLength("123456", 6));
    }

    @Test
    void testIsMaxLength_moreEdge_success() {
        assertTrue(CommonValidator.isMaxLength("123456", 7));
    }

    @Test
    void testIsMaxLength_empty_success() {
        assertTrue(CommonValidator.isMaxLength("", 0));
    }

    @Test
    void testIsMaxLength_space_success() {
        assertTrue(CommonValidator.isMaxLength(" ", 1));
    }

    @Test
    void testIsMaxLength_lessThenMax_fail() {
        assertFalse(CommonValidator.isMaxLength("123456", 2));
    }

    @Test
    void testIsMaxLength_null_fail() {
        assertFalse(CommonValidator.isMaxLength(null, 0));
    }
}
