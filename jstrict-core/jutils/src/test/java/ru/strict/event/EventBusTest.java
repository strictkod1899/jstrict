package ru.strict.event;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventBusTest {

    @Test
    void testPublishAndHandle_ValidData_NoError() throws Exception {
        var object = new AtomicReference<String>();

        var eventListener = new EventListener<String>() {
            @Override
            public void processEvent(String event) {
                if (event.equals("1")) {
                    object.set("hello");
                } else {
                    object.set("world");
                }
            }
        };

        var eventBus = new EventBus<String>();
        eventBus.subscribe(eventListener);

        var eventPublisher = new EventPublisher<>(eventBus);

        eventPublisher.publishEvent("2");
        TimeUnit.MILLISECONDS.sleep(500);

        assertEquals("world", object.get());

        eventPublisher.publishEvent("1");
        TimeUnit.MILLISECONDS.sleep(500);

        assertEquals("hello", object.get());
    }
}
