package ru.strict.component;

import ru.strict.model.Codeable;

public interface MessageProvider {
    default String getMessage(Codeable<String> messageCode) {
        return getMessage(messageCode.getCode());
    }
    String getMessage(String messageCode);
}
