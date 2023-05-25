package ru.strict.event;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@FieldDefaults(level = AccessLevel.PRIVATE)
class EventSubscriber<E> {
    final ExecutorService executorService;
    final EventListener<E> listener;

    EventSubscriber(EventListener<E> listener, int handlersPoolSize) {
        this.listener = listener;
        this.executorService = Executors.newFixedThreadPool(handlersPoolSize);
    }

    void addEventToQueueForProcess(E event) {
        executorService.submit(() -> listener.processEvent(event));
    }
}
