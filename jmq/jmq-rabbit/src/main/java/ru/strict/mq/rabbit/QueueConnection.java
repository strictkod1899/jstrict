package ru.strict.mq.rabbit;

import com.rabbitmq.client.*;
import ru.strict.mq.rabbit.exceptions.QueueChangeException;
import ru.strict.mq.rabbit.exceptions.QueueConnectionException;
import ru.strict.mq.rabbit.exceptions.QueueConnectionNotExistsException;
import ru.strict.validates.ValidateBaseValue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

public abstract class QueueConnection implements AutoCloseable{

    private String queueName;
    private String exchangeName;
    private String routingKey;
    /**
     * Если queue не существует, тогда будет выброшено исключение
     */
    private boolean isPassiveQueue;
    /**
     * Если exchange не существует, тогда будет выброшено исключение
     */
    private boolean isPassiveExchange;

    private String host;
    private Integer port;
    private String username;
    private String password;
    private String virtualHost;

    private String uri;

    protected Connection connection;
    protected Channel channel;

    private AMQP.Queue.DeclareOk queueInfo;

    public QueueConnection() { }

    public QueueConnection(String queueName) {
        this.queueName = queueName;
    }

    public QueueConnection(String queueName, String exchangeName, String routingKey) {
        this.queueName = queueName;
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
    }

    public QueueConnection(String queueName, String exchangeName, String routingKey, String host, int port, String username, String password, String virtualHost) {
        this.queueName = queueName;
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.virtualHost = virtualHost;
    }

    public QueueConnection(String queueName, String exchangeName, String routingKey, String uri) {
        this.queueName = queueName;
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
        this.uri = uri;
    }

    public void open() throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException, IOException, TimeoutException, QueueConnectionException {
        openProcess(false);
    }

    /**
     * Если имя очереди не задано, то выполнение метода будет аналогично open()
     */
    public void openNoWait() throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException, IOException, TimeoutException, QueueConnectionException {
        openProcess(true);
    }

    private void openProcess(boolean isNoWait) throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException, IOException, TimeoutException, QueueConnectionException{
        ConnectionFactory factory = createFactory();

        connection = factory.newConnection();
        channel = connection.createChannel();

        declareQueue(isNoWait);
    }

    private void declareQueue(boolean isNoWait) throws IOException, QueueConnectionException {
        if(!ValidateBaseValue.isEmptySpaceOrNull(exchangeName) && ValidateBaseValue.isEmptySpaceOrNull(routingKey)){
            throw new QueueConnectionException("routingKey is NULL [routingKey is required for exchangeName]");
        }
        if(!ValidateBaseValue.isEmptySpaceOrNull(exchangeName) && ValidateBaseValue.isEmptySpaceOrNull(queueName)){
            throw new QueueConnectionException(String.format("fail a binding of queue [%s] with exchange [%s]. Queue name is null", queueName, exchangeName));
        }
        if(ValidateBaseValue.isEmptySpaceOrNull(exchangeName) && isPassiveExchange){
            throw new QueueConnectionException("fail a declaring of exchange as passive. Exchange name is null");
        }
        if(ValidateBaseValue.isEmptySpaceOrNull(queueName) && isPassiveQueue){
            throw new QueueConnectionException("fail a declaring of queue as passive. Queue name is null");
        }

        if(!ValidateBaseValue.isEmptySpaceOrNull(exchangeName)){
            if(isPassiveExchange) {
                channel.exchangeDeclarePassive(exchangeName);
            } else {
                if(isNoWait){
                    channel.exchangeDeclareNoWait(exchangeName, BuiltinExchangeType.DIRECT, true, false, false,null);
                } else {
                    channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT, true, false, null);
                }
            }
        }

        if(!ValidateBaseValue.isEmptySpaceOrNull(queueName)){
            if(isPassiveQueue){
                queueInfo = channel.queueDeclarePassive(queueName);
            } else {
                if(isNoWait){
                    channel.queueDeclareNoWait(queueName, true, false, false, null);
                } else {
                    queueInfo = channel.queueDeclare(queueName, true, false, false, null);
                }
            }
        } else {
            queueInfo = channel.queueDeclare();
            queueName = queueInfo.getQueue();
        }

