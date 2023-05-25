package ru.strict.event;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventBus<E> implements EventBroker<E> {
    final List<EventSubscriber<E>> subscribers;

    public EventBus() {
        this.subscribers = new ArrayList<>();
    }

    @Override
    public void subscribe(EventListener<E> eventListener) {
        subscribers.add(new EventSubscriber<>(eventListener, 1));
    }

    @Override
    public void sendEvent(E event) {
        subscribers.forEach(subscriber -> subscriber.addEventToQueueForProcess(event));
    }
}
