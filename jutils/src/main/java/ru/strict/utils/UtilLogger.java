package ru.strict.utils;

import ru.strict.components.LoggerWrapper;

import static org.apache.log4j.Logger.getLogger;

/**
 * Управление логированием
 */
public class UtilLogger {

    public static LoggerWrapper createLogger(Class clazz){
        return new LoggerWrapper(clazz);
    }

    public static LoggerWrapper createLogger(String className){
        return new LoggerWrapper(className);
    }

    /**
     * Информационное сообщение логирования для Debug-режима
     * @param clazz Класс, в котором производится логирование
     * @param message Сообщение исключения
     */
    public static void trace(Class clazz, String message){
        LoggerWrapper logger = createLogger(clazz);
        logger.trace(message);
    }

    /**
     * Информационное сообщение логирования
     * @param format Сообщение для String.format
     * @param args Аргументы для String.format
     */
    public void trace(Class clazz, String format, String...args){
        LoggerWrapper logger = createLogger(clazz);
        logger.trace(format, args);
    }

    /**
     * Информационное сообщение логирования для Debug-режима
     * @param message Сообщение исключения
     */
    public void debug(Class clazz, String message){
        LoggerWrapper logger = createLogger(clazz);
        logger.debug(message);
    }

    /**
     * Информационное сообщение логирования для Debug-режима
     * @param format Сообщение для String.format
     * @param args Аргументы для String.format
     */
    public void debug(Class clazz, String format, String...args){
        LoggerWrapper logger = createLogger(clazz);
        logger.trace(format, args);
    }

    /**
     * Информационное сообщение логирования
     * @param clazz Класс, в котором производится логирование
     * @param message Сообщение исключения
     */
    public static void info(Class clazz, String message){
        LoggerWrapper logger = createLogger(clazz);
        logger.info(message);
    }

    /**
     * Информационное сообщение логирования
     * @param format Сообщение для String.format
     * @param args Аргументы для String.format
     */
    public void info(Class clazz, String format, String...args){
        LoggerWrapper logger = createLogger(clazz);
        logger.info(format, args);
    }

    /**
     * Предупреждающее сообщения логирования
     * @param clazz Класс, в котором производится логирование
     * @param message Сообщение исключения
     */
    public static void warn(Class clazz, String message){
        LoggerWrapper logger = createLogger(clazz);
        logger.warn(message);
    }

    /**
     * Предупреждающее сообщения логирования
     * @param format Сообщение для String.format
     * @param args Аргументы для String.format
     */
    public void warn(Class clazz, String format, String...args){
        LoggerWrapper logger = createLogger(clazz);
        logger.warn(format, args);
    }

    /**
     * Логирование исключения
     * @param message Сообщение исключения
     */
    public static void error(Class clazz, String message){
        LoggerWrapper logger = createLogger(clazz);
        logger.error(message);
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
    public static void error(Class clazz, String type,  String message){
        LoggerWrapper logger = createLogger(clazz);
        logger.error(String.format("%s - %s", type, message));
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
    public static void error(Class clazz, String type, String customMessage, String message){
        LoggerWrapper logger = createLogger(clazz);
        logger.error(String.format("%s \n %s - %s", customMessage, type, message));
    }

    /**
     * Логирование исключения
     * @param format Сообщение для String.format
     * @param args Аргументы для String.format
     */
    public void error(Class clazz, String format, String...args){
        LoggerWrapper logger = createLogger(clazz);
        logger.error(format, args);
    }

    /**
     * Логирование исключения
     * @param message Сообщение исключения
     */
    public static void fatal(Class clazz, String message){
        LoggerWrapper logger = createLogger(clazz);
        logger.fatal(message);
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
    public static void fatal(Class clazz, String type,  String message){
        LoggerWrapper logger = createLogger(clazz);
        logger.fatal(String.format("%s - %s", type, message));
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
    public static void fatal(Class clazz, String type, String customMessage, String message){
        LoggerWrapper logger = createLogger(clazz);
        logger.fatal(String.format("%s \n %s - %s", customMessage, type, message));
    }

    /**
     * Логирование исключения
     * @param format Сообщение для String.format
     * @param args Аргументы для String.format
     */
    public void fatal(Class clazz, String format, String...args){
        LoggerWrapper logger = createLogger(clazz);
        logger.fatal(format, args);
    }
}