        if(!ValidateBaseValue.isEmptySpaceOrNull(exchangeName) && !ValidateBaseValue.isEmptySpaceOrNull(queueName)){
            channel.queueBind(queueName, exchangeName, routingKey);
        }
    }

    @Override
    public void close() throws IOException, TimeoutException {
        if(channel != null){
            channel.close();
            channel = null;
        }

        if(connection != null){
            connection.close();
            connection = null;
        }

        queueInfo = null;
    }

    /**
     * Получить сообщение из очереди с автоподтверждением
     * @return
     */
    public GetResponse getMessage() throws QueueConnectionNotExistsException, IOException {
        return getMessage(true);
    }

    public GetResponse getMessage(boolean isAutoAck) throws QueueConnectionNotExistsException, IOException {
        if(channel == null){
            throw new QueueConnectionNotExistsException();
        }

        return channel.basicGet(queueName, isAutoAck);
    }

    public void ackMessage(long deliveryTag) throws QueueConnectionNotExistsException, IOException {
        if(channel == null){
            throw new QueueConnectionNotExistsException();
        }

        channel.basicAck(deliveryTag, false);
    }

    public void deleteQueue() throws QueueConnectionNotExistsException, IOException {
        if(channel == null){
            throw new QueueConnectionNotExistsException();
        }

        channel.queueDelete(queueName);
    }

    public void deleteQueueIfUnused() throws QueueConnectionNotExistsException, IOException {
        if(channel == null){
            throw new QueueConnectionNotExistsException();
        }

        channel.queueDelete(queueName, true, false);
    }

    public void deleteQueueIfEmpty() throws QueueConnectionNotExistsException, IOException {
        if(channel == null){
            throw new QueueConnectionNotExistsException();
        }

        channel.queueDelete(queueName, false, true);
    }

    public void deleteQueueIfEmptyAndUnused() throws QueueConnectionNotExistsException, IOException {
        if(channel == null){
            throw new QueueConnectionNotExistsException();
        }

        channel.queueDelete(queueName, true, true);
    }

    public void cleanMessages() throws QueueConnectionNotExistsException, IOException {
        if(channel == null){
            throw new QueueConnectionNotExistsException();
        }

        channel.queuePurge(queueName);
    }

    private ConnectionFactory createFactory() throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
        ConnectionFactory factory = new ConnectionFactory();

        if(!ValidateBaseValue.isEmptySpaceOrNull(uri)){
            factory.setUri(uri);
        } else {
            if (!ValidateBaseValue.isEmptySpaceOrNull(host)) {
                factory.setHost(host);
            }
            if (!ValidateBaseValue.isEmptySpaceOrNull(username)) {
                factory.setUsername(username);
            }
            if (!ValidateBaseValue.isEmptySpaceOrNull(password)) {
                factory.setPassword(password);
            }
            if (!ValidateBaseValue.isEmptySpaceOrNull(virtualHost)) {
                factory.setVirtualHost(virtualHost);
            }
            if (port != null && port > 0) {
                factory.setPort(port);
            }
        }

        return factory;
    }

    public Connection getConnection() {
        return connection;
    }

    public Channel getChannel() {
        return channel;
    }

    /**
     * Количество сообщений в очереди
     * @return Если соединение не установлено, то вернется -1
     */
    public int getMessagesCount(){
        return Optional.ofNullable(queueInfo).map(queue -> queue.getMessageCount()).orElse(-1);
    }

    /**
     * Количество отправителей в очереди
     * @return Если соединение не установлено, то вернется -1
     */
    public int getConsumersCount(){
        return Optional.ofNullable(queueInfo).map(queue -> queue.getConsumerCount()).orElse(-1);
    }

    public String getQueueName() {
        return queueName;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) throws QueueChangeException {
        if(channel != null){
            throw new QueueChangeException();
        }
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) throws QueueChangeException {
        if(channel != null){
            throw new QueueChangeException();
        }
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws QueueChangeException {
        if(channel != null){
            throw new QueueChangeException();
        }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws QueueChangeException {
        if(channel != null){
            throw new QueueChangeException();
        }
        this.password = password;
    }

    public String getVirtualHost() {
        return virtualHost;
    }

    public void setVirtualHost(String virtualHost) throws QueueChangeException {
        if(channel != null){
            throw new QueueChangeException();
        }
        this.virtualHost = virtualHost;
    }

    public boolean isPassiveQueue() {
        return isPassiveQueue;
    }

    public void setPassiveQueue(boolean passiveQueue) throws QueueChangeException {
        if(channel != null){
            throw new QueueChangeException();
        }
        isPassiveQueue = passiveQueue;
    }

    public boolean isPassiveExchange() {
        return isPassiveExchange;
    }

    public void setPassiveExchange(boolean passiveExchange) throws QueueChangeException {
        if(channel != null){
            throw new QueueChangeException();
        }
        isPassiveExchange = passiveExchange;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) throws QueueChangeException {
        if(channel != null){
            throw new QueueChangeException();
        }
        this.uri = uri;
    }
}
