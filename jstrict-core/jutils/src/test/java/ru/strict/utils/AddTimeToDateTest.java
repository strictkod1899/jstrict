package ru.strict.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.GregorianCalendar;

public class AddTimeToDateTest {

    @Test
    public void testAddDays(){
        Date startDate = new GregorianCalendar(2000, 1, 1).getTime();
        Date expectedDate = new GregorianCalendar(2000, 1, 6).getTime();

        Assertions.assertEquals(DateUtil.addDaysToDate(startDate, 5), expectedDate);
    }

    @Test
    public void testAddMonths(){
        Date startDate = new GregorianCalendar(2000, 1, 1).getTime();
        Date expectedDate = new GregorianCalendar(2000, 6, 1).getTime();

        Assertions.assertEquals(DateUtil.addMonthsToDate(startDate, 5), expectedDate);
    }

    @Test
    public void testAddYears(){
        Date startDate = new GregorianCalendar(2000, 1, 1).getTime();
        Date expectedDate = new GregorianCalendar(2005, 1, 1).getTime();

        Assertions.assertEquals(DateUtil.addYearsToDate(startDate, 5), expectedDate);
    }
}
