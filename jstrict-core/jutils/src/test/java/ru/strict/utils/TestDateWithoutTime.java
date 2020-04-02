package ru.strict.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Date;
import java.util.GregorianCalendar;

@RunWith(JUnit4.class)
public class TestDateWithoutTime {

    @Test
    public void test(){
        Date dateWithTime = new GregorianCalendar(2000, 1, 1, 12, 30, 0).getTime();
        Date dateWithoutTime = new GregorianCalendar(2000, 1, 1).getTime();

        Assert.assertEquals(DateUtil.getDateWithoutTime(dateWithTime), dateWithoutTime);
    }
}
