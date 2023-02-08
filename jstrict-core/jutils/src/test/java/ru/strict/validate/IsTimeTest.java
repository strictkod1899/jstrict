package ru.strict.validate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IsTimeTest {

    @Test
    void testIsTime_zero_success() {
        assertTrue(CommonValidator.isTime("00:00:00", ':'));
    }

    @Test
    void testIsTime_edge_success() {
        assertTrue(CommonValidator.isTime("23-59-59", '-'));
    }

    @Test
    void testIsTime_negative_false() {
        assertFalse(CommonValidator.isTime( "-01:01:01", ':'));
    }

    @Test
    void testIsTime_notValidHours_false() {
        assertFalse(CommonValidator.isTime( "24:00:00", ':'));
    }

    @Test
    void testIsTime_notValidMinutes_false() {
        assertFalse(CommonValidator.isTime( "23:60:00", ':'));
    }

    @Test
    void testIsTime_notValidSeconds_false() {
        assertFalse(CommonValidator.isTime( "23:00:60", ':'));
    }

    @Test
    void testIsTime_empty_false() {
        assertFalse(CommonValidator.isTime( "", '-'));
    }

    @Test
    void testIsTime_null_false() {
        assertFalse(CommonValidator.isTime( null, '-'));
    }
}
