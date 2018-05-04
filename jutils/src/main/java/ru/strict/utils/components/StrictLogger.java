package ru.strict.utils.components;

import org.apache.log4j.Logger;

/**
 * Обертка для логгера
 */
public class StrictLogger {

    private final Logger logger;

    public StrictLogger(Logger logger) {
        this.logger = logger;
    }

    /**
     * Информационное сообщение логирования для Debug-режима
     * @param message Сообщение исключения
     */
    public void trace(String message){
        logger.trace(message);
    }

    /**
     * Информационное сообщение логирования
     * @param message Сообщение исключения
     */
    public void info(String message){
        logger.info(message);
    }

    /**
     * Предупреждающее сообщения логирования
     * @param message Сообщение исключения
     */
    public void warn(String message){
        logger.warn(message);
    }

    /**
     * Логирование исключения
     * @param message Сообщение исключения
     */
    public void error(String message){
        logger.error(message);
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
        logger.error(String.format("%s - %s", type, message));
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
        logger.error(String.format("%s \n %s - %s", customMessage, type, message));
    }

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public Logger getLogger() {
        return logger;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("Logger: %s", logger.getName());
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof StrictLogger) {
            StrictLogger object = (StrictLogger) obj;
            return logger.equals(object.getLogger());
        }else
            return false;
    }
    //</editor-fold>
}
