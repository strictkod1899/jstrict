package ru.strict.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Управление датами
 */
public class UtilDate {

    /**
     * Получить текущее число месяца
     * @return
     */
    public static int getCurrentDay(){
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault(),
                Locale.getDefault());
        calendar.setTime(new Date());
        int result = calendar.get(Calendar.DAY_OF_MONTH);
        return result;
    }

    /**
     * Получить текущий месяц
     * @return
     */
    public static int getCurrentMonth(){
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault(),
                Locale.getDefault());
        calendar.setTime(new Date());
        int result = calendar.get(Calendar.MONTH);
        return result;
    }

    /**
     * Получить текущеий год
     * @return
     */
    public static int getCurrentYear(){
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault(),
                Locale.getDefault());
        calendar.setTime(new Date());
        int result = calendar.get(Calendar.YEAR);
        return result;
    }

    /**
     * Метод возвращает разницу в годах между двумя датами
     * @return
     */
    public static long diffByYear(Date d1, Date d2){
        UtilLogger.info(UtilDate.class, "diffByYear - started");
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTimeInMillis(d1.getTime() - d2.getTime());

        long millis = cal.getTimeInMillis();
        long days = millis / 1000 / 60 / 60 / 24 / 30 / 12;
        UtilLogger.info(UtilDate.class, "diffByYear - finished");
        return days;
    }

    /**
     * Метод возвращает разницу в месяцах между двумя датами
     * @return
     */
    public static long diffByMonth(Date d1, Date d2){
        UtilLogger.info(UtilDate.class, "diffByMonth - started");
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTimeInMillis(d1.getTime() - d2.getTime());

        long millis = cal.getTimeInMillis();
        long days = millis / 1000 / 60 / 60 / 24 / 30;
        UtilLogger.info(UtilDate.class, "diffByMonth - finished");
        return days;
    }

    /**
     * Метод возвращает разницу в днях между двумя датами
     * @return
     */
    public static long diffByDay(Date d1, Date d2){
        UtilLogger.info(UtilDate.class, "diffByDay - started");
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTimeInMillis(d1.getTime() - d2.getTime());

        long millis = cal.getTimeInMillis();
        long days = millis / 1000 / 60 / 60 / 24;
        UtilLogger.info(UtilDate.class, "diffByDay - finished");
        return days;
    }

    /**
     * Метод возвращает разницу в часах между двумя датами
     * @return
     */
    public static long diffByHour(Date d1, Date d2){
        UtilLogger.info(UtilDate.class, "diffByHour - started");
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTimeInMillis(d1.getTime() - d2.getTime());

        long millis = cal.getTimeInMillis();
        long hours = millis / 1000 / 60 / 60;
        UtilLogger.info(UtilDate.class, "diffByHour - finished");
        return hours;
    }

    /**
     * Метод возвращает разницу в минутах между двумя датами
     * @return
     */
    public static long diffByMinutes(Date d1, Date d2){
        UtilLogger.info(UtilDate.class, "diffByMinutes - started");
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTimeInMillis(d1.getTime() - d2.getTime());

        long millis = cal.getTimeInMillis();
        long hours = millis / 1000 / 60;
        UtilLogger.info(UtilDate.class, "diffByMinutes - finished");
        return hours;
    }
}
