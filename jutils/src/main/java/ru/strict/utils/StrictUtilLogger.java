package ru.strict.utils;

import org.apache.log4j.Logger;

import static org.apache.log4j.Logger.getLogger;

/**
 * Управления логированием
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

    public static void error(Class clazz, String type,  String message){
        Logger logger = getLogger(clazz);
        logger.error(String.format("%s - %s", type, message));
    }

    public static void error(Class clazz, String customMessage, String type,  String message){
        Logger logger = getLogger(clazz);
        logger.error(String.format("%s \n %s - %s", customMessage, type, message));
    }
}
