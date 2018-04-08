package ru.strict.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Управление датами
 */
public class StrictUtilDate {

    /**
     * Метод возвращает разницу в годах между двумя датами
     * @return
     */
    public static long diffByYear(Date d1, Date d2){
        StrictUtilLogger.info(StrictUtilDate.class, "diffByYear - started");
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTimeInMillis(d1.getTime() - d2.getTime());

        long millis = cal.getTimeInMillis();
        long days = millis / 1000 / 60 / 60 / 24 / 30 / 12;
        StrictUtilLogger.info(StrictUtilDate.class, "diffByYear - finished");
        return days;
    }

    /**
     * Метод возвращает разницу в месяцах между двумя датами
     * @return
     */
    public static long diffByMonth(Date d1, Date d2){
        StrictUtilLogger.info(StrictUtilDate.class, "diffByMonth - started");
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTimeInMillis(d1.getTime() - d2.getTime());

        long millis = cal.getTimeInMillis();
        long days = millis / 1000 / 60 / 60 / 24 / 30;
        StrictUtilLogger.info(StrictUtilDate.class, "diffByMonth - finished");
        return days;
    }

    /**
     * Метод возвращает разницу в днях между двумя датами
     * @return
     */
    public static long diffByDay(Date d1, Date d2){
        StrictUtilLogger.info(StrictUtilDate.class, "diffByDay - started");
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTimeInMillis(d1.getTime() - d2.getTime());

        long millis = cal.getTimeInMillis();
        long days = millis / 1000 / 60 / 60 / 24;
        StrictUtilLogger.info(StrictUtilDate.class, "diffByDay - finished");
        return days;
    }

    /**
     * Метод возвращает разницу в часах между двумя датами
     * @return
     */
    public static long diffByHour(Date d1, Date d2){
        StrictUtilLogger.info(StrictUtilDate.class, "diffByHour - started");
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTimeInMillis(d1.getTime() - d2.getTime());

        long millis = cal.getTimeInMillis();
        long hours = millis / 1000 / 60 / 60;
        StrictUtilLogger.info(StrictUtilDate.class, "diffByHour - finished");
        return hours;
    }

    /**
     * Метод возвращает разницу в минутах между двумя датами
     * @return
     */
    public static long diffByMinutes(Date d1, Date d2){
        StrictUtilLogger.info(StrictUtilDate.class, "diffByMinutes - started");
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTimeInMillis(d1.getTime() - d2.getTime());

        long millis = cal.getTimeInMillis();
        long hours = millis / 1000 / 60;
        StrictUtilLogger.info(StrictUtilDate.class, "diffByMinutes - finished");
        return hours;
    }
}
