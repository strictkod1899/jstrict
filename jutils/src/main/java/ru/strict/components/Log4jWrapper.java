package ru.strict.components;

import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.RollingFileAppender;

/**
 * Логирование. Класс инициализирует конфигурацию по-умолчанию для консольного вывода и записи в файл 'logs/report.log'
 */
public class Log4jWrapper implements ILogger{

    private Logger wrappedObject;
    private LoggerConfiguration configuration;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public void initialize(){
        configuration = new LoggerConfiguration();
        defaultConfiguration();
    }

    public Log4jWrapper(Class clazz) {
        this.wrappedObject = Logger.getLogger(clazz.getName());
        initialize();
    }

    public Log4jWrapper(String className) {
        this.wrappedObject = Logger.getLogger(className);
        initialize();
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Log methods">
    /**
     * Информационное сообщение логирования
     * @param message Сообщение исключения
     */
    @Override
    public void trace(String message){
        wrappedObject.trace(message);
    }

    /**
     * Информационное сообщение логирования
     * @param format Сообщение для String.format
     * @param args Аргументы для String.format
     */
    @Override
    public void trace(String format, String...args){
        wrappedObject.trace(String.format(format, args));
    }

    /**
     * Информационное сообщение логирования для Debug-режима
     * @param message Сообщение исключения
     */
    @Override
    public void debug(String message){
        wrappedObject.debug(message);
    }

    /**
     * Информационное сообщение логирования для Debug-режима
     * @param format Сообщение для String.format
     * @param args Аргументы для String.format
     */
    @Override
    public void debug(String format, String...args){
        wrappedObject.debug(String.format(format, args));
    }

    /**
     * Информационное сообщение логирования
     * @param message Сообщение исключения
     */
    @Override
    public void info(String message){
        wrappedObject.info(message);
    }

    /**
     * Информационное сообщение логирования
     * @param format Сообщение для String.format
     * @param args Аргументы для String.format
     */
    @Override
    public void info(String format, String...args){
        wrappedObject.info(String.format(format, args));
    }

    /**
     * Предупреждающее сообщения логирования
     * @param message Сообщение исключения
     */
    @Override
    public void warn(String message){
        wrappedObject.warn(message);
    }


    @Override
    public void warn(Exception ex){
        wrappedObject.warn(String.format("%s - %s", ex.getClass().toGenericString(), ex.getMessage()));
    }

    @Override
    public void warn(String customMessage, Exception ex){
        wrappedObject.warn(String.format("%s: [%s] - %s", customMessage, ex.getClass().toString(), ex.getMessage()));
    }

    @Override
    public void warn(String format, String...args){
        wrappedObject.warn(String.format(format, args));
    }

    @Override
    public void error(String message){
        wrappedObject.error(message);
    }

    @Override
    public void error(Exception ex){
        wrappedObject.error(String.format("%s - %s", ex.getClass().toGenericString(), ex.getMessage()));
    }

    @Override
    public void error(String customMessage, Exception ex){
        wrappedObject.error(String.format("%s: [%s] - %s", customMessage, ex.getClass().toString(), ex.getMessage()));
    }

    /**
     * Сообщение исключения
     * @param format Сообщение для String.format
     * @param args Аргументы для String.format
     */
    @Override
    public void error(String format, String...args){
        wrappedObject.error(String.format(format, args));
    }

    /**
     * Логирование исключения
     * @param message Сообщение исключения
     */
    @Override
    public void fatal(String message){
        wrappedObject.fatal(message);
    }

    @Override
    public void fatal(Exception ex){
        wrappedObject.fatal(String.format("%s - %s", ex.getClass().toString(), ex.getMessage()));
    }

    @Override
    public void fatal(String customMessage, Exception ex){
        wrappedObject.fatal(String.format("%s: [%s] - %s", customMessage, ex.getClass().toString(), ex.getMessage()));
    }

    /**
     * Сообщение исключения
     * @param format Сообщение для String.format
     * @param args Аргументы для String.format
     */
    @Override
    public void fatal(String format, String...args){
        wrappedObject.fatal(String.format(format, args));
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public Logger getWrappedObject() {
        return wrappedObject;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Configuration">
    /**
     * Конфигурация логирования по-умолчанию
     */
    private void defaultConfiguration(){
        if(configuration != null) {
            configuration.setPattern("%d{dd.MM.yyyy HH:mm:ss,SSS} [%p] %c{1}/%M:%L - %m%n");
            configuration.setLogDirectoryPath("logs");
            configuration.setLogFileName("report.log");
            configuration.setMaxFileSize("1024KB");
            configuration.setMaxBackupIndex(10);

            configuration();
        }
    }

    /**
     * Выполнить конфигурацию логирования, с установленными ранее значениями
     */
    private void configuration(){
        if(configuration != null) {
            PatternLayout layout = new PatternLayout();
            layout.setConversionPattern(configuration.getPattern());


            ConsoleAppender consoleAppender = new ConsoleAppender();
            consoleAppender.setLayout(layout);
            consoleAppender.activateOptions();


            RollingFileAppender fileAppender = new RollingFileAppender();
            fileAppender.setFile(configuration.getLogDirectoryPath() + "\\" + configuration.getLogFileName());
            fileAppender.setMaxFileSize(configuration.getMaxFileSize());
            fileAppender.setMaxBackupIndex(configuration.getMaxBackupIndex());
            fileAppender.setLayout(layout);
            fileAppender.activateOptions();

            wrappedObject.removeAllAppenders();
            wrappedObject.addAppender(consoleAppender);
            wrappedObject.addAppender(fileAppender);
        }
    }

    public void setPattern(String pattern) {
        configuration.setPattern(pattern);
    }

    public void setLogDirectoryPath(String logDirectoryPath) {
        configuration.setLogDirectoryPath(logDirectoryPath);
    }

    public void setMaxFileSize(String maxFileSize) {
        configuration.setMaxFileSize(maxFileSize);
    }

    public void setMaxBackupIndex(int maxBackupIndex) {
        configuration.setMaxBackupIndex(maxBackupIndex);
    }

    public void setLogFileName(String logFileName) {
        configuration.setLogFileName(logFileName);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("Logger: %s", wrappedObject.getName());
    }

    @Override
    public boolean equals(Object obj){
        if(obj != null && obj instanceof Log4jWrapper) {
            Log4jWrapper object = (Log4jWrapper) obj;
            return wrappedObject.equals(object.getWrappedObject());
        }else {
            return false;
        }
    }
    //</editor-fold>
}
