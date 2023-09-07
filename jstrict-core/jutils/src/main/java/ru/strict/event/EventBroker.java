package ru.strict.event;

public interface EventBroker {
    void subscribe(Topic topic, EventListener eventListener);
    void subscribe(String topic, EventListener eventListener);
    void sendEvent(Topic topic, Object event);
    void sendEvent(String topic, Object event);
}
