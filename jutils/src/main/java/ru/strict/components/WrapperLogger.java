package ru.strict.components;

import org.apache.log4j.*;

/**
 * Обертка для логгера
 */
public class WrapperLogger{

    private final Logger wrappedObject;

    public WrapperLogger(Class clazz) {
        defaultConfiguration();
        this.wrappedObject = Logger.getLogger(clazz);
    }

    public WrapperLogger(String className) {
        defaultConfiguration();
        this.wrappedObject = Logger.getLogger(className);
    }

    public WrapperLogger(Logger logger) {
        defaultConfiguration();
        this.wrappedObject = logger;
    }

    private void defaultConfiguration(){
        PatternLayout layout = new PatternLayout();
        layout.setConversionPattern("%d{ABSOLUTE} [%p] %c{1}/%M:%L - %m%n");

        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setLayout(layout);
        consoleAppender.activateOptions();

        RollingFileAppender fileAppender = new RollingFileAppender();
        fileAppender.setFile("logs/report.log");
        fileAppender.setMaxFileSize("1024KB");
        fileAppender.setMaxBackupIndex(10);
        fileAppender.setLayout(layout);
        fileAppender.activateOptions();

        Logger rootLogger = Logger.getRootLogger();
        rootLogger.addAppender(consoleAppender);
        rootLogger.addAppender(fileAppender);
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
     * UtilLogger.error(MyClass.class, ex.getClass().toString(), ex.getMessage());
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
     * UtilLogger.error(MyClass.class, "My message", ex.getClass().toString(), ex.getMessage());
     * </pre></code>
     * @param customMessage Пользовательское (дополнительное) сообщение
     * @param type Тип исключения
     * @param message Сообщение исключения
     */
    public void error(String customMessage, String type,  String message){
        wrappedObject.error(String.format("%s \n %s - %s", customMessage, type, message));
    }

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public Logger getWrappedObject() {
        return wrappedObject;
    }
    //</editor-fold>

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
