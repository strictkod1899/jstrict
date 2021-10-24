package ru.strict.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateTimeUtil {

    private DateTimeUtil() {}

    /**
     * Получить LocalDate из Date с системным часовым поясом
     */
    public static LocalDate getLocalDateFromDate(Date date) {
        return getLocalDateFromDate(date, ZoneId.systemDefault());
    }

    /**
     * Получить LocalDate из Date с указанным часовым поиском
     */
    public static LocalDate getLocalDateFromDate(Date date, ZoneId zoneId) {
        return Instant.ofEpochMilli(date.getTime())
                .atZone(zoneId)
                .toLocalDate();
    }

    /**
     * Получить LocalDateTime из Date с указанным часовым поиском
     */
    public static LocalDateTime getLocalDateTimeFromDate(Date date) {
        return getLocalDateTimeFromDate(date, ZoneId.systemDefault());
    }

    /**
     * Получить LocalDateTime из Date с указанным часовым поиском
     */
    public static LocalDateTime getLocalDateTimeFromDate(Date date, ZoneId zoneId) {
        return Instant.ofEpochMilli(date.getTime())
                .atZone(zoneId)
                .toLocalDateTime();
    }

    /**
     * Получить количество миллисекунд из объекта LocalDate с системным часовым поясом
     */
    public static Long getMillisFromLocalDate(LocalDate date) {
        return getMillisFromLocalDate(date, ZoneId.systemDefault());
    }

    /**
     * Получить количество миллисекунд из объекта LocalDate в указаном часовом поясе
     */
    public static Long getMillisFromLocalDate(LocalDate date, ZoneId zoneId) {
        return date.atStartOfDay()
                .atZone(zoneId)
                .toInstant()
                .toEpochMilli();
    }

    /**
     * Получить количество миллисекунд из объекта LocalDateTime с системным часовым поясом
     */
    public static Long getMillisFromLocalDateTime(LocalDateTime date) {
        return getMillisFromLocalDateTime(date, ZoneId.systemDefault());
    }

    /**
     * Получить количество миллисекунд из объекта LocalDateTime в указаном часовом поясе
     */
    public static Long getMillisFromLocalDateTime(LocalDateTime date, ZoneId zoneId) {
        return date.atZone(zoneId)
                .toInstant()
                .toEpochMilli();
    }
}
