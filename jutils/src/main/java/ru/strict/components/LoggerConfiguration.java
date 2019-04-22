package ru.strict.components;

import java.util.Objects;

/**
 * Конфигурация логирования. Используется в классе Log4jWrapper
 */
public class LoggerConfiguration {

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
    private boolean isLogErrorsToAdditionalFile;
    private String errorLogFileName;
    private String errorMaxFileSize;
    private int errorMaxBackupIndex;

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
        return isLogErrorsToAdditionalFile;
    }

    public void setLogErrorsToAdditionalFile(boolean logErrorsToAdditionalFile) {
        isLogErrorsToAdditionalFile = logErrorsToAdditionalFile;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoggerConfiguration that = (LoggerConfiguration) o;
        return maxBackupIndex == that.maxBackupIndex &&
                isLogToConsole == that.isLogToConsole &&
                isLogToFile == that.isLogToFile &&
                isLogErrorsToAdditionalFile == that.isLogErrorsToAdditionalFile &&
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
        return Objects.hash(pattern, logDirectoryPath, logFileName, maxFileSize, maxBackupIndex, isLogToConsole, isLogToFile, isLogErrorsToAdditionalFile, errorLogFileName, errorMaxFileSize, errorMaxBackupIndex);
    }
}
