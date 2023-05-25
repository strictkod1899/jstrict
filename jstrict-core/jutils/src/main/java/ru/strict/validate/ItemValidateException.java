package ru.strict.validate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemValidateException extends RuntimeException {
    String itemName;
    String reason;

    public ItemValidateException(String itemName, String reason) {
        super(String.format("%s %s", itemName, reason));
        this.itemName = itemName;
        this.reason = reason;
    }
}
