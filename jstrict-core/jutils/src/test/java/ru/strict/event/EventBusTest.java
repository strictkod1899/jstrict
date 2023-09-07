package ru.strict.event;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class EventBusTest {

    @Test
    void testPublishAndHandle_ValidSingleTopic_NoError() throws Exception {
        var topic = new SimpleTopic("anyTopic");
        var object = new AtomicReference<String>();

        var eventListener = new EventListener() {
            @Override
            public void processEvent(Object event) {
                if (event.equals("1")) {
                    object.set("hello");
                } else {
                    object.set("world");
                }
            }
        };

        var eventBus = new EventBus();
        eventBus.subscribe(topic, eventListener);

        var eventPublisher = new EventPublisher(eventBus, topic);

        eventPublisher.publishEvent("2");
        TimeUnit.MILLISECONDS.sleep(500);

        assertEquals("world", object.get());

        eventPublisher.publishEvent("1");
        TimeUnit.MILLISECONDS.sleep(500);

        assertEquals("hello", object.get());
    }

    @Test
    void testPublishAndHandle_ValidSeveralTopics_NoError() throws Exception {
        var topic1 = new SimpleTopic("topic1");
        var topic2 = new SimpleTopic("topic2");

        var object1 = new AtomicReference<String>();
        var object2 = new AtomicReference<String>();

        var eventListenerForTopic1 = new EventListener() {
            @Override
            public void processEvent(Object event) {
                if (event.equals("1")) {
                    object1.set("hello");
                } else {
                    object1.set("world");
                }
            }
        };

        var eventListenerForTopic2 = new EventListener() {
            @Override
            public void processEvent(Object event) {
                if (event.equals("2")) {
                    object2.set("any");
                }
            }
        };

        var eventBus = new EventBus();
        eventBus.subscribe(topic1, eventListenerForTopic1);
        eventBus.subscribe(topic2, eventListenerForTopic2);

        var eventPublisher1 = new EventPublisher(eventBus, topic1);
        var eventPublisher2 = new EventPublisher(eventBus, topic2);

        eventPublisher1.publishEvent("2");
        TimeUnit.MILLISECONDS.sleep(500);

        assertEquals("world", object1.get());
        assertNull(object2.get());

        eventPublisher1.publishEvent("1");
        TimeUnit.MILLISECONDS.sleep(500);

        assertEquals("hello", object1.get());
        assertNull(object2.get());

        eventPublisher2.publishEvent("2");
        TimeUnit.MILLISECONDS.sleep(500);

        assertEquals("hello", object1.get());
        assertEquals("any", object2.get());
    }

    @RequiredArgsConstructor
    private static class SimpleTopic implements Topic {

        final String topic;

        @Override
        public String getTopic() {
            return topic;
        }
    }
}
