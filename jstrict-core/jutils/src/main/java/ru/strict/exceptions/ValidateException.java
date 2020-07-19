package ru.strict.exceptions;

public class ValidateException extends RuntimeException {
    private String item;
    private String reason;
    private Object value;
    private String details;

    public ValidateException(String item) {
        super(String.format("Value is not valid [%s]", item));
        this.item = item;
    }

    public ValidateException(String item, String reason) {
        super(String.format("Value is not valid [%s]. Reason = [%s]", item, reason));
        this.item = item;
        this.reason = reason;
    }

    public ValidateException(String item, String reason, String details) {
        super(String.format("Value is not valid [%s]. Reason = [%s]. Details: %s", item, reason, details));
        this.item = item;
        this.reason = reason;
        this.details = details;
    }

    public ValidateException(String item, String reason, Object value) {
        super(String.format("Value is not valid [%s]. Reason = [%s]. Value = [%s]", item, reason, value));
        this.item = item;
        this.reason = reason;
        this.value = value;
    }

    public ValidateException(String item, String reason, Object value, String details) {
        super(String.format("Value is not valid [%s]. Reason = [%s]. Value = [%s]. Details: %s", item, reason, value, details));
        this.item = item;
        this.reason = reason;
        this.value = value;
        this.details = details;
    }

    public String getItem() {
        return item;
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
