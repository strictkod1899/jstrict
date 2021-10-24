package ru.strict.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.GregorianCalendar;

public class DateWithoutTimeTest {

    @Test
    public void test(){
        Date dateWithTime = new GregorianCalendar(2000, 1, 1, 12, 30, 0).getTime();
        Date dateWithoutTime = new GregorianCalendar(2000, 1, 1).getTime();

        Assertions.assertEquals(DateUtil.getDateWithoutTime(dateWithTime), dateWithoutTime);
    }
}
