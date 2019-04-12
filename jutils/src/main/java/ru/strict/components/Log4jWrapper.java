package ru.strict.components;

import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.RollingFileAppender;
import ru.strict.utils.UtilSystem;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Objects;

/**
 * Логирование с использованием Log4j.
 * Класс инициализирует конфигурацию по-умолчанию для консольного вывода и записи в файл 'logs/report.log'
 *
 * import ru.strict.components.Log4jWrapper;
 * import ru.strict.components.LoggerConfiguration;
 *
 * import java.io.File;
 *
 * public class AppLogger extends Log4jWrapper {
 *
 * public AppLogger(Class clazz) {
 *     super(clazz);
 * }
 *
 * public AppLogger(String className) {
 *     super(className);
 * }
 *
 * @Override
 * protected void prepareConfiguration(LoggerConfiguration configuration) {
 *     super.prepareConfiguration(configuration);
 *     configuration.setLogToConsole(false);
 *     configuration.setLogDirectoryPath("mypath");
 * }
 * }
 */
public class Log4jWrapper implements ILogger {

    private static final String DEFAULT_PATTERN = "%d{dd.MM.yyyy HH:mm:ss,SSS} [%p] %c{1}/%M:%L - %m%n";
    private static final String DEFAULT_FOLDER_NAME = "logs";
    private static final String DEFAULT_LOG_FILE_NAME = "report.log";
    private static final String DEFAULT_MAX_LOG_FILE_SIZE = "1024KB";
    private static final int DEFAULT_MAX_BACKUP_INDEX = 10;

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

    /**
     * Переопределить этот метод для настройки конфигурации в наследующих классах
     */
    protected void prepareConfiguration(LoggerConfiguration configuration){}

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
    public void trace(String format, Object...args){
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
    public void debug(String format, Object...args){
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
    public void info(String format, Object...args){
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
        wrappedObject.warn(String.format("%s - %s\n%s", ex.getClass().toGenericString(), ex.getMessage(),
                Arrays.toString(ex.getStackTrace())));
    }

    @Override
    public void warn(String customMessage, Exception ex){
        wrappedObject.warn(String.format("%s: [%s] - %s\n%s", customMessage, ex.getClass().toString(), ex.getMessage(),
                Arrays.toString(ex.getStackTrace())));
    }

    @Override
    public void warn(String format, Object...args){
        wrappedObject.warn(String.format(format, args));
    }

    @Override
    public void error(String message){
        wrappedObject.error(message);
    }

    @Override
    public void error(Exception ex){
        wrappedObject.error(String.format("%s - %s\n%s", ex.getClass().toGenericString(), ex.getMessage(),
                Arrays.toString(ex.getStackTrace())));
    }

    @Override
    public void error(String customMessage, Exception ex){
        wrappedObject.error(String.format("%s: [%s] - %s\n%s", customMessage, ex.getClass().toString(), ex.getMessage(),
                Arrays.toString(ex.getStackTrace())));
    }

    /**
     * Сообщение исключения
     * @param format Сообщение для String.format
     * @param args Аргументы для String.format
     */
    @Override
    public void error(String format, Object...args){
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
        wrappedObject.fatal(String.format("%s - %s\n%s", ex.getClass().toString(), ex.getMessage(),
                Arrays.toString(ex.getStackTrace())));
    }

    @Override
    public void fatal(String customMessage, Exception ex){
        wrappedObject.fatal(String.format("%s: [%s] - %s\n%s", customMessage, ex.getClass().toString(), ex.getMessage(),
                Arrays.toString(ex.getStackTrace())));
    }

    /**
     * Сообщение исключения
     * @param format Сообщение для String.format
     * @param args Аргументы для String.format
     */
    @Override
    public void fatal(String format, Object...args){
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
            configuration.setPattern(DEFAULT_PATTERN);
            try {
                configuration.setLogDirectoryPath(UtilSystem.getPathByClass(this.getClass()) + File.separator + DEFAULT_FOLDER_NAME);
            } catch (URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
            configuration.setLogFileName(DEFAULT_LOG_FILE_NAME);
            configuration.setMaxFileSize(DEFAULT_MAX_LOG_FILE_SIZE);
            configuration.setMaxBackupIndex(DEFAULT_MAX_BACKUP_INDEX);
            configuration.setLogToConsole(true);
            configuration.setLogToFile(true);

            prepareConfiguration(configuration);
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

            wrappedObject.removeAllAppenders();

            if(configuration.isLogToConsole()){
                ConsoleAppender consoleAppender = new ConsoleAppender();
                consoleAppender.setLayout(layout);
                consoleAppender.activateOptions();
                wrappedObject.addAppender(consoleAppender);
            }

            if(configuration.isLogToFile()) {
                RollingFileAppender fileAppender = new RollingFileAppender();
                fileAppender.setFile(configuration.getLogDirectoryPath() + "\\" + configuration.getLogFileName());
                fileAppender.setMaxFileSize(configuration.getMaxFileSize());
                fileAppender.setMaxBackupIndex(configuration.getMaxBackupIndex());
                fileAppender.setLayout(layout);
                fileAppender.activateOptions();
                wrappedObject.addAppender(fileAppender);
            }
        }
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("Logger: %s", wrappedObject.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Log4jWrapper that = (Log4jWrapper) o;
        return Objects.equals(wrappedObject, that.wrappedObject) &&
                Objects.equals(configuration, that.configuration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wrappedObject, configuration);
    }

    //</editor-fold>
}
