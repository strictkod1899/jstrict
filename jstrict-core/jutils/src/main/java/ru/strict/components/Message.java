package ru.strict.components;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class Message implements IMessage, Cloneable {

    private String code;
    private String message;

    public Message(String message) {
        this.message = message;
    }

    public Message(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Message(MessageType code, String message) {
        this.code = code == null ? null : code.getCode();
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Collection<Message> filterByCode(Collection<Message> messages, MessageType code) {
        if (code == null) {
            return null;
        }

        return filterByCode(messages, code.getCode());
    }

    public static Collection<String> filterByCodeToString(Collection<Message> messages, MessageType code) {
        if (code == null) {
            return null;
        }

        return filterByCodeToString(messages, code.getCode());
    }

    public static Collection<Message> filterByCode(Collection<Message> messages, String code) {
        if (messages == null || code == null) {
            return null;
        }

        return messages.stream()
                .filter(message -> message.getCode().equals(code))
                .collect(Collectors.toList());
    }

    public static Collection<String> filterByCodeToString(Collection<Message> messages, String code) {
        if (messages == null || code == null) {
            return null;
        }

        return messages.stream()
                .filter(message -> message.getCode().equals(code))
                .map(Message::getMessage)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", code, message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Message object = (Message) o;
        return Objects.equals(code, object.code) &&
                Objects.equals(message, object.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message);
    }

    @Override
    public Message clone() {
        try {
            return (Message) super.clone();
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
