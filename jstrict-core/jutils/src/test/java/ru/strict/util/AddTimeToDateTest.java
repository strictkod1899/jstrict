package ru.strict.util;

import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class AddTimeToDateTest {

    @Test
    void testAddDays() {
        var startDate = new GregorianCalendar(2000, 1, 1).getTime();
        var expectedDate = new GregorianCalendar(2000, 1, 6).getTime();

        assertEquals(DateUtil.addDaysToDate(startDate, 5), expectedDate);
    }

    @Test
    void testAddMonths() {
        var startDate = new GregorianCalendar(2000, 1, 1).getTime();
        var expectedDate = new GregorianCalendar(2000, 6, 1).getTime();

        assertEquals(DateUtil.addMonthsToDate(startDate, 5), expectedDate);
    }

    @Test
    void testAddYears() {
        var startDate = new GregorianCalendar(2000, 1, 1).getTime();
        var expectedDate = new GregorianCalendar(2005, 1, 1).getTime();

        assertEquals(DateUtil.addYearsToDate(startDate, 5), expectedDate);
    }
}
