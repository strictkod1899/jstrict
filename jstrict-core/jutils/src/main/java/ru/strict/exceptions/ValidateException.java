package ru.strict.exceptions;

public class ValidateException extends RuntimeException {
    private String valueName;
    private String reason;
    private Object value;
    private String details;

    public ValidateException(String valueName) {
        super(String.format("Value is not valid [%s]", valueName));
        this.valueName = valueName;
    }

    public ValidateException(String valueName, String reason) {
        super(String.format("Value is not valid [%s]. ", valueName, reason));
        this.valueName = valueName;
        this.reason = reason;
    }

    public ValidateException(String valueName, String reason, String details) {
        super(String.format("Value is not valid [%s]. Reason = [%s]. Details: %s", valueName, reason, details));
        this.valueName = valueName;
        this.reason = reason;
        this.details = details;
    }

    public ValidateException(String valueName, String reason, Object value) {
        super(String.format("Value is not valid [%s]. Reason = [%s]. Value = [%s]", valueName, reason, value));
        this.valueName = valueName;
        this.reason = reason;
        this.value = value;
    }

    public ValidateException(String valueName, String reason, Object value, String details) {
        super(String.format("Value is not valid [%s]. Reason = [%s]. Value = [%s]. Details: %s", valueName, reason, value, details));
        this.valueName = valueName;
        this.reason = reason;
        this.value = value;
        this.details = details;
    }

    public String getValueName() {
        return valueName;
    }

    public String getReason() {
        return reason;
    }

    public Object getValue() {
        return value;
    }

    public String getDetails() {
        return details;
    }
}
