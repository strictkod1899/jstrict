package ru.strict.ioc.exceptions;

public class SessionInstanceExistsException extends RuntimeException {

    private static final String MESSAGE_TEMPLATE = "An error occurred at set session-instance because session-instance " +
            "already exists. ExistsInstance = %s. NewInstance = %s";

    public SessionInstanceExistsException(Object existsInstance, Object newInstance) {
        super(String.format(MESSAGE_TEMPLATE, existsInstance, newInstance));
    }
}
