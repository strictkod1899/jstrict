package ru.strict.component;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Сообщение поля ввода данных
 */
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FieldMessage extends Message {
    private final String field;

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
    public FieldMessage clone() {
        return (FieldMessage) super.clone();
    }
}
