package ru.strict.event;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventPublisher<E> {
    final EventBroker<E> eventBroker;

    public EventPublisher(EventBroker<E> eventBroker) {
        this.eventBroker = eventBroker;
    }

    public void publishEvent(E event) {
        eventBroker.sendEvent(event);
    }
}
