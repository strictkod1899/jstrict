package ru.strict.utils;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;
import java.util.HashMap;
import java.util.Map;

import static java.time.temporal.ChronoField.*;

public final class DateFormatters {

    private DateFormatters() {
    }

    /**
     * Месяцы в родительном падеже для форматирования даты
     */
    private static final Map<Long, String> OF_MONTHS = new HashMap<>();

    static {
        OF_MONTHS.put(1L, "Января");
        OF_MONTHS.put(2L, "Февраля");
        OF_MONTHS.put(3L, "Марта");
        OF_MONTHS.put(4L, "Апреля");
        OF_MONTHS.put(5L, "Мая");
        OF_MONTHS.put(6L, "Июня");
        OF_MONTHS.put(7L, "Июля");
        OF_MONTHS.put(8L, "Августа");
        OF_MONTHS.put(9L, "Сентября");
        OF_MONTHS.put(10L, "Октября");
        OF_MONTHS.put(11L, "Ноября");
        OF_MONTHS.put(12L, "Декабря");
    }

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
    /**
     * Форматирование даты в формате '01_02_2019'
     */
    public static final DateTimeFormatter DATE_FORMATTER_3 = DateTimeFormatter.ofPattern("dd_MM_yyyy");
    /**
     * Форматирование даты в формате '01.02.2019 17:01'
     */
    public static final DateTimeFormatter DATETIME_FORMAT_MM = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    /**
     * Форматирование даты в формате '01.02.2019 17:01:31'
     */
    public static final DateTimeFormatter DATETIME_FORMAT_SS = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
}