package ru.strict.components;

import ru.strict.validate.ValidateBaseValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ResultMessages implements Cloneable {

    /**
     * Сообщение в порядке добавления. Содержит и ошибки и др.
     */
    private List<Object> sequenceMessages;
    private List<Error> errors;
    private List<String> messages;

    public ResultMessages() {
        errors = new ArrayList<>();
        messages = new ArrayList<>();
        sequenceMessages = new ArrayList<>();
    }

    public List<Error> getErrors() {
        return errors;
    }

    public List<String> getStringErrors() {
        return errors.stream().map(error -> error.toString()).collect(Collectors.toList());
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
        this.sequenceMessages.addAll(errors);
    }

    public void addError(Error error) {
        if(error != null) {
            this.errors.add(error);
            this.sequenceMessages.add(error);
        }
    }

    public void addError(String error) {
        if(error != null) {
            this.errors.add(new Error(error));
            this.sequenceMessages.add(error);
        }
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
        this.sequenceMessages.addAll(messages);
    }

    public void addMessage(String message) {
        if(!ValidateBaseValue.isEmptyOrNull(message)) {
            this.messages.add(message);
            this.sequenceMessages.add(message);
        }
    }

    public List<Object> getSequenceMessages() {
        return sequenceMessages;
    }

    public List<String> getSequenceStringMessages() {
        return sequenceMessages.stream().map(message -> message.toString()).collect(Collectors.toList());
    }

    public boolean hasMessages(){
        return !messages.isEmpty();
    }

    public boolean hasErrors(){
        return !errors.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultMessages object = (ResultMessages) o;
        return Objects.equals(sequenceMessages, object.sequenceMessages) &&
                Objects.equals(errors, object.errors) &&
                Objects.equals(messages, object.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sequenceMessages, errors, messages);
    }

    @Override
    public ResultMessages clone() {
        try {
            ResultMessages clone = (ResultMessages)super.clone();

            List<Error> errors = new ArrayList<>(this.errors.size());
            for(Error error : this.errors){
                errors.add(error.clone());
            }

            clone.messages = new ArrayList<>(this.messages);
            clone.sequenceMessages = new ArrayList<>(this.sequenceMessages);
            clone.errors = errors;
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
