package ru.strict.validate;

import ru.strict.exceptions.ValidateException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Builder ошибки. Позволяет динамически задавать параметры ошибки
 */
public class ValidateError {
    private List<DetailsError> errors;

    /**
     * Ошибка, которая генерируется в текущий момент
     */
    private DetailsError currentError;

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
        errors = new ArrayList<>(5);
    }

    public ValidateError reason(String reason) {
        if (currentError == null) {
            return this;
        }

        currentError.setReason(reason);
        return this;
    }

    public ValidateError value(Object value) {
        if (currentError == null) {
            return this;
        }

        currentError.setValue(value);
        return this;
    }

    public ValidateError details(String details, Object... detailsArgs) {
        if (currentError == null) {
            return this;
        }

        currentError.setDetails(details);
        currentError.setDetailsArgs(detailsArgs);
        return this;
    }

    /**
     * @throws ValidateException
     */
    public void onThrow() {
        if (currentError != null) {
            errors.add(currentError);
        }

        if (failProcess || errors.isEmpty()) {
            return;
        }

        String details = getDetailsMessage(errors);

        if (errors.size() == 1) {
            DetailsError error = errors.get(0);
            if (error.getReason() == null) {
                throw new ValidateException(error.getValueName());
            } else {
                if (error.getValue() != null) {
                    if (details != null) {
                        throw new ValidateException(error.getValueName(), error.getReason(), error.getValue(), details);
                    } else {
                        throw new ValidateException(error.getValueName(), error.getReason(), error.getValue());
                    }
                } else if (details != null) {
                    throw new ValidateException(error.getValueName(), error.getReason(), details);
                } else {
                    throw new ValidateException(error.getValueName(), error.getReason(), error.getValue(), details);
                }
            }
        } else {
            List<String> valuesNames = new ArrayList<>(errors.size());
            List<String> reasons = new ArrayList<>(errors.size());
            List<Object> values = new ArrayList<>(errors.size());
            for (DetailsError error : errors) {
                valuesNames.add(error.getValueName());
                if (error.getReason() != null) {
                    reasons.add(error.getReason());
                }
                if (error.getValue() != null) {
                    values.add(error.getValue());
                }
            }

            if (reasons.isEmpty()) {
                throw new ValidateException(valuesNames);
            } else {
                if (!values.isEmpty()) {
                    if (details != null) {
                        throw new ValidateException(valuesNames, reasons, values, details);
                    } else {
                        throw new ValidateException(valuesNames, reasons, values);
                    }
                } else if (details != null) {
                    throw new ValidateException(valuesNames, reasons, details);
                } else {
                    throw new ValidateException(valuesNames, reasons, values, details);
                }
            }
        }
    }

    private String getDetailsMessage(List<DetailsError> errors) {
        DetailsError lastError = errors.get(errors.size() - 1);
        String details = lastError.getDetails();
        Object[] detailsArgs = lastError.getDetailsArgs();
        return details == null ? null : String.format(details, detailsArgs);
    }

    public ValidateError isNull(Object value, String caption) {
        if (prepareAndCheckReturn()) {
            return this;
        }

        if (value == null) {
            currentError.setValueName(caption);
            currentError.setReason("value is NULL");
        } else {
            checkProcess(false);
        }
        return this;
    }

    public ValidateError isEmptyOrNull(String value, String caption) {
        if (prepareAndCheckReturn()) {
            return this;
        }

        if (ValidateBaseValue.isEmptyOrNull(value)) {
            currentError.setValueName(caption);
            currentError.setReason("value is EMPTY or NULL");
        } else {
            checkProcess(false);
        }
        return this;
    }

    public ValidateError isEmptySpaceOrNull(String value, String caption) {
        if (prepareAndCheckReturn()) {
            return this;
        }

        if (ValidateBaseValue.isEmptySpaceOrNull(value)) {
            currentError.setValueName(caption);
            currentError.setReason("value is EMPTY SPACE or NULL");
        } else {
            checkProcess(false);
        }
        return this;
    }

    public ValidateError isEmptyOrNull(Collection collection, String caption) {
        if (prepareAndCheckReturn()) {
            return this;
        }

        if (ValidateBaseValue.isEmptyOrNull(collection)) {
            currentError.setValueName(caption);
            currentError.setReason("collection is EMPTY or NULL");
        } else {
            checkProcess(false);
        }
        return this;
    }

    public ValidateError isEmptyOrNull(Object[] array, String caption) {
        if (prepareAndCheckReturn()) {
            return this;
        }

        if (ValidateBaseValue.isEmptyOrNull(array)) {
            currentError.setValueName(caption);
            currentError.setReason("array is EMPTY or NULL");
        } else {
            checkProcess(false);
        }
        return this;
    }

    public ValidateError isLess(long number, String caption, long minValue) {
        if (prepareAndCheckReturn()) {
            return this;
        }

        if (number < minValue) {
            currentError.setValueName(caption);
            currentError.setReason(String.format("number (%s) < %s", number, minValue));
        } else {
            checkProcess(false);
        }
        return this;
    }

    public ValidateError isLess(double number, String caption, double minValue) {
        if (prepareAndCheckReturn()) {
            return this;
        }

        if (number < minValue) {
            currentError.setValueName(caption);
            currentError.setReason(String.format("number (%s) < %s", number, minValue));
        } else {
            checkProcess(false);
        }
        return this;
    }

    public ValidateError isLessOrEquals(long number, String caption, long minValue) {
        if (prepareAndCheckReturn()) {
            return this;
        }

        if (number <= minValue) {
            currentError.setValueName(caption);
            currentError.setReason(String.format("number (%s) <= %s", number, minValue));
        } else {
            checkProcess(false);
        }
        return this;
    }

    public ValidateError isLessOrEquals(double number, String caption, double minValue) {
        if (prepareAndCheckReturn()) {
            return this;
        }

        if (number <= minValue) {
            currentError.setValueName(caption);
            currentError.setReason(String.format("number (%s) <= %s", number, minValue));
        } else {
            checkProcess(false);
        }
        return this;
    }

    public ValidateError isMore(long number, String caption, long maxValue) {
        if (prepareAndCheckReturn()) {
            return this;
        }

        if (number > maxValue) {
            currentError.setValueName(caption);
            currentError.setReason(String.format("number (%s) > %s", number, maxValue));
        } else {
            checkProcess(false);
        }
        return this;
    }

    public ValidateError isMore(double number, String caption, double maxValue) {
        if (prepareAndCheckReturn()) {
            return this;
        }

        if (number > maxValue) {
            currentError.setValueName(caption);
            currentError.setReason(String.format("number (%s) > %s", number, maxValue));
        } else {
            checkProcess(false);
        }
        return this;
    }

    public ValidateError isMoreOrEquals(long number, String caption, long maxValue) {
        if (prepareAndCheckReturn()) {
            return this;
        }

        if (number >= maxValue) {
            currentError.setValueName(caption);
            currentError.setReason(String.format("number (%s) >= %s", number, maxValue));
        } else {
            checkProcess(false);
        }
        return this;
    }

    public ValidateError isMoreOrEquals(double number, String caption, double maxValue) {
        if (prepareAndCheckReturn()) {
            return this;
        }

        if (number >= maxValue) {
            currentError.setValueName(caption);
            currentError.setReason(String.format("number (%s) >= %s", number, maxValue));
        } else {
            checkProcess(false);
        }
        return this;
    }

    public ValidateError isNotRange(long number, String caption, long minValue, long maxValue) {
        if (prepareAndCheckReturn()) {
            return this;
        }

        if (!ValidateBaseValue.isRange(number, minValue, maxValue)) {
            currentError.setValueName(caption);
            currentError.setReason(String.format("number (%s) < %s or > %s", number, minValue, maxValue));
        } else {
            checkProcess(false);
        }
        return this;
    }

    private boolean prepareAndCheckReturn() {
        if (currentError != null) {
            errors.add(currentError);
            currentError = null;
        }

        if (checkReturn()) {
            return true;
        }

        currentError = new DetailsError();
        return false;
    }

    private boolean checkReturn() {
        if (failProcess) {
            return true;
        }
        if (!errors.isEmpty() && !and) {
            return true;
        }

        return false;
    }

    private void checkProcess(boolean success) {
        if (!success) {
            currentError = null;
            if (and) {
                failProcess = true;
            }
        }

    }

    private class DetailsError {
        /**
         * Наименование элемента (для отображения в ошибке валидации), который не прошел валидацию
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
        /**
         * Аргументы для посдтавновки в String.format при использовании {@link #details}
         */
        private Object[] detailsArgs;

        public String getValueName() {
            return valueName;
        }

        public DetailsError setValueName(String valueName) {
            this.valueName = valueName;
            return this;
        }

        public String getReason() {
            return reason;
        }

        public DetailsError setReason(String reason) {
            this.reason = reason;
            return this;
        }

        public Object getValue() {
            return value;
        }

        public DetailsError setValue(Object value) {
            this.value = value;
            return this;
        }

        public String getDetails() {
            return details;
        }

        public DetailsError setDetails(String details) {
            this.details = details;
            return this;
        }

        public Object[] getDetailsArgs() {
            return detailsArgs;
        }

        public DetailsError setDetailsArgs(Object[] detailsArgs) {
            this.detailsArgs = detailsArgs;
            return this;
        }
    }
}
