package ru.strict.event;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import ru.strict.validate.CommonValidator;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventPublisher {
    final EventBroker eventBroker;
    final Topic topic;

    public EventPublisher(EventBroker eventBroker, Topic topic) {
        CommonValidator.throwIfNull(eventBroker, "eventBroker");
        CommonValidator.throwIfNull(topic, "topic");

        this.eventBroker = eventBroker;
        this.topic = topic;
    }

    public void publishEvent(Object event) {
        eventBroker.sendEvent(topic, event);
    }
}
