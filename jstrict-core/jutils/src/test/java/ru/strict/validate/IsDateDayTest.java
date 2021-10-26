package ru.strict.validate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IsDateDayTest {

    @Test
    void testIsDateStartDay_common_success() {
        assertTrue(CommonValidate.isDateStartDay("01-01-2000", '-'));
    }

    @Test
    void testIsDateStartDay_slash_success() {
        assertTrue(CommonValidate.isDateStartDay("31/12/0000", '/'));
    }

    @Test
    void testIsDateStartDay_common_false() {
        assertFalse(CommonValidate.isDateStartDay("-01-01-0001", '-'));
    }

    @Test
    void testIsDateStartDay_notValidMonth_false() {
        assertFalse(CommonValidate.isDateStartDay("01-13-2000", '-'));
    }

    @Test
    void testIsDateStartDay_notValidDay_false() {
        assertFalse(CommonValidate.isDateStartDay("32-12-2000", '-'));
    }

    @Test
    void testIsDateStartDay_empty_false() {
        assertFalse(CommonValidate.isDateStartDay("", '-'));
    }

    @Test
    void testIsDateStartDay_nunll_false() {
        assertFalse(CommonValidate.isDateStartDay(null, '-'));
    }
}
