package ru.strict.components;

import java.util.Objects;

/**
 * Ошибка поля ввода данных
 */
public class FieldError extends Error implements IFieldError{

    private String field;

    public FieldError(String field, String errorMessage) {
        super(errorMessage);
        this.field = field;
    }

    public FieldError(String field, String code, String errorMessage) {
        super(code, errorMessage);
        this.field = field;
    }

    @Override
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FieldError object = (FieldError) o;
        return Objects.equals(field, object.field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), field);
    }

    @Override
    public FieldError clone() {
        return (FieldError) super.clone();
    }
}
