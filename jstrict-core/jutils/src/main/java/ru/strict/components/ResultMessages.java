package ru.strict.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ResultMessages implements Cloneable {
    private List<Message> alerts;
    private List<Message> messages;

    public ResultMessages() {
        alerts = new ArrayList<>();
        messages = new ArrayList<>();
    }

    public List<Message> getAlerts() {
        return alerts;
    }

    public List<String> getAlertsAsString() {
        return alerts.stream()
                .map(Message::toString)
                .collect(Collectors.toList());
    }

    public void setAlerts(List<Message> alerts) {
        this.alerts = alerts;
    }

    public void addAlert(Message alert) {
        if(alert != null) {
            this.alerts.add(alert);
        }
    }

    public void addAlert(String alert) {
        if(alert != null) {
            this.alerts.add(new Message(alert));
        }
    }

    public List<Message> getMessages() {
        return messages;
    }

    public List<String> getMessagesAsString() {
        return messages.stream()
                .map(Message::toString)
                .collect(Collectors.toList());
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(Message message) {
        if(message != null) {
            this.messages.add(message);
        }
    }

    public boolean hasMessages(){
        return !messages.isEmpty();
    }

    public boolean hasAlerts(){
        return !alerts.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultMessages object = (ResultMessages) o;
        return Objects.equals(alerts, object.alerts) &&
                Objects.equals(messages, object.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alerts, messages);
    }

    @Override
    public ResultMessages clone() {
        try {
            ResultMessages clone = (ResultMessages)super.clone();

            List<Message> alerts = new ArrayList<>(this.alerts.size());
            for(Message alert : this.alerts){
                alerts.add(alert.clone());
            }

            clone.messages = new ArrayList<>(this.messages);
            clone.alerts = alerts;
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
