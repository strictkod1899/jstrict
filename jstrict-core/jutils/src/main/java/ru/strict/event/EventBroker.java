package ru.strict.event;

public interface EventBroker<E> {
    void subscribe(EventListener<E> eventListener);
    void sendEvent(E event);
}
