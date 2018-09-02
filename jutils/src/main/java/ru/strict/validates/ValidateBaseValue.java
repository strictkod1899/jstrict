package ru.strict.validates;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Проверка валидности значений стандартных типов (строка, целые числа, дробные числа и т.д.)
 */
public class ValidateBaseValue {

    /**
     * Проверка строки, чтобы она была не пустой и не равно нулю
     *
     * @param str Проверяемая строка
     * @return Если строка не содержит пустых символов и является корректной, то возвращается true, иначе false
     */
    public static boolean isNotEmptyOrNull(String str) {
        boolean result = false;

        if (str != null) {
            Pattern pattern = Pattern.compile("\\S");
            Matcher match = pattern.matcher(str);
            if(match.find()){
                result = true;
            }
        }

        return result;
    }

    public static boolean isMinLength(String str, int minLength){
        boolean result = false;
        if(str.length() >= minLength){
            result = true;
        }
        return result;
    }

    public static boolean isMaxLength(String str, int maxLength){
        boolean result = false;
        if(str.length() <= maxLength){
            result = true;
        }
        return result;
    }

    public static boolean isRangeLength(String str, int minLength, int maxLength){
        boolean result = false;
        if(str.length() >= minLength && str.length() <= maxLength){
            result = true;
        }
        return result;
    }

    /**
     * Проверка корректности приведения строки к целочисленному типу
     *
     * @param str Проверяемая строка
     * @return Если текстовое поле содержит корректные данные, то возвращается true, иначе false
     */
    public static boolean isInteger(String str) {
        boolean result = false;

        if (str != null) {
            Pattern pattern = Pattern.compile("[+-]?\\d+");
            Matcher match = pattern.matcher(str);
            result = match.matches();
        }

        return result;
    }

    /**
     * Проверка корректности приведения строки к дробному числовому типу
     *
     * @param str Проверяемая строка
     * @return Если текстовое поле содержит корректные данные, то возвращается true, иначе false
     */
    public static boolean isDouble(String str) {
        boolean result = false;

        if (str != null) {
            Pattern pattern = Pattern.compile("([+-]?\\d+\\.?\\d*)");
            Matcher match = pattern.matcher(str);
            result = match.matches();
        }

        return result;
    }

    /**
     * Проверка даты в формате YYYY-MM-DD
     *
     * @param str Проверяемая строка
     * @param splitSymbol Разделяемый символ (например YYYY-MM-DD, YYYY/MM/DD)
     * @return
     */
    public static boolean isDateYear(String str, char splitSymbol) {
        boolean result = false;

        if (str != null) {
            Pattern pattern = Pattern.compile("(19|20)\\d\\d[" + splitSymbol + " /.](0[1-9]|1[012])[" + splitSymbol + " /.](0[1-9]|[12][0-9]|3[01])");
            Matcher match = pattern.matcher(str);
            result = match.matches();
        }

        return result;
    }

    /**
     * Проверка даты в формате DD/MM/YYYY
     *
     * @param str Проверяемая строка
     * @param splitSymbol Разделяемый символ (например DD/MM/YYYY, DD-MM-YYYY)
     * @return
     */
    public static boolean isDateDay(String str, char splitSymbol) {
        boolean result = false;

        if (str != null) {
            Pattern pattern = Pattern.compile("(0[1-9]|[12][0-9]|3[01])[" + splitSymbol + " /.](0[1-9]|1[012])[" + splitSymbol + " /.](19|20)\\d\\d");
            Matcher match = pattern.matcher(str);
            result = match.matches();
        }

        return result;
    }

    /**
     * Проверка времени в формате HH:MM:SS
     *
     * @param str Проверяемая строка
     * @return
     */
    public static boolean isTime(String str) {
        boolean result = false;

        if (str != null) {
            Pattern pattern = Pattern.compile("^([0-1]\\d|2[0-3])(:[0-5]\\d){2}$");
            Matcher match = pattern.matcher(str);
            result = match.matches();
        }

        return result;
    }
}
