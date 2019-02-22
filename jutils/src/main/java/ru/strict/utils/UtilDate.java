package ru.strict.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Управление датами
 */
public class UtilDate {

    /**
     * Получить дату из строки формата dd.MM.yyyy
     */
    public static Date getDateFromString1(String dateString){
        return getDateFromStringProcess(dateString, "dd.MM.yyyy");
    }

    /**
     * Получить дату из строки формата dd/MM/yyyy
     */
    public static Date getDateFromString2(String dateString){
        return getDateFromStringProcess(dateString, "dd/MM/yyyy");
    }

    /**
     * Получить дату из строки формата dd.MM.yyyy HH:mm:ss
     */
    public static Date getDateFromString3(String dateString){
        return getDateFromStringProcess(dateString, "dd.MM.yyyy HH:mm:ss");
    }

    /**
     * Получить дату из строки формата dd/MM/yyyy HH:mm:ss
     */
    public static Date getDateFromString4(String dateString){
        return getDateFromStringProcess(dateString, "dd/MM/yyyy HH:mm:ss");
    }

    private static Date getDateFromStringProcess(String dateString, String pattern){
        Date result = null;
        if(dateString != null) {
            DateFormat formatter = new SimpleDateFormat(pattern);
            try {
                result = formatter.parse(dateString);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }

        return result;
    }

    /**
     * Преобразовать дату в строку формата dd.MM.yyyy
     */
    public static String formatDate1(Date date){
        String result = null;
        if(date != null) {
            DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            result = formatter.format(date);
        }

        return result;
    }

    /**
     * Преобразовать дату в строку формата dd/MM/yyyy
     */
    public static String formatDate2(Date date){
        String result = null;
        if(date != null) {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            result = formatter.format(date);
        }

        return result;
    }

    /**
     * Преобразовать дату в строку формата dd_MM_yyyy
     */
    public static String formatDate3(Date date){
        String result = null;
        if(date != null) {
            DateFormat formatter = new SimpleDateFormat("dd_MM_yyyy");
            result = formatter.format(date);
        }

        return result;
    }

    /**
     * Преобразовать дату в строку формата dd.MM.yyyy HH:mm:ss
     */
    public static String formatDateTime1(Date date){
        String result = null;
        if(date != null) {
            DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            result = formatter.format(date);
        }

        return result;
    }

    /**
     * Преобразовать дату в строку формата dd/MM/yyyy HH:mm:ss
     */
    public static String formatDateTime2(Date date){
        String result = null;
        if(date != null) {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            result = formatter.format(date);
        }

        return result;
    }

    public static Date getDateWithoutTime(Date date){
        Date result = null;
        if(date != null) {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            try {
                result = formatter.parse(formatter.format(date));
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
        return result;
    }

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
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTimeInMillis(d1.getTime() - d2.getTime());

        long millis = cal.getTimeInMillis();
        long days = millis / 1000 / 60 / 60 / 24 / 30 / 12;
        return days;
    }

    /**
     * Метод возвращает разницу в месяцах между двумя датами
     * @return
     */
    public static long diffByMonth(Date d1, Date d2){
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTimeInMillis(d1.getTime() - d2.getTime());

        long millis = cal.getTimeInMillis();
        long days = millis / 1000 / 60 / 60 / 24 / 30;
        return days;
    }

    /**
     * Метод возвращает разницу в днях между двумя датами
     * @return
     */
    public static long diffByDay(Date d1, Date d2){
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTimeInMillis(d1.getTime() - d2.getTime());

        long millis = cal.getTimeInMillis();
        long days = millis / 1000 / 60 / 60 / 24;
        return days;
    }

    /**
     * Метод возвращает разницу в часах между двумя датами
     * @return
     */
    public static long diffByHour(Date d1, Date d2){
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTimeInMillis(d1.getTime() - d2.getTime());

        long millis = cal.getTimeInMillis();
        long hours = millis / 1000 / 60 / 60;
        return hours;
    }

    /**
     * Метод возвращает разницу в минутах между двумя датами
     * @return
     */
    public static long diffByMinutes(Date d1, Date d2){
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTimeInMillis(d1.getTime() - d2.getTime());

        long millis = cal.getTimeInMillis();
        long hours = millis / 1000 / 60;
        return hours;
    }

    public static Date addDaysToDate(Date date, int countDaysToAdd){
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTimeInMillis(date.getTime());
        cal.add(Calendar.DAY_OF_YEAR, countDaysToAdd);

        return cal.getTime();
    }

    public static Date addMonthsToDate(Date date, int countMonthsToAdd){
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTimeInMillis(date.getTime());
        cal.add(Calendar.MONTH, countMonthsToAdd);

        return cal.getTime();
    }

    public static Date addYearsToDate(Date date, int countYearsToAdd){
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTimeInMillis(date.getTime());
        cal.add(Calendar.YEAR, countYearsToAdd);

        return cal.getTime();
    }
}
