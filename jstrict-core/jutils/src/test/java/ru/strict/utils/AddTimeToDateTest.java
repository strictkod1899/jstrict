package ru.strict.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Date;
import java.util.GregorianCalendar;

@RunWith(JUnit4.class)
public class AddTimeToDateTest {

    @Test
    public void testAddDays(){
        Date startDate = new GregorianCalendar(2000, 1, 1).getTime();
        Date expectedDate = new GregorianCalendar(2000, 1, 6).getTime();

        Assert.assertEquals(DateUtil.addDaysToDate(startDate, 5), expectedDate);
    }

    @Test
    public void testAddMonths(){
        Date startDate = new GregorianCalendar(2000, 1, 1).getTime();
        Date expectedDate = new GregorianCalendar(2000, 6, 1).getTime();

        Assert.assertEquals(DateUtil.addMonthsToDate(startDate, 5), expectedDate);
    }

    @Test
    public void testAddYears(){
        Date startDate = new GregorianCalendar(2000, 1, 1).getTime();
        Date expectedDate = new GregorianCalendar(2005, 1, 1).getTime();

        Assert.assertEquals(DateUtil.addYearsToDate(startDate, 5), expectedDate);
    }
}
