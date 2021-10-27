package ru.strict.file.properties;

import lombok.RequiredArgsConstructor;
import ru.strict.component.MessageProvider;

@RequiredArgsConstructor
public class MessagePropertiesFile implements MessageProvider {

    private final PropertiesFile messagePropertiesFile;

    @Override
    public String getMessage(String messageCode) {
        return messagePropertiesFile.readValue(messageCode);
    }
}
