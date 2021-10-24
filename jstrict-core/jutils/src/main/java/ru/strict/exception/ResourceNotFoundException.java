package ru.strict.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String filePath) {
        super(String.format("Resource file not found [%s]", filePath));
    }
}
