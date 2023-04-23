package ru.strict.exception;

import java.util.List;

public class ErrorsException extends RuntimeException {

    private final Errors errors;

    public ErrorsException(Errors errors) {
        this.errors = errors;
    }

    public List<CodeableException> toList() {
        return errors.toList();
    }

    public boolean containsByCode(String code) {
        return this.errors.containsByCode(code);
    }

    @Override
    public String toString() {
        return this.errors.toString();
    }
}
