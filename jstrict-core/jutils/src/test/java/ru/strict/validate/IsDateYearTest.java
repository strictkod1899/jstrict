package ru.strict.validate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IsDateYearTest {

    @Test
    public void testIsDateStartYear_common_success() {
        Assertions.assertTrue(CommonValidate.isDateStartYear("2000-01-01", '-'));
    }

    @Test
    public void testIsDateStartYear_slash_success() {
        Assertions.assertTrue(CommonValidate.isDateStartYear("0000/12/31", '/'));
    }

    @Test
    public void testIsDateStartYear_common_false() {
        Assertions.assertFalse(CommonValidate.isDateStartYear("-0001-01-01", '-'));
    }

    @Test
    public void testIsDateStartYear_notValidMonth_false() {
        Assertions.assertFalse(CommonValidate.isDateStartYear("2000-13-01", '-'));
    }

    @Test
    public void testIsDateStartYear_notValidDay_false() {
        Assertions.assertFalse(CommonValidate.isDateStartYear("2000-12-32", '-'));
    }

    @Test
    public void testIsDateStartYear_empty_false() {
        Assertions.assertFalse(CommonValidate.isDateStartYear("", '-'));
    }

    @Test
    public void testIsDateStartYear_null_false() {
        Assertions.assertFalse(CommonValidate.isDateStartYear(null, '-'));
    }
}
