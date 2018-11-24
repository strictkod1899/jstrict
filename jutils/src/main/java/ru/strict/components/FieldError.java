package ru.strict.components;

/**
 * Ошибка поля ввода данных
 */
public class FieldError extends Error implements IFieldError{

    private String field;

    public FieldError(String errorMessage) {
        super(errorMessage);
    }

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
}
