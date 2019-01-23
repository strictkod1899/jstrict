package ru.strict.components;

/**
 * Ошибка
 */
public class Error implements IError {

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

    @Override
    public String toString() {
        return errorMessage;
    }
}
