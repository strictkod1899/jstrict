package ru.strict.patterns.mapper;

public class MappingException extends RuntimeException {

    public MappingException(Throwable cause) {
        super("The exception occurred at object mapping", cause);
    }

    public MappingException(String message, Throwable cause) {
        super(message, cause);
    }
}
