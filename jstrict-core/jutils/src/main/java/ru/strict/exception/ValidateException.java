package ru.strict.exception;

/**
 * @deprecated use CodeableException
 */
public class ValidateException extends RuntimeException {

    public ValidateException(String details, Object... args) {
        super(String.format(details, args));
    }

    public ValidateException(Exception cause) {
        super(cause);
    }

    public ValidateException(String details, Exception cause, Object... args) {
        super(String.format(details, args), cause);
    }
}
