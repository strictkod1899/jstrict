package ru.strict.ioc.exceptions;

public class SessionInstanceExistsException extends RuntimeException {

    public SessionInstanceExistsException(Object createdInstance, Object newInstance) {
        super(String.format("IoC exception. Fail set session-instance because session-instance already exists." +
                "Created instance = [%s], new instance = [%s]", createdInstance, newInstance));
    }
}
