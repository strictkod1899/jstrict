package ru.strict.exception;

import java.util.ArrayList;
import java.util.List;

public class Errors {
    private final List<CodeableException> errors;

    public Errors() {
        this.errors = new ArrayList<>();
    }

    public boolean isEmpty() {
        return errors.size() == 0;
    }

    public boolean isPresent() {
        return errors.size() > 0;
    }

    public int size() {
        return errors.size();
    }

    public boolean contains(Throwable error) {
        if (error == null) {
            return false;
        }

        if (!(error instanceof CodeableException)) {
            return false;
        }

        for (var err : errors) {
            if (err.equals(error)) {
                return true;
            }
        }

        return false;
    }

    public boolean containsByCode(String code) {
        if (code == null) {
            return false;
        }

        for (var error : errors) {
            if (error.getCode().equals(code)) {
                return true;
            }
        }

        return false;
    }

    public List<CodeableException> toList() {
        return errors;
    }

    public ErrorsException toException() {
        return new ErrorsException(this.toString());
    }

    @Override
    public String toString() {
        var str = new StringBuilder();
        for (var error : errors) {
            str.append(error.toString());
            str.append("\n");
        }

        return str.toString();
    }

    public static class ErrorsException extends RuntimeException {

        private ErrorsException(String message) {
            super(message);
        }
    }
}
