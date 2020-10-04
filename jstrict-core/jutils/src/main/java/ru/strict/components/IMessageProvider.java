package ru.strict.components;

public interface IMessageProvider {
    default String getMessage(IMessageCode messageCode) {
        return getMessage(messageCode.getCode());
    }

    String getMessage(String messageCode);
}
