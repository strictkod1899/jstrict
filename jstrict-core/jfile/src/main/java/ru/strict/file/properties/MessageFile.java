package ru.strict.file.properties;

import ru.strict.model.Codeable;

public interface MessageFile {
    String getMessage(Codeable<String> messageCode);
}
