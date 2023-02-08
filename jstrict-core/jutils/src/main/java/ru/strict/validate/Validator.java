package ru.strict.validate;

import java.util.Collection;

/**
 * @deprecated use CommonValidator
 */
public final class Validator {

    private Validator() {
    }

    public static void isNull(Object value, String caption) {
        if (value == null) {
            throw new ItemValidateException(caption, "value is NULL");
        }
    }

    public static void isNullOrEmpty(String value, String caption) {
        if (CommonValidator.isNullOrEmpty(value)) {
            throw new ItemValidateException(caption, "value is EMPTY or NULL");
        }
    }

    public static void isEmptySpaceOrNull(String value, String caption) {
        if (CommonValidator.isEmptySpaceOrNull(value)) {
            throw new ItemValidateException(caption, "value is EMPTY SPACE or NULL");
        }
    }

    public static void isNullOrEmpty(Collection<?> collection, String caption) {
        if (CommonValidator.isNullOrEmpty(collection)) {
            throw new ItemValidateException(caption, "collection is EMPTY or NULL");
        }
    }

    public static void isNullOrEmpty(Object[] array, String caption) {
        if (CommonValidator.isNullOrEmpty(array)) {
            throw new ItemValidateException(caption, "array is EMPTY or NULL");
        }
    }

    public static void isLess(long number, String caption, long minValue) {
        if (number < minValue) {
            throw new ItemValidateException(caption, String.format("number (%s) < %s", number, minValue));
        }
    }

    public static void isLess(double number, String caption, double minValue) {
        if (number < minValue) {
            throw new ItemValidateException(caption, String.format("number (%s) < %s", number, minValue));
        }
    }

    public static void isLessOrEquals(double number, String caption, double minValue) {
        if (number <= minValue) {
            throw new ItemValidateException(caption, String.format("number (%s) <= %s", number, minValue));
        }
    }

    public static void isLessOrEquals(long number, String caption, long minValue) {
        if (number <= minValue) {
            throw new ItemValidateException(caption, String.format("number (%s) <= %s", number, minValue));
        }
    }

    public static void isMore(long number, String caption, long maxValue) {
        if (number > maxValue) {
            throw new ItemValidateException(caption, String.format("number (%s) > %s", number, maxValue));
        }
    }

    public static void isMore(double number, String caption, double maxValue) {
        if (number > maxValue) {
            throw new ItemValidateException(caption, String.format("number (%s) > %s", number, maxValue));
        }
    }

    public static void isMoreOrEquals(long number, String caption, long maxValue) {
        if (number >= maxValue) {
            throw new ItemValidateException(caption, String.format("number (%s) >= %s", number, maxValue));
        }
    }

    public static void isMoreOrEquals(double number, String caption, double maxValue) {
        if (number >= maxValue) {
            throw new ItemValidateException(caption, String.format("number (%s) >= %s", number, maxValue));
        }
    }

    public static void isRange(long number, String caption, long minValue, long maxValue) {
        if (CommonValidator.isRange(number, minValue, maxValue)) {
            throw new ItemValidateException(caption, String.format("number (%s) is in range (%s...%s)",
                    number,
                    minValue,
                    maxValue)
            );
        }
    }

    public static void isNotRange(long number, String caption, long minValue, long maxValue) {
        if (!CommonValidator.isRange(number, minValue, maxValue)) {
            throw new ItemValidateException(caption, String.format("number (%s) isn't in range (%s...%s)",
                    number,
                    minValue,
                    maxValue)
            );
        }
    }
}
