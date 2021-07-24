package ru.strict.ioc.exceptions;

public class IoCException extends RuntimeException {

    public IoCException(String message) {
        super(message);
    }

    public IoCException(String message, Throwable cause) {
        super(message, cause);
    }
}
