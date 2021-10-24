package ru.strict.file.properties;

import lombok.Getter;
import lombok.Setter;
import ru.strict.model.Codeable;

@Getter
@Setter
public class MessageFile extends ResourcePropertiesFile {

    public static final String FILE_PATH = "message/message.properties";

    private String fileEncoding;
    private String targetEncoding;

    public MessageFile() {
        super(FILE_PATH);
    }

    public MessageFile(String lang) {
        super(FILE_PATH, lang, null);
    }

    public MessageFile(String lang, String targetFilePath) {
        super(FILE_PATH, lang, targetFilePath);
    }

    public String getMessage(Codeable<String> messageCode) {
        return readValue(messageCode.getCode(), fileEncoding, targetEncoding);
    }

    @Override
    protected Class<MessageFile> getThisClass() {
        return MessageFile.class;
    }
}
