package ru.strict.components;

public interface IExceptionBuilder {
    void onThrow(String messageCode, Object[] args);
}
