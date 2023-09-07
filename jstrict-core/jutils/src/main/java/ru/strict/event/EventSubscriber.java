package ru.strict.event;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@FieldDefaults(level = AccessLevel.PRIVATE)
class EventSubscriber {
    final ExecutorService executorService;
    final EventListener listener;

    EventSubscriber(EventListener listener, int handlersPoolSize) {
        this.listener = listener;
        this.executorService = Executors.newFixedThreadPool(handlersPoolSize);
    }

    void addEventToQueueForProcess(Object event) {
        executorService.submit(() -> listener.processEvent(event));
    }
}
