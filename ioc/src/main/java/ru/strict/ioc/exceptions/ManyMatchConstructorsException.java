package ru.strict.ioc.exceptions;

public class ManyMatchConstructorsException extends RuntimeException {

    private static final String MESSAGE_TEMPLATE =
            "An error occurred at component creation because was found many constructors. ComponentClass = %s";

    public ManyMatchConstructorsException(Class<?> component) {
        super(String.format(MESSAGE_TEMPLATE, component));
    }
}
