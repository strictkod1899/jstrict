package ru.strict.components;

import java.util.Arrays;

public enum ErrorCode {
    TRACE("TRACE"),
    INFO("INFO"),
    WARNING("WARN"),
    ERROR("ERROR"),
    FATAL("FATAL");

    private String errorCode;

    ErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public static ErrorCode getByCode(String errorCode){
        if(errorCode == null){
            return null;
        }

        return Arrays.stream(ErrorCode.values())
                .filter(e -> e.errorCode.equals(errorCode))
                .findFirst()
                .orElse(null);
    }
}
