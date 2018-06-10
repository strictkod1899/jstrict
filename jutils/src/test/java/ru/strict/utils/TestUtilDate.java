package ru.strict.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

@RunWith(Parameterized.class)
public class TestUtilDate {

    private Date date1, date2;
    private int resultYear, resultMonth, resultDay, resultHour, resultMinutes;

    public TestUtilDate(Date date1, Date date2, int resultYear, int resultMonth, int resultDay, int resultHour, int resultMinutes) {
        this.date1 = date1;
        this.date2 = date2;
        this.resultYear = resultYear;
        this.resultMonth = resultMonth;
        this.resultDay = resultDay;
        this.resultHour = resultHour;
        this.resultMinutes = resultMinutes;
    }

    @Parameters
    public static Collection setUp(){
        Date date1 = new GregorianCalendar(2017,12,23).getTime();
        Date date2 = new GregorianCalendar(2016, 11, 22).getTime();

        return Arrays.asList(new Object[][]{
                {date1, date2, 1, 13, 397, 9528, 571680}
        });
    }


    @Test
    public void testCountYear(){
        Assert.assertEquals(resultYear, UtilDate.diffByYear(date1, date2));
    }

    @Test
    public void testCountMonth(){
        Assert.assertEquals(resultMonth, UtilDate.diffByMonth(date1, date2));
    }

    @Test
    public void testCountDay(){
        Assert.assertEquals(resultDay, UtilDate.diffByDay(date1, date2));
    }

    @Test
    public void testCountHour(){
        Assert.assertEquals(resultHour, UtilDate.diffByHour(date1, date2));
    }

    @Test
    public void testCountMinutes(){
        Assert.assertEquals(resultMinutes, UtilDate.diffByMinutes(date1, date2));
    }
}
