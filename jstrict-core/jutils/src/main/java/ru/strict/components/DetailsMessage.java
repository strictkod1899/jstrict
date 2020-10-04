package ru.strict.components;

import java.util.Objects;

public class DetailsMessage extends Message {

    private String details;

    public DetailsMessage(String message, String details) {
        super(message);
        this.details = details;
    }

    public DetailsMessage(String code, String message, String details) {
        super(code, message);
        this.details = details;
    }

    public DetailsMessage(MessageType code, String message, String details) {
        super(code, message);
        this.details = details;
    }

    public String getDetails() {
        return details;
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
        DetailsMessage that = (DetailsMessage) o;
        return Objects.equals(details, that.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), details);
    }
}
