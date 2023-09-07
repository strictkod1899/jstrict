package ru.strict.event;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventBus implements EventBroker {
    final Map<String, List<EventSubscriber>> subscribers;

    public EventBus() {
        this.subscribers = new HashMap<>();
    }

    @Override
    public void subscribe(Topic topic, EventListener eventListener) {
        subscribe(topic.getTopic(), eventListener);
    }

    @Override
    public void subscribe(String topic, EventListener eventListener) {
        subscribers.putIfAbsent(topic, new ArrayList<>());

        var topicSubscribers = subscribers.get(topic);
        topicSubscribers.add(new EventSubscriber(eventListener, 1));
    }

    @Override
    public void sendEvent(Topic topic, Object event) {
        sendEvent(topic.getTopic(), event);
    }

    @Override
    public void sendEvent(String topic, Object event) {
        if (!subscribers.containsKey(topic)) {
            return;
        }

        var topicSubscribers = subscribers.get(topic);
        topicSubscribers.forEach(subscriber -> subscriber.addEventToQueueForProcess(event));
    }
}
