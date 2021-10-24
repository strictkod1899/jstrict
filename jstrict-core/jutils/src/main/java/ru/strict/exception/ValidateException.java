package ru.strict.exception;

import lombok.Getter;

@Getter
public class ValidateException extends RuntimeException {
    private String itemName;
    private String reason;
    private String details;

    private ValidateException(String details, Object... args) {
        super(String.format(details, args));
        this.details = details;
    }

    public ValidateException(String itemName, String reason) {
        super(String.format("Value is not valid [%s]. Reason = [%s]", itemName, reason));
        this.itemName = itemName;
        this.reason = reason;
    }

    public ValidateException(String itemName, String reason, String details) {
        super(String.format("Value is not valid [%s]. Reason = [%s]. Details: %s", itemName, reason, details));
        this.itemName = itemName;
        this.reason = reason;
        this.details = details;
    }

    public static ValidateException byDetails(String details, Object... args) {
        return new ValidateException(details, args);
    }
}
