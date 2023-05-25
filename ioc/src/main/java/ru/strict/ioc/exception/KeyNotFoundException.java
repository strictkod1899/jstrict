package ru.strict.ioc.exception;

public class KeyNotFoundException extends IoCException {

    private static final String MESSAGE_TEMPLATE_BY_COMPONENT =
            "Key not found by class = %s";
    private static final String MESSAGE_TEMPLATE_BY_NAME =
            "Key not found by name = %s";

    public KeyNotFoundException(Class<?> componentClass) {
        super(String.format(MESSAGE_TEMPLATE_BY_COMPONENT, componentClass));
    }

    public KeyNotFoundException(String componentName) {
        super(String.format(MESSAGE_TEMPLATE_BY_NAME, componentName));
    }
}
