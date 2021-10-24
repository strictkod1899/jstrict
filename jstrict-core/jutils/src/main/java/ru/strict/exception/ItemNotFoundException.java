package ru.strict.exception;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(String itemName) {
        super(String.format("Item not found [%s]", itemName));
    }

    public ItemNotFoundException(String itemName, Object details) {
        super(String.format("Item not found [%s]. Details: %s", itemName, details));
    }
}
