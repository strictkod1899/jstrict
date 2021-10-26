package ru.strict.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateFormatterTest {

    @Test
    void testFormat_named_success() {
        var date = LocalDate.of(2019, 2, 23);
        assertEquals("23 Февраля 2019", date.format(DateFormatter.NAMED_DATE_FORMATTER));
    }

    @Test
    void tesFormat_dmyByPoint_success() {
        var date = LocalDate.of(2019, 2, 23);
        assertEquals("23.02.2019", date.format(DateFormatter.DATE_FORMAT_DMY_BY_POINT));
    }

    @Test
    void testFormat_dmyBySlash_success() {
        var date = LocalDate.of(2019, 2, 23);
        assertEquals("23/02/2019", date.format(DateFormatter.DATE_FORMAT_DMY_BY_SLASH));
    }
}
