package ru.strict.validate;

import java.util.Collection;

/**
 * Валидатор. При валидации выбрасывается ValidateException
 */
public final class Validator {

    private Validator() {}

    public static ValidateError isNull(Object value, String caption) {
        ValidateError error = new ValidateError();
        return error.isNull(value, caption);
    }

    public static ValidateError isEmptyOrNull(String value, String caption) {
        ValidateError error = new ValidateError();
        return error.isEmptyOrNull(value, caption);
    }

    public static ValidateError isEmptySpaceOrNull(String value, String caption) {
        ValidateError error = new ValidateError();
        return error.isEmptySpaceOrNull(value, caption);
    }

    public static ValidateError isEmptyOrNull(Collection<?> collection, String caption) {
        ValidateError error = new ValidateError();
        return error.isEmptyOrNull(collection, caption);
    }

    public static ValidateError isEmptyOrNull(Object[] array, String caption) {
        ValidateError error = new ValidateError();
        return error.isEmptyOrNull(array, caption);
    }

    public static ValidateError isLess(long number, String caption, long minValue) {
        ValidateError error = new ValidateError();
        return error.isLess(number, caption, minValue);
    }

    public static ValidateError isLess(double number, String caption, double minValue) {
        ValidateError error = new ValidateError();
        return error.isLess(number, caption, minValue);
    }

    public static ValidateError isLessOrEquals(double number, String caption, double minValue) {
        ValidateError error = new ValidateError();
        return error.isLessOrEquals(number, caption, minValue);
    }

    public static ValidateError isLessOrEquals(long number, String caption, long minValue) {
        ValidateError error = new ValidateError();
        return error.isLessOrEquals(number, caption, minValue);
    }

    public static ValidateError isMore(long number, String caption, long maxValue) {
        ValidateError error = new ValidateError();
        return error.isMore(number, caption, maxValue);
    }

    public static ValidateError isMore(double number, String caption, double maxValue) {
        ValidateError error = new ValidateError();
        return error.isMore(number, caption, maxValue);
    }

    public static ValidateError isMoreOrEquals(long number, String caption, long maxValue) {
        ValidateError error = new ValidateError();
        return error.isMoreOrEquals(number, caption, maxValue);
    }

    public static ValidateError isMoreOrEquals(double number, String caption, double maxValue) {
        ValidateError error = new ValidateError();
        return error.isMoreOrEquals(number, caption, maxValue);
    }

    public static ValidateError isNotRange(long number, String caption, long minValue, long maxValue) {
        ValidateError error = new ValidateError();
        return error.isNotRange(number, caption, minValue, maxValue);
    }
}
