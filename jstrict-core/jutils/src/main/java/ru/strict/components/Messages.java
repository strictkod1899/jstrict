package ru.strict.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Messages implements Cloneable {
    /**
     * Обобщенный сообщения
     */
    private List<Message> actionMessages;
    /**
     * Сообщения по ошибке в указанном поле
     */
    private List<FieldMessage> fieldMessages;

    public Messages() {
        actionMessages = new ArrayList<>();
        fieldMessages = new ArrayList<>();
    }

    public void addActionMessage(Message message){
        actionMessages.add(message);
    }

    public void addFieldMessage(FieldMessage message){
        fieldMessages.add(message);
    }

    public List<Message> getActionMessages() {
        return actionMessages;
    }

    public List<FieldMessage> getFieldMessages() {
        return fieldMessages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Messages object = (Messages) o;
        return Objects.equals(actionMessages, object.actionMessages) &&
                Objects.equals(fieldMessages, object.fieldMessages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actionMessages, fieldMessages);
    }

    @Override
    public Messages clone() {
        try {
            Messages clone = (Messages) super.clone();

            List<Message> actionMessages = new ArrayList<>();
            List<FieldMessage> fieldMessages = new ArrayList<>();
            for(Message message : this.actionMessages){
                actionMessages.add(message.clone());
            }
            for(FieldMessage message : this.fieldMessages){
                fieldMessages.add(message.clone());
            }

            clone.actionMessages = actionMessages;
            clone.fieldMessages = fieldMessages;
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
