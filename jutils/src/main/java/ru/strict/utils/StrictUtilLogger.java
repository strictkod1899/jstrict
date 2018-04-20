package ru.strict.utils;

import org.apache.log4j.Logger;

import static org.apache.log4j.Logger.getLogger;

/**
 * Управление логированием
 */
public class StrictUtilLogger {

    public static void trace(Class clazz, String message){
        Logger logger = getLogger(clazz);
        logger.trace(message);
    }

    public static void info(Class clazz, String message){
        Logger logger = getLogger(clazz);
        logger.info(message);
    }

    public static void error(Class clazz, String message){
        Logger logger = getLogger(clazz);
        logger.error(message);
    }

    /**
     * <p><b>Пример использования:</b></p>
     * <code><pre style="background-color: white; font-family: consolas">
     * StrictUtilLogger.error(MyClass.class, ex.getClass().toString(), ex.getMessage());
     * </pre></code>
     * @param clazz
     * @param type
     * @param message
     */
    public static void error(Class clazz, String type,  String message){
        Logger logger = getLogger(clazz);
        logger.error(String.format("%s - %s", type, message));
    }

    /**
     * <p><b>Пример использования:</b></p>
     * <code><pre style="background-color: white; font-family: consolas">
     * StrictUtilLogger.error(MyClass.class, "My message", ex.getClass().toString(), ex.getMessage());
     * </pre></code>
     * @param clazz
     * @param customMessage
     * @param type
     * @param message
     */
    public static void error(Class clazz, String customMessage, String type,  String message){
        Logger logger = getLogger(clazz);
        logger.error(String.format("%s \n %s - %s", customMessage, type, message));
    }
}
