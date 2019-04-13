package ru.strict.mq.rabbit;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.ReturnListener;
import org.apache.commons.lang3.SerializationUtils;
import ru.strict.mq.rabbit.exceptions.QueueConnectionNotExistsException;
import ru.strict.validates.ValidateBaseValue;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

public class Producer extends QueueConnection {

    public Producer() {
        super();
    }

    public Producer(String queueName) {
        super(queueName);
    }

    public Producer(String queueName, String exchangeName, String routingKey) {
        super(queueName, exchangeName, routingKey);
    }

    public Producer(String queueName, String exchangeName, String routingKey, String host, int port, String username, String password, String virtualHost) {
        super(queueName, exchangeName, routingKey, host, port, username, password, virtualHost);
    }

    public Producer(String queueName, String exchangeName, String routingKey, String uri) {
        super(queueName, exchangeName, routingKey, uri);
    }

    /**
     * Отправить сообщение
     * @param message Отправляемое сообщение
     * @param headers Пользовательские заголовки
     * @param expiration Срок годности, секунды
     */
    public void sendMessage(Serializable message, Map<String, Object> headers, int expiration) throws QueueConnectionNotExistsException, IOException {
        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                .expiration(String.valueOf(expiration))
                .headers(headers)
                .build();

        sendMessage(message, false, properties);
    }

    /**
     * Отправить сообщение
     * @param message Отправляемое сообщение
     * @param headers Пользовательские заголовки
     */
    public void sendMessage(Serializable message, Map<String, Object> headers) throws QueueConnectionNotExistsException, IOException {
        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                .headers(headers)
                .build();

        sendMessage(message, false, properties);
    }

    /**
     * Отправить сообщение
     * @param message Отправляемое сообщение
     * @param expiration Срок годности, секунды
     */
    public void sendMessage(Serializable message, int expiration) throws QueueConnectionNotExistsException, IOException {
        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                .expiration(String.valueOf(expiration))
                .build();

        sendMessage(message, false, properties);
    }

    /**
     * Отправить сообщение с обязательной доставкой.
     * Если сообщение не будет доставлено, то channel активирует ReturnListener-объекты, которые были присвоены через метод addReturnListener
     * @param message Отправляемое сообщение
     * @param headers Пользовательские заголовки
     * @param expiration Срок годности, секунды
     */
    public void sendMandatoryMessage(Serializable message, Map<String, Object> headers, int expiration) throws QueueConnectionNotExistsException, IOException {
        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                .expiration(String.valueOf(expiration))
                .headers(headers)
                .build();

        sendMessage(message, true, properties);
    }

    /**
     * Отправить сообщение с обязательной доставкой.
     * Если сообщение не будет доставлено, то channel активирует ReturnListener-объекты, которые были присвоены через метод addReturnListener
     * @param message Отправляемое сообщение
     * @param headers Пользовательские заголовки
     */
    public void sendMandatoryMessage(Serializable message, Map<String, Object> headers) throws QueueConnectionNotExistsException, IOException {
        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                .headers(headers)
                .build();

        sendMessage(message, true, properties);
    }

    /**
     * Отправить сообщение с обязательной доставкой.
     * Если сообщение не будет доставлено, то channel активирует ReturnListener-объекты, которые были присвоены через метод addReturnListener
     * @param message Отправляемое сообщение
     * @param expiration Срок годности, секунды
     */
    public void sendMandatoryMessage(Serializable message, int expiration) throws QueueConnectionNotExistsException, IOException {
        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                .expiration(String.valueOf(expiration))
                .build();

        sendMessage(message, true, properties);
    }

    /**
     * Отправить сообщение
     * @param message Отправляемое сообщение
     * @param properties Для создание используется AMQP.BasicProperties.Builder()...build()
     */
    public void sendMessage(Serializable message, boolean mandatory, AMQP.BasicProperties properties) throws QueueConnectionNotExistsException, IOException {
        if(channel == null){
            throw new QueueConnectionNotExistsException();
        }

        channel.basicPublish(getExchangeName(), getQueueName(), mandatory, properties, SerializationUtils.serialize(message));
    }

    /**
     * Слушатель, который будет реагировать на ситуацию, когда сообщение с флагом mandatory не доставлено
     * @param listener
     */
    public void addReturnListener(ReturnListener listener) throws QueueConnectionNotExistsException {
        if(channel == null){
            throw new QueueConnectionNotExistsException();
        }

        if(listener == null){
            throw new IllegalArgumentException("ReturnListener-instance is NULL");
        }

        channel.addReturnListener(listener);
    }

    /**
     * Отменить получателя.
     * У consumer сработает метод handleCancelOk
     * @param consumerTag
     */
    public void cancelConsumer(String consumerTag) throws QueueConnectionNotExistsException, IOException {
        if(channel == null){
            throw new QueueConnectionNotExistsException();
        }

        if(ValidateBaseValue.isEmptyOrNull(consumerTag)){
            throw new IllegalArgumentException("consumerTag is NULL");
        }

        channel.basicCancel(consumerTag);
    }
}
