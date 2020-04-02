package ru.strict.mq.rabbit.exceptions;

public class QueueConnectionException extends Exception {
    private static final String DEFAULT_MESSAGE = "RabbitMQ connection error";

    public QueueConnectionException() {
        super(DEFAULT_MESSAGE);
    }

    public QueueConnectionException(String message) {
        super(DEFAULT_MESSAGE + ": " + message);
    }
}
