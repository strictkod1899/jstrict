package ru.strict.mq.rabbit.exceptions;

public class QueueChangeException extends Exception {
    private static final String DEFAULT_MESSAGE = "RabbitMQ connection to queue is exists. Change properties is illegal";

    public QueueChangeException() {
        super(DEFAULT_MESSAGE);
    }

    public QueueChangeException(String message) {
        super(DEFAULT_MESSAGE + ": " + message);
    }
}
