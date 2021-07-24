package ru.strict.ioc.exceptions;

public class SingletonInstanceExistsException extends IoCException {

    private static final String MESSAGE_TEMPLATE = "An error occurred at set singleton-instance because" +
            "singleton-instance already exists. ExistsInstance = %s. NewInstance = %s";

    public SingletonInstanceExistsException(Object existsInstance, Object newInstance) {
        super(String.format(MESSAGE_TEMPLATE, existsInstance, newInstance));
    }
}
