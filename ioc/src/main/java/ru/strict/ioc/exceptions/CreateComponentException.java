package ru.strict.ioc.exceptions;

public class CreateComponentException extends RuntimeException {

    private static final String MESSAGE_TEMPLATE = "Fail create component [class = %s, name = %s]";

    public CreateComponentException(Class componentClass, String componentName, Throwable innerException) {
        super(String.format(MESSAGE_TEMPLATE, componentClass, componentName), innerException);
    }
}
