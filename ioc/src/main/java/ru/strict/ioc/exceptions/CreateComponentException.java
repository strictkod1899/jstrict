package ru.strict.ioc.exceptions;

public class CreateComponentException extends IoCException {

    private static final String MESSAGE_TEMPLATE =
            "An error occurred at component creation. ComponentClass = %s. ComponentName = %s";

    public CreateComponentException(Class<?> componentClass, String componentName, Throwable innerException) {
        super(String.format(MESSAGE_TEMPLATE, componentClass, componentName), innerException);
    }
}
