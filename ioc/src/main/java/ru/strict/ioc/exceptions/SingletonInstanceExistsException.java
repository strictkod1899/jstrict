package ru.strict.ioc.exceptions;

public class SingletonInstanceExistsException extends RuntimeException {

    public SingletonInstanceExistsException(Object createdInstance, Object newInstance) {
        super(String.format("IoC exception. Fail set singleton-instance because singleton-instance already exists." +
                "Created instance = [%s], new instance = [%s]", createdInstance, newInstance));
    }
}
