package ru.strict.validate;

import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class CommonValidator {
    
    public void throwIfNull(Object value, String caption) {
        if (value == null) {
            throw new ItemValidateException(caption, "is null");
        }
    }

    public void throwIfNullOrEmpty(String value, String caption) {
        if (CommonValidator.isNullOrEmpty(value)) {
            throw new ItemValidateException(caption, "is empty or null");
        }
    }

    public void throwIfNullOrEmpty(Collection<?> collection, String caption) {
        if (CommonValidator.isNullOrEmpty(collection)) {
            throw new ItemValidateException(caption, "is empty or null");
        }
    }

    public void throwIfNullOrEmpty(Object[] array, String caption) {
        if (CommonValidator.isNullOrEmpty(array)) {
            throw new ItemValidateException(caption, "is empty or null");
        }
    }
    
    public boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public boolean isEmptySpaceOrNull(String str) {
        if (str == null) {
            return true;
        }

        return isNullOrEmpty(str.trim());
    }

    public boolean isNullOrEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public boolean isNullOrEmpty(Object[] array) {
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

    public boolean isMinLength(String str, int minLength) {
        return str != null && str.length() >= minLength;
    }

    public boolean isMaxLength(String str, int maxLength) {
        return str != null && str.length() <= maxLength;
    }

    public boolean isRangeLength(String str, int minLength, int maxLength) {
        return str != null && str.length() >= minLength && str.length() <= maxLength;
    }

    public boolean isRange(int number, int minValue, int maxValue) {
        return number > minValue && number < maxValue;
    }

    public boolean isRange(long number, long minValue, long maxValue) {
        return number > minValue && number < maxValue;
    }

    public boolean isInteger(String str) {
        if (str == null) {
            return false;
        }

        var pattern = Pattern.compile("[+-]?\\d+");
        var match = pattern.matcher(str);
        return match.matches();
    }

    public boolean isDouble(String str) {
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
    public boolean isDateStartYear(String str, char splitSymbol) {
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
    public boolean isDateStartDay(String str, char splitSymbol) {
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
    public boolean isTime(String str, char splitSymbol) {
        if (str == null) {
            return false;
        }

        var pattern = Pattern.compile("^([0-1]\\d|2[0-3])(" + splitSymbol + "[0-5]\\d){2}$");
        var match = pattern.matcher(str);
        return match.matches();
    }
}
