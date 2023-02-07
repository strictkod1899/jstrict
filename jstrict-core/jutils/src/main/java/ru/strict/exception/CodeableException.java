package ru.strict.exception;

import lombok.Getter;

import java.util.Objects;

@Getter
public class CodeableException extends RuntimeException {
    private final String code;

    public CodeableException(String code, String message) {
        super(message);
        this.code = code;
    }

    public CodeableException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        var other = (CodeableException) o;
        return Objects.equals(this.code, other.code) &&
                Objects.equals(this.getMessage(), other.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.code, this.getMessage());
    }

    @Override
    public String toString() {
        return String.format("%s: %s", code, getMessage());
    }

    public static boolean equalsByCode(CodeableException e, String expectedCode) {
        return e.code.equals(expectedCode);
    }
}
