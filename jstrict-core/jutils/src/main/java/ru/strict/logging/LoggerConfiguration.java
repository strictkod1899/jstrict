package ru.strict.logging;

import org.apache.log4j.Level;

import java.util.Objects;

/**
 * Конфигурация логирования
 */
public class LoggerConfiguration {

    private Level level;
    private String pattern;
    private String logDirectoryPath;
    private String logFileName;
    private String maxFileSize;
    private int maxBackupIndex;
    private boolean isLogToConsole;
    private boolean isLogToFile;

    /**
     * Логирование ошибок дополнительно в отдельный файл
     */
    private boolean logErrorsToAdditionalFile;
    private String errorLogFileName;
    private String errorMaxFileSize;
    private int errorMaxBackupIndex;

    /**
     * Логирование сообщений уровня DEBUG в отдельный файл
     */
    private boolean logDebugToSeparateFile;
    private String debugLogFileName;
    private String debugMaxFileSize;
    private int debugMaxBackupIndex;

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getLogDirectoryPath() {
        return logDirectoryPath;
    }

    public void setLogDirectoryPath(String logDirectoryPath) {
        this.logDirectoryPath = logDirectoryPath;
    }

    public String getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(String maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    public int getMaxBackupIndex() {
        return maxBackupIndex;
    }

    public void setMaxBackupIndex(int maxBackupIndex) {
        this.maxBackupIndex = maxBackupIndex;
    }

    public String getLogFileName() {
        return logFileName;
    }

    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName;
    }

    public boolean isLogToConsole() {
        return isLogToConsole;
    }

    public void setLogToConsole(boolean logToConsole) {
        isLogToConsole = logToConsole;
    }

    public boolean isLogToFile() {
        return isLogToFile;
    }

    public void setLogToFile(boolean logToFile) {
        isLogToFile = logToFile;
    }

    public boolean isLogErrorsToAdditionalFile() {
        return logErrorsToAdditionalFile;
    }

    public void setLogErrorsToAdditionalFile(boolean logErrorsToAdditionalFile) {
        this.logErrorsToAdditionalFile = logErrorsToAdditionalFile;
    }

    public String getErrorLogFileName() {
        return errorLogFileName;
    }

    public void setErrorLogFileName(String errorLogFileName) {
        this.errorLogFileName = errorLogFileName;
    }

    public String getErrorMaxFileSize() {
        return errorMaxFileSize;
    }

    public void setErrorMaxFileSize(String errorMaxFileSize) {
        this.errorMaxFileSize = errorMaxFileSize;
    }

    public int getErrorMaxBackupIndex() {
        return errorMaxBackupIndex;
    }

    public void setErrorMaxBackupIndex(int errorMaxBackupIndex) {
        this.errorMaxBackupIndex = errorMaxBackupIndex;
    }

    public boolean isLogDebugToSeparateFile() {
        return logDebugToSeparateFile;
    }

    public void setLogDebugToSeparateFile(boolean logDebugToSeparateFile) {
        this.logDebugToSeparateFile = logDebugToSeparateFile;
    }

    public String getDebugLogFileName() {
        return debugLogFileName;
    }

    public void setDebugLogFileName(String debugLogFileName) {
        this.debugLogFileName = debugLogFileName;
    }

    public String getDebugMaxFileSize() {
        return debugMaxFileSize;
    }

    public void setDebugMaxFileSize(String debugMaxFileSize) {
        this.debugMaxFileSize = debugMaxFileSize;
    }

    public int getDebugMaxBackupIndex() {
        return debugMaxBackupIndex;
    }

    public void setDebugMaxBackupIndex(int debugMaxBackupIndex) {
        this.debugMaxBackupIndex = debugMaxBackupIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoggerConfiguration that = (LoggerConfiguration) o;
        return level == that.level &&
                maxBackupIndex == that.maxBackupIndex &&
                isLogToConsole == that.isLogToConsole &&
                isLogToFile == that.isLogToFile &&
                logErrorsToAdditionalFile == that.logErrorsToAdditionalFile &&
                errorMaxBackupIndex == that.errorMaxBackupIndex &&
                Objects.equals(pattern, that.pattern) &&
                Objects.equals(logDirectoryPath, that.logDirectoryPath) &&
                Objects.equals(logFileName, that.logFileName) &&
                Objects.equals(maxFileSize, that.maxFileSize) &&
                Objects.equals(errorLogFileName, that.errorLogFileName) &&
                Objects.equals(errorMaxFileSize, that.errorMaxFileSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(level, pattern, logDirectoryPath, logFileName, maxFileSize, maxBackupIndex, isLogToConsole, isLogToFile, logErrorsToAdditionalFile, errorLogFileName, errorMaxFileSize, errorMaxBackupIndex);
    }
}
