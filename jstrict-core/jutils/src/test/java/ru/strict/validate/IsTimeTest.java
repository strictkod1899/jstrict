package ru.strict.validate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IsTimeTest {

    @Test
    public void testIsTime_zero_success() {
        Assertions.assertTrue(CommonValidate.isTime("00:00:00", ':'));
    }

    @Test
    public void testIsTime_edge_success() {
        Assertions.assertTrue(CommonValidate.isTime("23-59-59", '-'));
    }

    @Test
    public void testIsTime_negative_false() {
        Assertions.assertFalse(CommonValidate.isTime( "-01:01:01", ':'));
    }

    @Test
    public void testIsTime_notValidHours_false() {
        Assertions.assertFalse(CommonValidate.isTime( "24:00:00", ':'));
    }

    @Test
    public void testIsTime_notValidMinutes_false() {
        Assertions.assertFalse(CommonValidate.isTime( "23:60:00", ':'));
    }

    @Test
    public void testIsTime_notValidSeconds_false() {
        Assertions.assertFalse(CommonValidate.isTime( "23:00:60", ':'));
    }

    @Test
    public void testIsTime_empty_false() {
        Assertions.assertFalse(CommonValidate.isTime( "", '-'));
    }

    @Test
    public void testIsTime_null_false() {
        Assertions.assertFalse(CommonValidate.isTime( null, '-'));
    }
}
