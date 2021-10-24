package ru.strict.file;

public class ReadonlyException extends RuntimeException {

    public ReadonlyException() {
        super("Write file not accessed");
    }
}
