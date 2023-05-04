package ru.strict.event;

public interface EventListener<E> {
    void processEvent(E event);
}
