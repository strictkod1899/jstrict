package ru.strict.utils;

import org.apache.log4j.Logger;
import ru.strict.components.WrapperLogger;

import static org.apache.log4j.Logger.getLogger;

/**
 * Управление логированием
 */
public class UtilLogger {

    public static WrapperLogger createLogger(Class clazz){
        return new WrapperLogger(clazz);
    }

    public static WrapperLogger createLogger(String className){
        return new WrapperLogger(className);
    }

    /**
     * Информационное сообщение логирования для Debug-режима
     * @param clazz Класс, в котором производится логирование
     * @param message Сообщение исключения
     */
    public static void trace(Class clazz, String message){
        Logger logger = createLogger(clazz);
        logger.trace(message);
    }

    /**
     * Информационное сообщение логирования
     * @param clazz Класс, в котором производится логирование
     * @param message Сообщение исключения
     */
    public static void info(Class clazz, String message){
        Logger logger = createLogger(clazz);
        logger.info(message);
    }

    /**
     * Предупреждающее сообщения логирования
     * @param clazz Класс, в котором производится логирование
     * @param message Сообщение исключения
     */
    public static void warn(Class clazz, String message){
        Logger logger = createLogger(clazz);
        logger.warn(message);
    }

    /**
     * Логирование исключения
     * @param clazz Класс, в котором производится логирование
     * @param message Сообщение исключения
     */
    public static void error(Class clazz, String message){
        Logger logger = createLogger(clazz);
        logger.error(message);
    }

    /**
     * Логирование исключения
     * <p><b>Пример использования:</b></p>
     * <code><pre style="background-color: white; font-family: consolas">
     * UtilLogger.error(MyClass.class, ex.getClass().toString(), ex.getMessage());
     * </pre></code>
     * @param clazz Класс, в котором производится логирование
     * @param type Тип исключения
     * @param message Сообщение исключения
     */
    public static void error(Class clazz, String type,  String message){
        Logger logger = createLogger(clazz);
        logger.error(String.format("%s - %s", type, message));
    }

    /**
     * Логирование исключения
     * <p><b>Пример использования:</b></p>
     * <code><pre style="background-color: white; font-family: consolas">
     * UtilLogger.error(MyClass.class, "My message", ex.getClass().toString(), ex.getMessage());
     * </pre></code>
     * @param clazz Класс, в котором производится логирование
     * @param customMessage Пользовательское (дополнительное) сообщение
     * @param type Тип исключения
     * @param message Сообщение исключения
     */
    public static void error(Class clazz, String customMessage, String type,  String message){
        Logger logger = createLogger(clazz);
        logger.error(String.format("%s \n %s - %s", customMessage, type, message));
    }
}
