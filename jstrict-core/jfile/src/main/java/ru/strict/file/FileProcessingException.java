package ru.strict.file;

public class FileProcessingException extends RuntimeException {

    private static final String MESSAGE_TEMPLATE = "An error occurred at process file - %s";

    public FileProcessingException(Exception cause) {
        super(cause);
    }

    public FileProcessingException(String fileName, Exception cause) {
        super(String.format(MESSAGE_TEMPLATE, fileName), cause);
    }
}
