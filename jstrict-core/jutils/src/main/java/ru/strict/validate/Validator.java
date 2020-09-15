package ru.strict.validate;

import ru.strict.exceptions.ValidateException;

import java.util.Collection;

/**
 * Валидатор. При валидации выбрасывается ValidateException
 */
public final class Validator {

    private Validator() {}

    public static DetailsValidator byDetails(String details, Object...args) {
        return new DetailsValidator(details, args);
    }

    public static void isNull(Object value, String caption) {
        if (value == null) {
            throw new ValidateException(caption, "value is NULL");
        }
    }

    public static void isEmptyOrNull(String value, String caption) {
        if (CommonValidate.isEmptyOrNull(value)) {
            throw new ValidateException(caption, "value is EMPTY or NULL");
        }
    }

    public static void isEmptySpaceOrNull(String value, String caption) {
        if (CommonValidate.isEmptySpaceOrNull(value)) {
            throw new ValidateException(caption, "value is EMPTY SPACE or NULL");
        }
    }

    public static void isEmptyOrNull(Collection<?> collection, String caption) {
        if (CommonValidate.isEmptyOrNull(collection)) {
            throw new ValidateException(caption, "collection is EMPTY or NULL");
        }
    }

    public static void isEmptyOrNull(Object[] array, String caption) {
        if (CommonValidate.isEmptyOrNull(array)) {
            throw new ValidateException(caption, "array is EMPTY or NULL");
        }
    }

    public static void isLess(long number, String caption, long minValue) {
        if ( number < minValue) {
            throw new ValidateException(caption, String.format("number (%s) < %s", number, minValue));
        }
    }

    public static void isLess(double number, String caption, double minValue) {
        if (number < minValue) {
            throw new ValidateException(caption, String.format("number (%s) < %s", number, minValue));
        }
    }

    public static void isLessOrEquals(double number, String caption, double minValue) {
        if (number <= minValue) {
            throw new ValidateException(caption, String.format("number (%s) <= %s", number, minValue));
        }
    }

    public static void isLessOrEquals(long number, String caption, long minValue) {
        if (number <= minValue) {
            throw new ValidateException(caption, String.format("number (%s) <= %s", number, minValue));
        }
    }

    public static void isMore(long number, String caption, long maxValue) {
        if (number > maxValue) {
            throw new ValidateException(caption, String.format("number (%s) > %s", number, maxValue));
        }
    }

    public static void isMore(double number, String caption, double maxValue) {
        if (number > maxValue) {
            throw new ValidateException(caption, String.format("number (%s) > %s", number, maxValue));
        }
    }

    public static void isMoreOrEquals(long number, String caption, long maxValue) {
        if (number >= maxValue) {
            throw new ValidateException(caption, String.format("number (%s) >= %s", number, maxValue));
        }
    }

    public static void isMoreOrEquals(double number, String caption, double maxValue) {
        if (number >= maxValue) {
            throw new ValidateException(caption, String.format("number (%s) >= %s", number, maxValue));
        }
    }

    public static void isRange(long number, String caption, long minValue, long maxValue) {
        if (CommonValidate.isRange(number, minValue, maxValue)) {
            throw new ValidateException(caption, String.format("number (%s) is in range (%s...%s)",
                    number,
                    minValue,
                    maxValue)
            );
        }
    }

    public static void isNotRange(long number, String caption, long minValue, long maxValue) {
        if (!CommonValidate.isRange(number, minValue, maxValue)) {
            throw new ValidateException(caption, String.format("number (%s) isn't in range (%s...%s)",
                    number,
                    minValue,
                    maxValue)
            );
        }
    }
}
