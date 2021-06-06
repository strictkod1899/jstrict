package ru.strict.validate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IsDateDayTest {

    @Test
    public void testIsDateStartDay_common_success() {
        Assertions.assertTrue(CommonValidate.isDateStartDay("01-01-2000", '-'));
    }

    @Test
    public void testIsDateStartDay_slash_success() {
        Assertions.assertTrue(CommonValidate.isDateStartDay("31/12/0000", '/'));
    }

    @Test
    public void testIsDateStartDay_common_false() {
        Assertions.assertFalse(CommonValidate.isDateStartDay("-01-01-0001", '-'));
    }

    @Test
    public void testIsDateStartDay_notValidMonth_false() {
        Assertions.assertFalse(CommonValidate.isDateStartDay("01-13-2000", '-'));
    }

    @Test
    public void testIsDateStartDay_notValidDay_false() {
        Assertions.assertFalse(CommonValidate.isDateStartDay("32-12-2000", '-'));
    }

    @Test
    public void testIsDateStartDay_empty_false() {
        Assertions.assertFalse(CommonValidate.isDateStartDay("", '-'));
    }

    @Test
    public void testIsDateStartDay_nunll_false() {
        Assertions.assertFalse(CommonValidate.isDateStartDay(null, '-'));
    }
}
