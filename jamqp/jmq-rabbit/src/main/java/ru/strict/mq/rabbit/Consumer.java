package ru.strict.mq.rabbit;

import ru.strict.mq.rabbit.exceptions.QueueConnectionNotExistsException;
import ru.strict.validate.ValidateBaseValue;

import java.io.IOException;

/**
 * Если необходимо, чтобы consumerTag генерировался авоматически, необходимо передать для него null или пустую строку
 */
public abstract class Consumer extends QueueConnection implements Runnable, com.rabbitmq.client.Consumer{

    private String consumerTag;
    private boolean autoAck;

    public Consumer(String consumerTag) {
        super();
        if(consumerTag != null) {
            this.consumerTag = consumerTag;
        } else {
            this.consumerTag = "";
        }
        autoAck = true;
    }

    public Consumer(String consumerTag, String queueName) {
        super(queueName);
        if(consumerTag != null) {
            this.consumerTag = consumerTag;
        } else {
            this.consumerTag = "";
        }
        autoAck = true;
    }

    public Consumer(String consumerTag, String queueName, String exchangeName, String routingKey) {
        super(queueName, exchangeName, routingKey);
        this.consumerTag = consumerTag;
        autoAck = true;
    }

    public Consumer(String consumerTag, String queueName, String exchangeName, String routingKey, String host, int port, String username, String password, String virtualHost) {
        super(queueName, exchangeName, routingKey, host, port, username, password, virtualHost);
        if(consumerTag != null) {
            this.consumerTag = consumerTag;
        } else {
            this.consumerTag = "";
        }
        autoAck = true;
    }

    public Consumer(String consumerTag, String queueName, String exchangeName, String routingKey, String uri) {
        super(queueName, exchangeName, routingKey, uri);
        if(consumerTag != null) {
            this.consumerTag = consumerTag;
        } else {
            this.consumerTag = "";
        }
        autoAck = true;
    }

    @Override
    public void run() {
        if(channel == null){
            throw new RuntimeException(new QueueConnectionNotExistsException());
        }

        try {
            String returnedConsumerTag = channel.basicConsume(getQueueName(), autoAck, consumerTag, this);
            if(ValidateBaseValue.isEmptyOrNull(consumerTag)){
                consumerTag = returnedConsumerTag;
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Thread runAsThread() throws QueueConnectionNotExistsException {
        if(channel == null){
            throw new QueueConnectionNotExistsException();
        }
        Thread thread = new Thread(this);
        thread.start();
        return thread;
    }

    public String getConsumerTag() {
        return consumerTag;
    }

    public boolean isAutoAck() {
        return autoAck;
    }

    public void setAutoAck(boolean autoAck) throws QueueConnectionNotExistsException {
        if(channel == null){
            throw new QueueConnectionNotExistsException();
        }
        this.autoAck = autoAck;
    }
}
