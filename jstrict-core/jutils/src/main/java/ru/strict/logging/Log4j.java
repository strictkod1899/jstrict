package ru.strict.logging;

import org.apache.log4j.*;
import ru.strict.utils.ClassUtil;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import static ru.strict.utils.ResourcesUtil.*;

/**
 * <p>Логирование с использованием Log4j.</p>
 * <p>Класс инициализирует конфигурацию по-умолчанию для консольного вывода и записи в файл 'logs/report.log'</p>
 *
 * <p><b>Пример использования:</b></p>
 * <code><pre style="background-color: white; font-family: consolas">
 * import ru.strict.logging.Log4jWrapper;
 * import ru.strict.logging.LoggerConfiguration;
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
 * </pre></code>
 */
public class Log4j extends LoggerBase {

    private static final String DEFAULT_PATTERN = "%d{dd.MM.yyyy HH:mm:ss,SSS} [%p] %c{1}/%M:%L - %m%n";
    protected static final String DEFAULT_FOLDER_NAME = "logs";
    private static final String DEFAULT_LOG_FILE_NAME = "logs.log";
    private static final String DEFAULT_MAX_LOG_FILE_SIZE = "5120KB";
    private static final int DEFAULT_MAX_BACKUP_INDEX = 20;
    private static final String DEFAULT_ERROR_LOG_FILE_NAME = "errors.log";
    private static final String DEFAULT_ERROR_MAX_LOG_FILE_SIZE = "5120KB";
    private static final int DEFAULT_ERROR_MAX_BACKUP_INDEX = 10;
    private static final String DEFAULT_DEBUG_LOG_FILE_NAME = "debug.log";
    private static final String DEFAULT_DEBUG_MAX_LOG_FILE_SIZE = "10240KB";
    private static final int DEFAULT_DEBUG_MAX_BACKUP_INDEX = 10;

    private Logger logger;
    private LoggerConfiguration configuration;
    
    private Map<String, Object> userParams;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public Log4j(String className) throws ClassNotFoundException {
        this(Class.forName(className), Collections.emptyMap());
    }

    public Log4j(Class clazz) {
        this(clazz, Collections.emptyMap());
    }

    public Log4j(Class clazz, Map<String, Object> userParams) {
        super(clazz);
        this.logger = Logger.getLogger(this.clazz);
        this.userParams = userParams;

        if (getResource("log4j.xml") == null && getResource("log4j.properties") == null) {
            configuration = new LoggerConfiguration();
            defaultConfiguration();
        }
    }
    //</editor-fold>

    /**
     * Переопределить этот метод для настройки конфигурации в наследующих классах
     */
    protected void prepareConfiguration(LoggerConfiguration configuration, Map<String, Object> userParams) {
    }

    //<editor-fold defaultState="collapsed" desc="Log methods">
    @Override
    public void log(LogLevel logLevel, String format, Object... args) {
        logger.log(convertLogLevel(logLevel), String.format(format, args));
    }

    /**
     * Информационное сообщение логирования
     *
     * @param message Сообщение исключения
     */
    @Override
    public void trace(String message) {
        logger.trace(message);
    }

    /**
     * Информационное сообщение логирования
     *
     * @param format Сообщение для String.format
     * @param args Аргументы для String.format
     */
    @Override
    public void trace(String format, Object... args) {
        logger.trace(String.format(format, args));
    }

    /**
     * Информационное сообщение логирования для Debug-режима
     *
     * @param message Сообщение исключения
     */
    @Override
    public void debug(String message) {
        logger.debug(message);
    }

    /**
     * Информационное сообщение логирования для Debug-режима
     *
     * @param format Сообщение для String.format
     * @param args Аргументы для String.format
     */
    @Override
    public void debug(String format, Object... args) {
        logger.debug(String.format(format, args));
    }

    /**
     * Информационное сообщение логирования
     *
     * @param message Сообщение исключения
     */
    @Override
    public void info(String message) {
        logger.info(message);
    }

    /**
     * Информационное сообщение логирования
     *
     * @param format Сообщение для String.format
     * @param args Аргументы для String.format
     */
    @Override
    public void info(String format, Object... args) {
        logger.info(String.format(format, args));
    }

    /**
     * Предупреждающее сообщения логирования
     *
     * @param message Сообщение исключения
     */
    @Override
    public void warn(String message) {
        logger.warn(message);
    }


    @Override
    public void warn(Exception ex) {
        logger.warn(String.format("%s - %s\n%s", ex.getClass().toGenericString(), ex.getMessage(),
                Arrays.toString(ex.getStackTrace())));
    }

    @Override
    public void warn(String customMessage, Exception ex) {
        logger.warn(String.format("%s: [%s] - %s\n%s", customMessage, ex.getClass().toString(), ex.getMessage(),
                Arrays.toString(ex.getStackTrace())));
    }

    @Override
    public void warn(String format, Object... args) {
        logger.warn(String.format(format, args));
    }

    @Override
    public void error(String message) {
        logger.error(message);
    }

    @Override
    public void error(Exception ex) {
        logger.error(String.format("%s - %s\n%s", ex.getClass().toGenericString(), ex.getMessage(),
                Arrays.toString(ex.getStackTrace())));
    }

    @Override
    public void error(String customMessage, Exception ex) {
        logger.error(String.format("%s: [%s] - %s\n%s", customMessage, ex.getClass().toString(), ex.getMessage(),
                Arrays.toString(ex.getStackTrace())));
    }

    /**
     * Сообщение исключения
     *
     * @param format Сообщение для String.format
     * @param args Аргументы для String.format
     */
    @Override
    public void error(String format, Object... args) {
        logger.error(String.format(format, args));
    }

