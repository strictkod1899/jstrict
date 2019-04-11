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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoggerConfiguration object = (LoggerConfiguration) o;
        return maxBackupIndex == object.maxBackupIndex &&
                isLogToConsole == object.isLogToConsole &&
                isLogToFile == object.isLogToFile &&
                Objects.equals(pattern, object.pattern) &&
                Objects.equals(logDirectoryPath, object.logDirectoryPath) &&
                Objects.equals(logFileName, object.logFileName) &&
                Objects.equals(maxFileSize, object.maxFileSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pattern, logDirectoryPath, logFileName, maxFileSize, maxBackupIndex, isLogToConsole, isLogToFile);
    }
}
