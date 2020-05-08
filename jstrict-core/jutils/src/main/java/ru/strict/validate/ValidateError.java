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

    /*public ValidateError valueName(String valueName) {
        valuesNames.add(valueName);
        return this;
    }*/

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

        if (!failProcess && !errors.isEmpty()) {
            DetailsError lastError =  errors.get(errors.size()-1);
            String details = lastError.getDetails();
            Object[] detailsArgs = lastError.getDetailsArgs();
            details = details == null ? null : String.format(details, detailsArgs);

            List<String> valuesNames = new ArrayList<>(errors.size());
            List<String> reasons = new ArrayList<>(errors.size());
            List<Object> values = new ArrayList<>(errors.size());
            for (DetailsError error : errors) {
                valuesNames.add(error.getValueName());
                reasons.add(error.getReason());
                values.add(error.getValue());
            }

            if (values.stream().allMatch(Objects::isNull)) {
                values = new ArrayList<>();
            }

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

        if (currentError != null) {
            errors.add(currentError);
        }
        currentError = new DetailsError();

        if (value == null) {
            currentError.setValueName(caption);
            currentError.setReason("value is NULL");
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
            currentError.setValueName(caption);
            currentError.setReason("value is EMPTY or NULL");
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
            currentError.setValueName(caption);
            currentError.setReason("value is EMPTY SPACE or NULL");
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
            currentError.setValueName(caption);
            currentError.setReason("collection is EMPTY or NULL");
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
            currentError.setValueName(caption);
            currentError.setReason("array is EMPTY or NULL");
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
            currentError.setValueName(caption);
            currentError.setReason(String.format("number (%s) < %s", number, minValue));
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
            currentError.setValueName(caption);
            currentError.setReason(String.format("number (%s) < %s", number, minValue));
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
            currentError.setValueName(caption);
            currentError.setReason(String.format("number (%s) > %s", number, maxValue));
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
            currentError.setValueName(caption);
            currentError.setReason(String.format("number (%s) > %s", number, maxValue));
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
            currentError.setValueName(caption);
            currentError.setReason(String.format("number (%s) < %s or > %s", number, minValue, maxValue));
        } else {
            checkProcess(false);
        }
        return this;
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
