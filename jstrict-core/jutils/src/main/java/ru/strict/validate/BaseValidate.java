package ru.strict.validate;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Проверка валидности значений стандартных типов (строка, целые числа, дробные числа и т.д.)
 */
public class BaseValidate {

    /**
     * Проверка строки, чтобы она была не пустой и не равна нулю
     *
     * @param str Проверяемая строка
     * @return Если строка не содержит пустых символов и является корректной, то возвращается true, иначе false
     */
    public static boolean isEmptyOrNull(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * Проверка строки, чтобы она была не пустой, не равна нулю и не содержала одних пробелов
     *
     * @param str Проверяемая строка
     * @return Если строка не содержит пустых символов и является корректной, то возвращается true, иначе false
     */
    public static boolean isEmptySpaceOrNull(String str) {
        if(str == null){
            return true;
        }

        return isEmptyOrNull(str.trim());
    }

    public static boolean isEmptyOrNull(Collection<?> collection){
        boolean result = false;
        if (collection == null || collection.isEmpty()) {
            result = true;
        }

        return result;
    }

    public static boolean isEmptyOrNull(Object[] array){
        boolean result = false;
        if (array == null || array.length == 0) {
            result = true;
        }

        return result;
    }

    public static boolean isMinLength(String str, int minLength){
        boolean result = false;
        if(str != null && str.length() >= minLength){
            result = true;
        }
        return result;
    }

    public static boolean isMaxLength(String str, int maxLength){
        boolean result = false;
        if(str != null && str.length() <= maxLength){
            result = true;
        }
        return result;
    }

    public static boolean isRangeLength(String str, int minLength, int maxLength){
        boolean result = false;
        if(str != null && str.length() >= minLength && str.length() <= maxLength){
            result = true;
        }
        return result;
    }

    public static boolean isRange(int number, int minValue, int maxValue){
        boolean result = true;
        if(number < minValue || number > maxValue){
            result = false;
        }
        return result;
    }

    public static boolean isRange(long number, long minValue, long maxValue){
        boolean result = true;
        if(number < minValue || number > maxValue){
            result = false;
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
     */
    public static boolean isDateStartYear(String str, char splitSymbol) {
        boolean result = false;

        if (str != null) {
            Pattern pattern = Pattern.compile("[0-9]{4}["+splitSymbol+"](0[1-9]|1[012])["+splitSymbol+"](0[1-9]|[12][0-9]|3[01])");
            Matcher match = pattern.matcher(str);
            result = match.matches();
        }

        return result;
    }

    /**
     * Проверка даты в формате DD-MM-YYYY
     *
     * @param str Проверяемая строка
     * @param splitSymbol Разделяемый символ (например DD/MM/YYYY, DD-MM-YYYY)
     */
    public static boolean isDateStartDay(String str, char splitSymbol) {
        boolean result = false;

        if (str != null) {
            Pattern pattern = Pattern.compile("(0[1-9]|[12][0-9]|3[01])["+splitSymbol+"](0[1-9]|1[012])["+splitSymbol+"][0-9]{4}");
            Matcher match = pattern.matcher(str);
            result = match.matches();
        }

        return result;
    }

    /**
     * Проверка времени в формате HH:MM:SS
     *
     * @param str Проверяемая строка
     * @param splitSymbol Разделяемый символ (например HH:MM:SS, HH-MM-SS)
     */
    public static boolean isTime(String str, char splitSymbol) {
        boolean result = false;

        if (str != null) {
            Pattern pattern = Pattern.compile("^([0-1]\\d|2[0-3])("+splitSymbol+"[0-5]\\d){2}$");
            Matcher match = pattern.matcher(str);
            result = match.matches();
        }

        return result;
    }
}
