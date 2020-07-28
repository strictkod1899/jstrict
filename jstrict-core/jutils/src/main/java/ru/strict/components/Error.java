package ru.strict.components;

import ru.strict.validate.Validator;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Ошибка
 */
public class Error implements IError, Cloneable {

    private String code;
    private String message;

    public Error(String message) {
        this.message = message;
    }

    public Error(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Error(ErrorCode code, String message) {
        Validator.isNull(code, "code");
        this.code = code.getErrorCode();
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Collection<Error> filterByCode(Collection<Error> errors, String code){
        if(errors == null || code == null){
            return null;
        }

        return errors.stream()
                .filter(error -> error.getCode().equals(code))
                .collect(Collectors.toList());
    }

    public static Collection<Error> filterByCode(Collection<Error> errors, ErrorCode code){
        if(code == null){
            return null;
        }

        return filterByCode(errors, code.getErrorCode());
    }

    public static Collection<String> filterByCodeToString(Collection<Error> errors, String code){
        if(errors == null || code == null){
            return null;
        }

        return errors.stream()
                .filter(error -> error.getCode().equals(code))
                .map(error -> error.getMessage())
                .collect(Collectors.toList());
    }

    public static Collection<String> filterByCodeToString(Collection<Error> errors, ErrorCode code){
        if(code == null){
            return null;
        }

        return filterByCodeToString(errors, code.getErrorCode());
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", code, message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Error object = (Error) o;
        return Objects.equals(code, object.code) &&
                Objects.equals(message, object.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message);
    }

    @Override
    public Error clone() {
        try {
            return (Error) super.clone();
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
