package ru.strict.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.LocalDate;

@RunWith(JUnit4.class)
public class DateFormattersTest {

    private static final LocalDate DATE = LocalDate.of(2019, 2, 23);

    @Test
    public void testNamedDateFormatter() {
        Assert.assertEquals("23 Февраля 2019", DATE.format(DateFormatters.NAMED_DATE_FORMATTER));
    }

    @Test
    public void testDateFormatter1() {
        Assert.assertEquals("23.02.2019", DATE.format(DateFormatters.DATE_FORMATTER_1));
    }

    @Test
    public void testDateFormatter2() {
        Assert.assertEquals("23/02/2019", DATE.format(DateFormatters.DATE_FORMATTER_2));
    }
}
