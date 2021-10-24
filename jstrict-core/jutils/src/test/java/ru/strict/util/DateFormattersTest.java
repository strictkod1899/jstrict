package ru.strict.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class DateFormattersTest {

    private static final LocalDate DATE = LocalDate.of(2019, 2, 23);

    @Test
    public void testNamedDateFormatter() {
        Assertions.assertEquals("23 Февраля 2019", DATE.format(DateFormatters.NAMED_DATE_FORMATTER));
    }

    @Test
    public void testDateFormatter1() {
        Assertions.assertEquals("23.02.2019", DATE.format(DateFormatters.DATE_FORMATTER_1));
    }

    @Test
    public void testDateFormatter2() {
        Assertions.assertEquals("23/02/2019", DATE.format(DateFormatters.DATE_FORMATTER_2));
    }
}
