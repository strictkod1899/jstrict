package ru.strict.component;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DetailMessage extends Message {
    private final String details;

    public DetailMessage(String message, String details) {
        super(message);
        this.details = details;
    }

    public DetailMessage(String code, String message, String details) {
        super(code, message);
        this.details = details;
    }

    public DetailMessage(MessageType code, String message, String details) {
        super(code, message);
        this.details = details;
    }

    @Override
    public DetailMessage clone() {
        return (DetailMessage) super.clone();
    }
}
