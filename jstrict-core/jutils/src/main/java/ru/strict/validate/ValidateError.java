package ru.strict.validate;

import ru.strict.exceptions.ValidateException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Builder ошибки. Позволяет динамически задавать параметры ошибки
 */
public class ValidateError {
    /**
     * Наименование элементов (для отображения в ошибке валидации), которые не прошли валидацию
     */
    private List<String> valuesNames;
    /**
     * Причины, по которым не прйодена валидация
     */
    private List<String> reasons;
    /**
     * Значения, которое не прошло валидацию
     */
    private List<Object> values;
    /**
     * Детальное сообщение об ошибке валидации
     */
    private String details;
    /**
     * Аргументы для посдтавновки в String.format при использовании {@link #details}
     */
    private Object[] detailsArgs;

    private boolean and;
    /**
     * Если какие-либо условия (например, {@link #and}) были не выполнены, тогда дальненйший процесс валидации
     * блокируется
     */
    private boolean failProcess;

    ValidateError() {
        this(false);
    }

    ValidateError(boolean and) {
        this.and = and;
        valuesNames = new ArrayList<>(5);
        reasons = new ArrayList<>(5);
        values = new ArrayList<>(5);
    }

    public ValidateError valueName(String valueName) {
        valuesNames.add(valueName);
        return this;
    }

    public ValidateError reason(String reason) {
        reasons.add(reason);
        return this;
    }

    public ValidateError value(Object value) {
        values.add(value);
        return this;
    }

    public ValidateError details(String details, Object... detailsArgs) {
        this.details = details;
        this.detailsArgs = detailsArgs;
        return this;
    }

    /**
     * @throws ValidateException
     */
    public void onThrow() {
        if (!failProcess && !ValidateBaseValue.isEmptyOrNull(valuesNames)) {
            String details = this.details == null ? null : String.format(this.details, this.detailsArgs);

            if (!reasons.isEmpty()) {
                if (!values.isEmpty() && details != null) {
                    if (valuesNames.size() == 1) {
                        throw new ValidateException(valuesNames.get(0), reasons.get(0), values.get(0), details);
                    } else {
                        throw new ValidateException(valuesNames, reasons, values, details);
                    }
                } else if (!values.isEmpty()) {
                    if (valuesNames.size() == 1) {
                        throw new ValidateException(valuesNames.get(0), reasons.get(0), values.get(0));
                    } else {
                        throw new ValidateException(valuesNames, reasons, values);
                    }
                } else if (details != null) {
                    if (valuesNames.size() == 1) {
                        throw new ValidateException(valuesNames.get(0), reasons.get(0), details);
                    } else {
                        throw new ValidateException(valuesNames, reasons, details);
                    }
                } else {
                    throw new ValidateException(valuesNames, reasons, values, details);
                }
            } else {
                if (valuesNames.size() == 1) {
                    throw new ValidateException(valuesNames.get(0));
                } else {
                    throw new ValidateException(valuesNames);
                }
            }
        }
    }

    public ValidateError isNull(Object value, String caption) {
        if (checkReturn()) {
            return this;
        }

        if (value == null) {
            valueName(caption)
                    .reason("value is NULL");
        } else {
            checkProcess(false);
        }
        return this;
    }

    public ValidateError isEmptyOrNull(String value, String caption) {
        if (checkReturn()) {
            return this;
        }

        if (ValidateBaseValue.isEmptyOrNull(value)) {
            valueName(caption)
                    .reason("value is EMPTY or NULL");
        } else {
            checkProcess(false);
        }
        return this;
    }

    public ValidateError isEmptySpaceOrNull(String value, String caption) {
        if (checkReturn()) {
            return this;
        }

        if (ValidateBaseValue.isEmptySpaceOrNull(value)) {
            valueName(caption)
                    .reason("value is EMPTY SPACE or NULL");
        } else {
            checkProcess(false);
        }
        return this;
    }

    public ValidateError isEmptyOrNull(Collection collection, String caption) {
        if (checkReturn()) {
            return this;
        }

        if (ValidateBaseValue.isEmptyOrNull(collection)) {
            valueName(caption)
                    .reason("collection is EMPTY or NULL");
        } else {
            checkProcess(false);
        }
        return this;
    }

    public ValidateError isEmptyOrNull(Object[] array, String caption) {
        if (checkReturn()) {
            return this;
        }

        if (ValidateBaseValue.isEmptyOrNull(array)) {
            valueName(caption)
                    .reason("array is EMPTY or NULL");
        } else {
            checkProcess(false);
        }
        return this;
    }

    public ValidateError isMinValue(long number, String caption, long minValue) {
        if (checkReturn()) {
            return this;
        }

        if (number < minValue) {
            valueName(caption)
                    .reason(String.format("number (%s) < %s", number, minValue));
        } else {
            checkProcess(false);
        }
        return this;
    }

    public ValidateError isMinValue(double number, String caption, double minValue) {
        if (checkReturn()) {
            return this;
        }

        if (number < minValue) {
            valueName(caption)
                    .reason(String.format("number (%s) < %s", number, minValue));
        } else {
            checkProcess(false);
        }
        return this;
    }

    public ValidateError isMaxValue(long number, String caption, long maxValue) {
        if (checkReturn()) {
            return this;
        }

        if (number > maxValue) {
            valueName(caption)
                    .reason(String.format("number (%s) > %s", number, maxValue));
        } else {
            checkProcess(false);
        }
        return this;
    }

    public ValidateError isMaxValue(double number, String caption, double maxValue) {
        if (checkReturn()) {
            return this;
        }

        if (number > maxValue) {
            valueName(caption)
                    .reason(String.format("number (%s) > %s", number, maxValue));
        } else {
            checkProcess(false);
        }
        return this;
    }

    public ValidateError isNotRange(long number, String caption, long minValue, long maxValue) {
        if (checkReturn()) {
            return this;
        }

        if (!ValidateBaseValue.isRange(number, minValue, maxValue)) {
            valueName(caption)
                    .reason(String.format("number (%s) < %s or > %s", number, minValue, maxValue));
        } else {
            checkProcess(false);
        }
        return this;
    }

    private boolean checkReturn() {
        if (failProcess) {
            return true;
        }
        if (!valuesNames.isEmpty() && !and) {
            return true;
        }

        return false;
    }

    private void checkProcess(boolean success) {
        if (and && !success) {
            failProcess = true;
        }
    }
}
