package ru.strict.file.properties;

import ru.strict.components.IMessageCode;

public class MessageFile extends ResourcePropertiesFile {

    public static final String FILE_PATH = "message/message.properties";

    public MessageFile() {
        super(FILE_PATH);
    }

    public MessageFile(String lang) {
        super(FILE_PATH, lang, null);
    }

    public MessageFile(String lang, String targetFilePath) {
        super(FILE_PATH, lang, targetFilePath);
    }

    public String getMessage(IMessageCode messageCode) {
        return readValueToUTF8(messageCode.getCode());
    }

    @Override
    protected Class getThisClass() {
        return MessageFile.class;
    }
}
