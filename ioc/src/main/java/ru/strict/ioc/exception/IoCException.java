package ru.strict.ioc.exception;

public class IoCException extends RuntimeException {

    public IoCException(String message) {
        super(message);
    }

    public IoCException(String message, Throwable cause) {
        super(message, cause);
    }
}
