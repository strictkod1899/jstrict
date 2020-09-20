package ru.strict.components;

import java.util.Objects;

/**
 * Сообщение поля ввода данных
 */
public class FieldMessage extends Message implements IFieldMessage {

    private String field;

    public FieldMessage(String field, String message) {
        super(message);
        this.field = field;
    }

    public FieldMessage(String field, String code, String message) {
        super(code, message);
        this.field = field;
    }

    public FieldMessage(String field, MessageType code, String message) {
        super(code, message);
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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        FieldMessage object = (FieldMessage) o;
        return Objects.equals(field, object.field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), field);
    }

    @Override
    public FieldMessage clone() {
        return (FieldMessage) super.clone();
    }
}
