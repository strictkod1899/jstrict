package ru.strict.exceptions;

import java.util.Collections;
import java.util.List;

public class ValidateException extends RuntimeException {
    private List<String> valuesNames;
    private List<String> reasons;
    private List<Object> values;
    private String details;

    public ValidateException(String valueName) {
        super(String.format("Value is not valid [%s]", valueName));
        this.valuesNames = Collections.singletonList(valueName);
    }

    public ValidateException(String valueName, String reason) {
        super(String.format("Value is not valid [%s]. Reason = [%s]", valueName, reason));
        this.valuesNames = Collections.singletonList(valueName);
        this.reasons = Collections.singletonList(reason);
    }

    public ValidateException(String valueName, String reason, String details) {
        super(String.format("Value is not valid [%s]. Reason = [%s]. Details: %s", valueName, reason, details));
        this.valuesNames = Collections.singletonList(valueName);
        this.reasons = Collections.singletonList(reason);
        this.details = details;
    }

    public ValidateException(String valueName, String reason, Object value) {
        super(String.format("Value is not valid [%s]. Reason = [%s]. Value = [%s]", valueName, reason, value));
        this.valuesNames = Collections.singletonList(valueName);
        this.reasons = Collections.singletonList(reason);
        this.values = Collections.singletonList(value);
    }

    public ValidateException(String valueName, String reason, Object value, String details) {
        super(String.format("Value is not valid [%s]. Reason = [%s]. Value = [%s]. Details: %s",
                valueName,
                reason,
                value,
                details));
        this.valuesNames = Collections.singletonList(valueName);
        this.reasons = Collections.singletonList(reason);
        this.values = Collections.singletonList(value);
        this.details = details;
    }

    public ValidateException(List<String> valuesNames) {
        super(String.format("Values is not valid [%s]", valuesNames));
        this.valuesNames = valuesNames;
    }

    public ValidateException(List<String> valuesNames, List<String> reasons) {
        super(formatMessage(valuesNames, reasons, null, null));
        this.valuesNames = valuesNames;
        this.reasons = reasons;
    }

    public ValidateException(List<String> valuesNames, List<String> reasons, String details) {
        super(formatMessage(valuesNames, reasons, null, details));
        this.valuesNames = valuesNames;
        this.reasons = reasons;
        this.details = details;
    }

    public ValidateException(List<String> valuesNames, List<String> reasons, List<Object> values) {
        super(formatMessage(valuesNames, reasons, values, null));
        this.valuesNames = valuesNames;
        this.reasons = reasons;
        this.values = values;
    }

    public ValidateException(List<String> valuesNames, List<String> reasons, List<Object> values, String details) {
        super(formatMessage(valuesNames, reasons, values, details));
        this.valuesNames = valuesNames;
        this.reasons = reasons;
        this.values = values;
        this.details = details;
    }

    private static String formatMessage(List<String> valuesNames,
            List<String> reasons,
            List<Object> values,
            String details) {
        StringBuilder errorText = new StringBuilder();
        for (int i = 0; i < valuesNames.size(); i++) {
            if (errorText.length() > 0) {
                errorText.append("    |    ");
            }

            if (values.isEmpty()) {
                errorText.append(String.format("%s - %s", valuesNames.get(i), reasons.get(i)));
            } else {
                errorText.append(String.format("%s - %s [value = %s]",
                        valuesNames.get(i),
                        reasons.get(i),
                        values.get(i))
                );
            }
        }

        if (details == null) {
            return String.format("Values is not valid. Reasons [%s]", errorText.toString());
        } else {
            return String.format("Values is not valid. %s. Reasons [%s]", details, errorText.toString());
        }
    }

    public List<String> getValuesNames() {
        return valuesNames;
    }

    public List<String> getReasons() {
        return reasons;
    }

    public List<Object> getValues() {
        return values;
    }

    public String getDetails() {
        return details;
    }
}
