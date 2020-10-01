package ru.strict.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Messages implements Cloneable {
    /**
     * Обобщенный сообщения
     */
    private Collection<Message> messages;
    /**
     * Сообщения по ошибке в указанном поле
     */
    private Collection<FieldMessage> fieldMessages;

    public Messages(Collection<Message> messages, Collection<FieldMessage> fieldMessages) {
        this.messages = messages;
        this.fieldMessages = fieldMessages;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public void addFieldMessage(FieldMessage message) {
        fieldMessages.add(message);
    }

    public Collection<Message> getMessages() {
        return messages;
    }

    public List<String> getMessagesAsString() {
        return messages.stream()
                .map(Message::toString)
                .collect(Collectors.toList());
    }

    public Collection<FieldMessage> getFieldMessages() {
        return fieldMessages;
    }

    public List<String> getFieldMessagesAsString() {
        return fieldMessages.stream()
                .map(Message::toString)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Messages object = (Messages) o;
        return Objects.equals(messages, object.messages) &&
                Objects.equals(fieldMessages, object.fieldMessages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messages, fieldMessages);
    }

    @Override
    public Messages clone() {
        try {
            Messages clone = (Messages) super.clone();

            List<Message> actionMessages = new LinkedList<>();
            List<FieldMessage> fieldMessages = new LinkedList<>();
            for (Message message : this.messages) {
                actionMessages.add(message.clone());
            }
            for (FieldMessage message : this.fieldMessages) {
                fieldMessages.add(message.clone());
            }

            clone.messages = actionMessages;
            clone.fieldMessages = fieldMessages;
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Messages asList() {
        return new Messages(new LinkedList<>(), new LinkedList<>());
    }

    public static Messages asSet() {
        return new Messages(new LinkedHashSet<>(), new LinkedHashSet<>());
    }
}
