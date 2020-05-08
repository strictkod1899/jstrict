package ru.strict.validate;

import ru.strict.exceptions.ValidateException;

import java.util.Collection;

/**
 * При валидации выбрасывается ValidateException
 */
public final class Validator {

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

    /**
     * Builder ошибки. Позволяет динамически задавать параметры ошибки
     */
    public static class ValidateError {
        /**
         * Наименование элемента, который не прошел валидацию, для отображения в ошибке валидации
         */
        private String valueName;
        /**
         * Причина, по которой не прйодена валидация
         */
        private String reason;
        /**
         * Значение, которое не прошло валидацию
         */
        private Object value;
        /**
         * Детальное сообщение об ошибке валидации
         */
        private String details;

        private ValidateError() {
        }

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

        public ValidateError isNull(Object value, String caption) {
            if (valueName != null) {
                return this;
            }

            if (value == null) {
                valueName(caption)
                        .reason("value is NULL");
            }
            return this;
        }

        public ValidateError isEmptyOrNull(String value, String caption) {
            if (valueName != null) {
                return this;
            }

            if (ValidateBaseValue.isEmptyOrNull(value)) {
                valueName(caption)
                        .reason("value is EMPTY or NULL");
            }
            return this;
        }

        public ValidateError isEmptySpaceOrNull(String value, String caption) {
            if (valueName != null) {
                return this;
            }

            if (ValidateBaseValue.isEmptySpaceOrNull(value)) {
                valueName(caption)
                        .reason("value is EMPTY SPACE or NULL");
            }
            return this;
        }

        public ValidateError isEmptyOrNull(Collection collection, String caption) {
            if (valueName != null) {
                return this;
            }

            if (ValidateBaseValue.isEmptyOrNull(collection)) {
                valueName(caption)
                        .reason("collection is EMPTY or NULL");
            }
            return this;
        }

        public ValidateError isEmptyOrNull(Object[] array, String caption) {
            if (valueName != null) {
                return this;
            }

            if (ValidateBaseValue.isEmptyOrNull(array)) {
                valueName(caption)
                        .reason("array is EMPTY or NULL");
            }
            return this;
        }

        public ValidateError isMinValue(long number, String caption, long minValue) {
            if (valueName != null) {
                return this;
            }

            if (number < minValue) {
                valueName(caption)
                        .reason(String.format("number (%s) < %s", number, minValue));
            }
            return this;
        }

        public ValidateError isMinValue(double number, String caption, double minValue) {
            if (valueName != null) {
                return this;
            }

            if (number < minValue) {
                valueName(caption)
                        .reason(String.format("number (%s) < %s", number, minValue));
            }
            return this;
        }

        public ValidateError isMaxValue(long number, String caption, long maxValue) {
            if (valueName != null) {
                return this;
            }

            if (number > maxValue) {
                valueName(caption)
                        .reason(String.format("number (%s) > %s", number, maxValue));
            }
            return this;
        }

        public ValidateError isMaxValue(double number, String caption, double maxValue) {
            if (valueName != null) {
                return this;
            }

            if (number > maxValue) {
                valueName(caption)
                        .reason(String.format("number (%s) > %s", number, maxValue));
            }
            return this;
        }

        public ValidateError isNotRange(long number, String caption, long minValue, long maxValue) {
            if (valueName != null) {
                return this;
            }

            if (!ValidateBaseValue.isRange(number, minValue, maxValue)) {
                valueName(caption)
                        .reason(String.format("number (%s) < %s or > %s", number, minValue, maxValue));
            }
            return this;
        }
    }
}
