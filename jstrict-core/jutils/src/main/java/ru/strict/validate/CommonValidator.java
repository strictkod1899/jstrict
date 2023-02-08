package ru.strict.validate;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonValidator {

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isEmptySpaceOrNull(String str) {
        if (str == null) {
            return true;
        }

        return isNullOrEmpty(str.trim());
    }

    public static boolean isNullOrEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNullOrEmpty(Object[] array) {
        if (array == null || array.length == 0) {
            return true;
        }

        var result = true;
        for (var item : array) {
            if (item != null) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static boolean isMinLength(String str, int minLength) {
        return str != null && str.length() >= minLength;
    }

    public static boolean isMaxLength(String str, int maxLength) {
        return str != null && str.length() <= maxLength;
    }

    public static boolean isRangeLength(String str, int minLength, int maxLength) {
        return str != null && str.length() >= minLength && str.length() <= maxLength;
    }

    public static boolean isRange(int number, int minValue, int maxValue) {
        return number > minValue && number < maxValue;
    }

    public static boolean isRange(long number, long minValue, long maxValue) {
        return number > minValue && number < maxValue;
    }

    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }

        var pattern = Pattern.compile("[+-]?\\d+");
        var match = pattern.matcher(str);
        return match.matches();
    }

    public static boolean isDouble(String str) {
        if (str == null) {
            return false;
        }

        var pattern = Pattern.compile("([+-]?\\d+\\.?\\d*)");
        var match = pattern.matcher(str);
        return match.matches();
    }

    /**
     * Проверка даты в формате YYYY-MM-DD
     *
     * @param str Проверяемая строка
     * @param splitSymbol Разделяемый символ (например YYYY-MM-DD, YYYY/MM/DD)
     */
    public static boolean isDateStartYear(String str, char splitSymbol) {
        if (str == null) {
            return false;
        }

        var pattern = Pattern.compile(
                "[0-9]{4}[" + splitSymbol + "](0[1-9]|1[012])[" + splitSymbol + "](0[1-9]|[12][0-9]|3[01])");
        var match = pattern.matcher(str);
        return match.matches();
    }

    /**
     * Проверка даты в формате DD-MM-YYYY
     *
     * @param str Проверяемая строка
     * @param splitSymbol Разделяемый символ (например DD/MM/YYYY, DD-MM-YYYY)
     */
    public static boolean isDateStartDay(String str, char splitSymbol) {
        if (str == null) {
            return false;
        }

        var pattern = Pattern.compile(
                "(0[1-9]|[12][0-9]|3[01])[" + splitSymbol + "](0[1-9]|1[012])[" + splitSymbol + "][0-9]{4}");
        var match = pattern.matcher(str);
        return match.matches();
    }

    /**
     * Проверка времени в формате HH:MM:SS
     *
     * @param str Проверяемая строка
     * @param splitSymbol Разделяемый символ (например HH:MM:SS, HH-MM-SS)
     */
    public static boolean isTime(String str, char splitSymbol) {
        if (str == null) {
            return false;
        }

        var pattern = Pattern.compile("^([0-1]\\d|2[0-3])(" + splitSymbol + "[0-5]\\d){2}$");
        var match = pattern.matcher(str);
        return match.matches();
    }
}
