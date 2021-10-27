package ru.strict.validate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IsDateYearTest {

    @Test
    void testIsDateStartYear_common_success() {
        assertTrue(CommonValidate.isDateStartYear("2000-01-01", '-'));
    }

    @Test
    void testIsDateStartYear_slash_success() {
        assertTrue(CommonValidate.isDateStartYear("0000/12/31", '/'));
    }

    @Test
    void testIsDateStartYear_common_false() {
        assertFalse(CommonValidate.isDateStartYear("-0001-01-01", '-'));
    }

    @Test
    void testIsDateStartYear_notValidMonth_false() {
        assertFalse(CommonValidate.isDateStartYear("2000-13-01", '-'));
    }

    @Test
    void testIsDateStartYear_notValidDay_false() {
        assertFalse(CommonValidate.isDateStartYear("2000-12-32", '-'));
    }

    @Test
    void testIsDateStartYear_empty_false() {
        assertFalse(CommonValidate.isDateStartYear("", '-'));
    }

    @Test
    void testIsDateStartYear_null_false() {
        assertFalse(CommonValidate.isDateStartYear(null, '-'));
    }
}
