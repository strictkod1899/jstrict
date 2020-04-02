package ru.strict.validate;

import ru.strict.exceptions.ValidateException;

import java.util.Collection;

/**
 * При валидации выбрасывается ValidateException
 */
public class Validator {

    public static ValidateError isNull(Object value, String caption){
        ValidateError error = new ValidateError();
        if (value == null) {
            error.valueName(caption)
                    .reason("value is NULL");
        }
        return error;
    }

    public static ValidateError isEmptyOrNull(String value, String caption){
        ValidateError error = new ValidateError();
        if (ValidateBaseValue.isEmptyOrNull(value)) {
            error.valueName(caption)
                    .reason("value is EMPTY or NULL");
        }
        return error;
    }

    public static ValidateError isEmptySpaceOrNull(String value, String caption){
        ValidateError error = new ValidateError();
        if (ValidateBaseValue.isEmptySpaceOrNull(value)) {
            error.valueName(caption)
                    .reason("value is EMPTY SPACE or NULL");
        }
        return error;
    }

    public static ValidateError isEmptyOrNull(Collection collection, String caption){
        ValidateError error = new ValidateError();
        if (ValidateBaseValue.isEmptyOrNull(collection)) {
            error.valueName(caption)
                    .reason("collection is EMPTY or NULL");
        }
        return error;
    }

    public static ValidateError isEmptyOrNull(Object[] array, String caption){
        ValidateError error = new ValidateError();
        if (ValidateBaseValue.isEmptyOrNull(array)) {
            error.valueName(caption)
                    .reason("array is EMPTY or NULL");
        }
        return error;
    }

    public static ValidateError isMinValue(long number, String caption, long minValue){
        ValidateError error = new ValidateError();
        if (number < minValue) {
            error.valueName(caption)
                    .reason(String.format("number (%s) < %s", number, minValue));
        }
        return error;
    }

    public static ValidateError isMinValue(double number, String caption, double minValue){
        ValidateError error = new ValidateError();
        if (number < minValue) {
            error.valueName(caption)
                    .reason(String.format("number (%s) < %s", number, minValue));
        }
        return error;
    }

    public static ValidateError isMaxValue(long number, String caption, long maxValue){
        ValidateError error = new ValidateError();
        if (number > maxValue) {
            error.valueName(caption)
                    .reason(String.format("number (%s) > %s", number, maxValue));
        }
        return error;
    }

    public static ValidateError isMaxValue(double number, String caption, double maxValue){
        ValidateError error = new ValidateError();
        if (number > maxValue) {
            error.valueName(caption)
                    .reason(String.format("number (%s) > %s", number, maxValue));
        }
        return error;
    }

    public static ValidateError isNotRange(long number, String caption, long minValue, long maxValue){
        ValidateError error = new ValidateError();
        if (!ValidateBaseValue.isRange(number, minValue, maxValue)) {
            error.valueName(caption)
                    .reason(String.format("number (%s) < %s or > %s", number, minValue, maxValue));
        }
        return error;
    }

    /**
     * Builder ошибки. Позволяет динамически задавать параметры ошибки
     */
    public static class ValidateError {
        private String valueName;
        private String reason;
        private Object value;
        private String details;

        private ValidateError(){}

        public ValidateError valueName(String valueName) {
            this.valueName = valueName;
            return this;
        }

        public ValidateError reason(String reason) {
            this.reason = reason;
            return this;
        }

        public ValidateError value(Object value) {
            this.value = value;
            return this;
        }

        public ValidateError details(String details) {
            this.details = details;
            return this;
        }

        /**
         * @throws ValidateException
         */
        public void onThrow() {
            if (!ValidateBaseValue.isEmptyOrNull(valueName)) {
                if (reason == null && value == null && details == null) {
                    throw new ValidateException(valueName);
                } else if (value != null && details != null) {
                    throw new ValidateException(valueName, reason, value, details);
                } else if (value != null) {
                    throw new ValidateException(valueName, reason, value);
                } else if (details != null) {
                    throw new ValidateException(valueName, reason, details);
                } else {
                    throw new ValidateException(valueName, reason, value, details);
                }
            }
        }
    }
}
