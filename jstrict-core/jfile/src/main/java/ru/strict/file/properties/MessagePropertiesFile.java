package ru.strict.file.properties;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MessagePropertiesFile {

    private final PropertiesFile messagePropertiesFile;

    public String getMessage(String messageCode) {
        return messagePropertiesFile.readValue(messageCode);
    }
}
