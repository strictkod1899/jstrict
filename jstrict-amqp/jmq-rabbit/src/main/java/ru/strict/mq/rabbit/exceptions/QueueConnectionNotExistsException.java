package ru.strict.mq.rabbit.exceptions;

public class QueueConnectionNotExistsException extends Exception {
    private static final String DEFAULT_MESSAGE = "RabbitMQ connection to queue is not exists";

    public QueueConnectionNotExistsException() {
        super(DEFAULT_MESSAGE);
    }

    public QueueConnectionNotExistsException(String message) {
        super(DEFAULT_MESSAGE + ": " + message);
    }
}
