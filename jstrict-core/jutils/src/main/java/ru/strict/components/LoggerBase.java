package ru.strict.components;

public abstract class LoggerBase implements ILogger {
    protected Class clazz;

    public LoggerBase(Class clazz) {
        this.clazz = clazz;
    }
}
