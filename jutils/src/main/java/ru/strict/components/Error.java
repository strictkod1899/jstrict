package ru.strict.components;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Ошибка
 */
public class Error implements IError, Cloneable {

    private String code;
    private String errorMessage;

    public Error(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Error(String code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public Error(ErrorCode code, String errorMessage) {
        this.code = code.getErrorCode();
        this.errorMessage = errorMessage;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
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
                .map(error -> error.getErrorMessage())
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
        return String.format("[%s] %s", code, errorMessage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Error object = (Error) o;
        return Objects.equals(code, object.code) &&
                Objects.equals(errorMessage, object.errorMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, errorMessage);
    }

    @Override
    public Error clone() {
        return new Error(code, errorMessage);
    }
}
