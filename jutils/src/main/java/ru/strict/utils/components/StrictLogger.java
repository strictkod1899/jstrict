package ru.strict.utils.components;

import org.apache.log4j.Logger;

/**
 * Обертка для логгера
 */
public class StrictLogger {

    private final Logger wrappedObject;

    public StrictLogger(Logger logger) {
        this.wrappedObject = logger;
    }

    public StrictLogger(Class clazz) {
        this.wrappedObject = Logger.getLogger(clazz);;
    }

    public StrictLogger(String className) {
        this.wrappedObject = Logger.getLogger(className);
    }

    /**
     * Информационное сообщение логирования для Debug-режима
     * @param message Сообщение исключения
     */
    public void trace(String message){
        wrappedObject.trace(message);
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
     * StrictUtilLogger.error(MyClass.class, ex.getClass().toString(), ex.getMessage());
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
     * StrictUtilLogger.error(MyClass.class, "My message", ex.getClass().toString(), ex.getMessage());
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
        if(obj instanceof StrictLogger) {
            StrictLogger object = (StrictLogger) obj;
            return wrappedObject.equals(object.getWrappedObject());
        }else
            return false;
    }
    //</editor-fold>
}
