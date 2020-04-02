package ru.strict.utils;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;
import java.util.HashMap;
import java.util.Map;

import static java.time.temporal.ChronoField.*;

public final class DateFormatters {

    private DateFormatters(){}

    /**
     * Месяцы в родительном падеже для форматирования даты
     */
    private static final Map<Long, String> OF_MONTHS = new HashMap() {
        {
            put(1L, "Января");
            put(2L, "Февраля");
            put(3L, "Марта");
            put(4L, "Апреля");
            put(5L, "Мая");
            put(6L, "Июня");
            put(7L, "Июля");
            put(8L, "Августа");
            put(9L, "Сентября");
            put(10L, "Октября");
            put(11L, "Ноября");
            put(12L, "Декабря");
        }
    };
    /**
     * Форматирование даты в формате '1 Февраля 2019'
     */
    public static final DateTimeFormatter NAMED_DATE_FORMATTER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .parseLenient()
            .appendValue(DAY_OF_MONTH, 1, 2, SignStyle.NOT_NEGATIVE)
            .appendLiteral(' ')
            .appendText(MONTH_OF_YEAR, OF_MONTHS)
            .appendLiteral(' ')
            .appendValue(YEAR, 4)
            .toFormatter();
    /**
     * Форматирование даты в формате '01.02.2019'
     */
    public static final DateTimeFormatter DATE_FORMATTER_1 = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    /**
     * Форматирование даты в формате '01/02/2019'
     */
    public static final DateTimeFormatter DATE_FORMATTER_2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
}
