package ru.strict.ioc.exception;

public class ManyMatchConstructorsException extends IoCException {

    private static final String MESSAGE_TEMPLATE =
            "An error occurred at component creation because was found many constructors. ComponentClass = %s";

    public ManyMatchConstructorsException(Class<?> component) {
        super(String.format(MESSAGE_TEMPLATE, component));
    }
}
