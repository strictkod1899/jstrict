package ru.strict.components;

import org.apache.log4j.*;

/**
 * Логирование
 */
public class WrapperLogger{

    private Logger wrappedObject;
    private LoggerConfiguration configuration;

    /**
     * Действия выполняемые
     */
    private void initialize(){
        configuration = new LoggerConfiguration();
        defaultConfiguration();
    }

    public WrapperLogger(Class clazz) {
        this.wrappedObject = Logger.getLogger(clazz);
        initialize();
    }

    public WrapperLogger(String className) {
        this.wrappedObject = Logger.getLogger(className);
        initialize();
    }

    /**
     * Конфигурация логирования по-умолчанию
     */
    private void defaultConfiguration(){
        configuration.setPattern("%d{ABSOLUTE} [%p] %c{1}/%M:%L - %m%n");
        configuration.setFilePath("logs/report.log");
        configuration.setMaxFileSize("1024KB");
        configuration.setMaxBackupIndex(10);

        configuration();
    }

    /**
     * Выполнить конфигурацию логирования, с установленными ранее значениями
     */
    public void configuration(){
        PatternLayout layout = new PatternLayout();
        layout.setConversionPattern(configuration.getPattern());

        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setLayout(layout);
        consoleAppender.activateOptions();

        RollingFileAppender fileAppender = new RollingFileAppender();
        fileAppender.setFile(configuration.getFilePath());
        fileAppender.setMaxFileSize(configuration.getMaxFileSize());
        fileAppender.setMaxBackupIndex(configuration.getMaxBackupIndex());
        fileAppender.setLayout(layout);
        fileAppender.activateOptions();

        Logger rootLogger = Logger.getRootLogger();
        rootLogger.addAppender(consoleAppender);
        rootLogger.addAppender(fileAppender);

        wrappedObject = Logger.getLogger(wrappedObject.getName());
    }

    /**
     * Информационное сообщение логирования
     * @param message Сообщение исключения
     */
    public void trace(String message){
        wrappedObject.trace(message);
    }

    /**
     * Информационное сообщение логирования для Debug-режима
     * @param message Сообщение исключения
     */
    public void debug(String message){
        wrappedObject.debug(message);
    }

    /**
     * Информационное сообщение логирования
     * @param message Сообщение исключения
     */
    public void info(String message){
        wrappedObject.info(message);
    }

    /**
     * Предупреждающее сообщения логирования
     * @param message Сообщение исключения
     */
    public void warn(String message){
        wrappedObject.warn(message);
    }

    /**
     * Логирование исключения
     * @param message Сообщение исключения
     */
    public void error(String message){
        wrappedObject.error(message);
    }

    /**
     * Логирование исключения
     * <p><b>Пример использования:</b></p>
     * <code><pre style="background-color: white; font-family: consolas">
     *      UtilLogger.error(MyClass.class, ex.getClass().toString(), ex.getMessage());
     * </pre></code>
     * @param type Тип исключения
     * @param message Сообщение исключения
     */
    public void error(String type,  String message){
        wrappedObject.error(String.format("%s - %s", type, message));
    }

    /**
     * Логирование исключения
     * <p><b>Пример использования:</b></p>
     * <code><pre style="background-color: white; font-family: consolas">
     *      UtilLogger.error(MyClass.class, "My message", ex.getClass().toString(), ex.getMessage());
     * </pre></code>
     * @param customMessage Пользовательское (дополнительное) сообщение
     * @param type Тип исключения
     * @param message Сообщение исключения
     */
    public void error(String type, String customMessage, String message){
        wrappedObject.error(String.format("%s \n %s - %s", customMessage, type, message));
    }

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public Logger getWrappedObject() {
        return wrappedObject;
    }
    //</editor-fold>

    public void setPattern(String pattern) {
        configuration.setPattern(pattern);
    }

    public void setFilePath(String filePath) {
        configuration.setFilePath(filePath);
    }

    public void setMaxFileSize(String maxFileSize) {
        configuration.setMaxFileSize(maxFileSize);
    }

    public void setMaxBackupIndex(int maxBackupIndex) {
        configuration.setMaxBackupIndex(maxBackupIndex);
    }

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("Logger: %s", wrappedObject.getName());
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof WrapperLogger) {
            WrapperLogger object = (WrapperLogger) obj;
            return wrappedObject.equals(object.getWrappedObject());
        }else
            return false;
    }
    //</editor-fold>
}
