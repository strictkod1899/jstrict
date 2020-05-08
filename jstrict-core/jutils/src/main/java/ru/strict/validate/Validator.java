package ru.strict.validate;

import java.util.Collection;

/**
 * При валидации выбрасывается ValidateException
 */
public final class Validator {

    public static ValidateError and() {
        return new ValidateError(true);
    }

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

    public static ValidateError isEmptyOrNull(Collection collection, String caption) {
        ValidateError error = new ValidateError();
        return error.isEmptyOrNull(collection, caption);
    }

    public static ValidateError isEmptyOrNull(Object[] array, String caption) {
        ValidateError error = new ValidateError();
        return error.isEmptyOrNull(array, caption);
    }

    public static ValidateError isMinValue(long number, String caption, long minValue) {
        ValidateError error = new ValidateError();
        return error.isMinValue(number, caption, minValue);
    }

    public static ValidateError isMinValue(double number, String caption, double minValue) {
        ValidateError error = new ValidateError();
        return error.isMinValue(number, caption, minValue);
    }

    public static ValidateError isMaxValue(long number, String caption, long maxValue) {
        ValidateError error = new ValidateError();
        return error.isMaxValue(number, caption, maxValue);
    }

    public static ValidateError isMaxValue(double number, String caption, double maxValue) {
        ValidateError error = new ValidateError();
        return error.isMaxValue(number, caption, maxValue);
    }

    public static ValidateError isNotRange(long number, String caption, long minValue, long maxValue) {
        ValidateError error = new ValidateError();
        return error.isNotRange(number, caption, minValue, maxValue);
    }
}
