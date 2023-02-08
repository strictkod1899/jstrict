package ru.strict.validate;

import lombok.Getter;

/**
 * @deprecated use CodeableException
 */
@Getter
public class ItemValidateException extends RuntimeException {
    private String itemName;
    private String reason;
    private String details;

    public ItemValidateException(String itemName, String reason) {
        super(String.format("Value is not valid [%s]. Reason = [%s]", itemName, reason));
        this.itemName = itemName;
        this.reason = reason;
    }

    public ItemValidateException(String itemName, String reason, String details) {
        super(String.format("Value is not valid [%s]. Reason = [%s]. Details: %s", itemName, reason, details));
        this.itemName = itemName;
        this.reason = reason;
        this.details = details;
    }
}
