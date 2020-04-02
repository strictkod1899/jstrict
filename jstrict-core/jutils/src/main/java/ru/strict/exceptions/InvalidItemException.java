package ru.strict.exceptions;

public class InvalidItemException extends Exception {

    public InvalidItemException(String itemName) {
        super(String.format("Invalid item [%s]", itemName));
    }

    public InvalidItemException(String itemName, Object details) {
        super(String.format("Invalid item [%s]. Details: %s", itemName, details));
    }

}
