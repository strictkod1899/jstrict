package ru.strict.component;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
@EqualsAndHashCode
public class Message implements Cloneable {
    private final String code;
    private final String message;

    public Message(String message) {
        this.code = null;
        this.message = message;
    }

    public Message(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Message(MessageType messageType, String message) {
        this.code = messageType == null ? null : messageType.getCode();
        this.message = message;
    }

    public static Collection<Message> filterByCode(Collection<Message> messages, MessageType messageType) {
        if (messageType == null) {
            return null;
        }

        return filterByCode(messages, messageType.getCode());
    }

    public static Collection<Message> filterByCode(Collection<Message> messages, String code) {
        if (messages == null || code == null) {
            return null;
        }

        return messages.stream()
                .filter(message -> message.getCode().equals(code))
                .collect(Collectors.toList());
    }

    public static Collection<String> filterByCodeToString(Collection<Message> messages, MessageType code) {
        if (code == null) {
            return null;
        }

        return filterByCodeToString(messages, code.getCode());
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
    public Message clone() {
        try {
            return (Message) super.clone();
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
