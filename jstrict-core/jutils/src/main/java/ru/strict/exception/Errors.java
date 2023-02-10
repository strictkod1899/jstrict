package ru.strict.exception;

import java.util.ArrayList;
import java.util.List;

public class Errors {
    private final List<CodeableException> errors;

    public Errors() {
        this.errors = new ArrayList<>();
    }

    public void addError(CodeableException ex) {
        this.errors.add(ex);
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
        return new ErrorsException(this);
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

        private final Errors errors;

        private ErrorsException(Errors errors) {
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
}
