package ru.strict.components;

public interface IExceptionProvider {
    void onThrow(IMessageCode messageCode, Object[] args);
    void onThrow(String messageCode, Object[] args);
}
