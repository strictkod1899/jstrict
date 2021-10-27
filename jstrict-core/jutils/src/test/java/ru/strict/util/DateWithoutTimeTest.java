package ru.strict.util;

import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class DateWithoutTimeTest {

    @Test
    void test() {
        var dateWithTime = new GregorianCalendar(2000, 1, 1, 12, 30, 0).getTime();
        var dateWithoutTime = new GregorianCalendar(2000, 1, 1).getTime();

        assertEquals(DateUtil.getDateWithoutTime(dateWithTime), dateWithoutTime);
    }
}
