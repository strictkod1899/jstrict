package ru.strict.file.properties;

import ru.strict.model.Codeable;

public interface IMessageFile {
    String getMessage(Codeable<String> messageCode);
}
