package ru.strict.file.properties;

import lombok.RequiredArgsConstructor;
import ru.strict.model.Codeable;

@RequiredArgsConstructor
public class MessagePropertiesFile implements MessageFile {

    private final PropertiesFile messagePropertiesFile;

    @Override
    public String getMessage(Codeable<String> messageCode) {
        return messagePropertiesFile.readValue(messageCode.getCode());
    }
}