    /**
     * Логирование исключения
     *
     * @param message Сообщение исключения
     */
    @Override
    public void fatal(String message) {
        logger.fatal(message);
    }

    @Override
    public void fatal(Exception ex) {
        logger.fatal(String.format("%s - %s\n%s", ex.getClass().toString(), ex.getMessage(),
                Arrays.toString(ex.getStackTrace())));
    }

    @Override
    public void fatal(String customMessage, Exception ex) {
        logger.fatal(String.format("%s: [%s] - %s\n%s", customMessage, ex.getClass().toString(), ex.getMessage(),
                Arrays.toString(ex.getStackTrace())));
    }

    /**
     * Сообщение исключения
     *
     * @param format Сообщение для String.format
     * @param args Аргументы для String.format
     */
    @Override
    public void fatal(String format, Object... args) {
        logger.fatal(String.format(format, args));
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public Logger getLogger() {
        return logger;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Configuration">

    /**
     * Конфигурация логирования по-умолчанию
     */
    private void defaultConfiguration() {
        if (configuration != null) {
            configuration.setLevel(LogLevel.DEBUG);
            configuration.setPattern(DEFAULT_PATTERN);
            configuration.setLogDirectoryPath(
                    ClassUtil.getPathByClass(this.getClass()) + File.separator + DEFAULT_FOLDER_NAME);
            configuration.setLogFileName(DEFAULT_LOG_FILE_NAME);
            configuration.setMaxFileSize(DEFAULT_MAX_LOG_FILE_SIZE);
            configuration.setMaxBackupIndex(DEFAULT_MAX_BACKUP_INDEX);

            configuration.setErrorLogFileName(DEFAULT_ERROR_LOG_FILE_NAME);
            configuration.setErrorMaxFileSize(DEFAULT_ERROR_MAX_LOG_FILE_SIZE);
            configuration.setErrorMaxBackupIndex(DEFAULT_ERROR_MAX_BACKUP_INDEX);

            configuration.setDebugLogFileName(DEFAULT_DEBUG_LOG_FILE_NAME);
            configuration.setDebugMaxFileSize(DEFAULT_DEBUG_MAX_LOG_FILE_SIZE);
            configuration.setDebugMaxBackupIndex(DEFAULT_DEBUG_MAX_BACKUP_INDEX);

            configuration.setLogToConsole(true);
            configuration.setLogToFile(true);
            configuration.setLogErrorsToAdditionalFile(true);
            configuration.setLogDebugToSeparateFile(true);

            prepareConfiguration(configuration, userParams);
            configuration();
        }
    }

    /**
     * Выполнить конфигурацию логирования, с установленными ранее значениями
     */
    private void configuration() {
        if (configuration != null) {
            PatternLayout layout = new PatternLayout();
            layout.setConversionPattern(configuration.getPattern());

            logger.removeAllAppenders();

            logger.setLevel(convertLogLevel(configuration.getLevel()));

            if (configuration.isLogToConsole()) {
                ConsoleAppender consoleAppender = new ConsoleAppender();
                consoleAppender.setLayout(layout);
                consoleAppender.activateOptions();
                logger.addAppender(consoleAppender);
            }

            if (configuration.isLogToFile()) {
                RollingFileAppender fileAppender = new RollingFileAppender();
                fileAppender.setThreshold(configuration.isLogDebugToSeparateFile() ? Level.INFO : Level.DEBUG);
                fileAppender.setFile(
                        configuration.getLogDirectoryPath() + File.separator + configuration.getLogFileName());
                fileAppender.setMaxFileSize(configuration.getMaxFileSize());
                fileAppender.setMaxBackupIndex(configuration.getMaxBackupIndex());
                fileAppender.setLayout(layout);
                fileAppender.activateOptions();
                logger.addAppender(fileAppender);
            }

            if (configuration.isLogErrorsToAdditionalFile()) {
                RollingFileAppender errorFileAppender = new RollingFileAppender();
                errorFileAppender.setThreshold(Level.ERROR);
                errorFileAppender.setFile(
                        configuration.getLogDirectoryPath() + File.separator + configuration.getErrorLogFileName());
                errorFileAppender.setMaxFileSize(configuration.getErrorMaxFileSize());
                errorFileAppender.setMaxBackupIndex(configuration.getErrorMaxBackupIndex());
                errorFileAppender.setLayout(layout);
                errorFileAppender.activateOptions();
                logger.addAppender(errorFileAppender);
            }

            if (configuration.isLogDebugToSeparateFile()) {
                RollingFileAppender debugFileAppender = new RollingFileAppender();
                debugFileAppender.setThreshold(Level.DEBUG);
                debugFileAppender.setFile(
                        configuration.getLogDirectoryPath() + File.separator + configuration.getDebugLogFileName());
                debugFileAppender.setMaxFileSize(configuration.getDebugMaxFileSize());
                debugFileAppender.setMaxBackupIndex(configuration.getDebugMaxBackupIndex());
                debugFileAppender.setLayout(layout);
                debugFileAppender.activateOptions();
                logger.addAppender(debugFileAppender);
            }
        }
    }

    private Level convertLogLevel(LogLevel logLevel) {
        switch (logLevel) {
            case FATAL:
                return Level.FATAL;
            case ERROR:
                return Level.ERROR;
            case WARN:
                return Level.WARN;
            case INFO:
                return Level.INFO;
            case DEBUG:
                return Level.DEBUG;
            case ALL:
                return Level.ALL;
            default:
                throw new UnsupportedOperationException(String.format("Unsupported LogLevel [%s]", logLevel));
        }
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString() {
        return String.format("Logger: %s", logger.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Log4j object = (Log4j) o;
        return Objects.equals(logger, object.logger) &&
                Objects.equals(configuration, object.configuration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logger, configuration);
    }
    //</editor-fold>
}
