package ru.strict.validate;

import ru.strict.exceptions.ValidateException;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * Builder ошибки, который непосредственно выкидываешь Exception. Позволяет динамически задавать параметры ошибки
 */
public class ValidateError {
    /**
     * Наименование элемента (для отображения в ошибке валидации), который не прошел валидацию
     */
    private String item;
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
    private String detailsMessage;
    /**
     * Аргументы для посдтановки в String.format при использовании {@link #detailsMessage}
     */
    private Object[] detailsArgs;

    private boolean and;
    private int currentOrdinal;
    /**
     * Если какие-либо условия (например, {@link #and}) были не выполнены, тогда дальнейший процесс валидации блокируется
     */
    private Integer failOrdinal;

    ValidateError() {}

    public ValidateError reason(String reason) {
        if (!checkFail() || (failOrdinal != null && currentOrdinal == failOrdinal)) {
            this.reason = reason;
        }
        return this;
    }

    public ValidateError value(Object value) {
        if (!checkFail() || (failOrdinal != null && currentOrdinal == failOrdinal)) {
            this.value = value;
        }
        return this;
    }

    public ValidateError details(String detailsMessage, Object... detailsArgs) {
        if (!checkFail() || (failOrdinal != null && currentOrdinal == failOrdinal)) {
            this.detailsMessage = detailsMessage;
            this.detailsArgs = detailsArgs;
        }
        return this;
    }

    public ValidateError and() {
        this.and = true;
        return this;
    }

    /**
     * @throws ValidateException
     */
    public void onThrow() {
        if (failOrdinal != null) {
            return;
        }

        String detailsMessage = this.detailsMessage == null ? null : String.format(this.detailsMessage, this.detailsArgs);

        if (reason == null) {
            throw new ValidateException(item);
        } else {
            if (value != null) {
                if (detailsMessage != null) {
                    throw new ValidateException(item, reason, value, detailsMessage);
                } else {
                    throw new ValidateException(item, reason, value);
                }
            } else if (detailsMessage != null) {
                throw new ValidateException(item, reason, detailsMessage);
            } else {
                throw new ValidateException(item, reason, value, detailsMessage);
            }
        }
    }

    public ValidateError isNull(Object value, String caption) {
        return process(caption, "value is NULL", () -> value == null);
    }

    public ValidateError isEmptyOrNull(String value, String caption) {
        return process(caption, "value is EMPTY or NULL", () -> BaseValidate.isEmptyOrNull(value));
    }

    public ValidateError isEmptySpaceOrNull(String value, String caption) {
        return process(caption, "value is EMPTY SPACE or NULL", () -> BaseValidate.isEmptySpaceOrNull(value));
    }

    public ValidateError isEmptyOrNull(Collection<?> collection, String caption) {
        return process(caption, "collection is EMPTY or NULL", () -> BaseValidate.isEmptyOrNull(collection));
    }

    public ValidateError isEmptyOrNull(Object[] array, String caption) {
        return process(caption, "array is EMPTY or NULL", () -> BaseValidate.isEmptyOrNull(array));
    }

    public ValidateError isLess(long number, String caption, long minValue) {
        return process(caption, String.format("number (%s) < %s", number, minValue), () -> number < minValue);
    }

    public ValidateError isLess(double number, String caption, double minValue) {
        return process(caption, String.format("number (%s) < %s", number, minValue), () -> number < minValue);
    }

    public ValidateError isLessOrEquals(long number, String caption, long minValue) {
        return process(caption, String.format("number (%s) <= %s", number, minValue), () -> number <= minValue);
    }

    public ValidateError isLessOrEquals(double number, String caption, double minValue) {
        return process(caption, String.format("number (%s) <= %s", number, minValue), () -> number <= minValue);
    }

    public ValidateError isMore(long number, String caption, long maxValue) {
        return process(caption, String.format("number (%s) > %s", number, maxValue), () -> number > maxValue);
    }

    public ValidateError isMore(double number, String caption, double maxValue) {
        return process(caption, String.format("number (%s) > %s", number, maxValue), () -> number > maxValue);
    }

    public ValidateError isMoreOrEquals(long number, String caption, long maxValue) {
        return process(caption, String.format("number (%s) >= %s", number, maxValue), () -> number >= maxValue);
    }

    public ValidateError isMoreOrEquals(double number, String caption, double maxValue) {
        return process(caption, String.format("number (%s) >= %s", number, maxValue), () -> number >= maxValue);
    }

    public ValidateError isNotRange(long number, String caption, long minValue, long maxValue) {
        return process(caption,
                String.format("number (%s) < %s or > %s", number, minValue, maxValue),
                () -> !BaseValidate.isRange(number, minValue, maxValue));
    }

    private ValidateError process(String caption,
            String message,
            Supplier<Boolean> validateProcess) {
        currentOrdinal++;
        if (checkFail()) {
            return this;
        }

        if (validateProcess.get()) {
            this.item = caption;
            this.reason = message;
            failOrdinal = null;
        } else {
            failOrdinal = currentOrdinal;
        }

        and = false;
        return this;
    }

    private boolean checkFail() {
        return (and && failOrdinal != null)
                || (!and && currentOrdinal > 1 && failOrdinal == null);
    }
}
