package ru.strict.ioc.exceptions;

public class ManyMatchConstructorFieldsException extends IoCException {

    private static final String MESSAGE_TEMPLATE = "An error occurred at component creation because" +
            "was found many fields with @Component-annotation and same constructor param ordinal = %s. " +
            "ConstructorParameter = %s, ComponentClass = %s";

    public ManyMatchConstructorFieldsException(Class<?> componentClass, Class<?> constructorParameter, int ordinal) {
        super(String.format(MESSAGE_TEMPLATE, ordinal, constructorParameter, componentClass));
    }
}
