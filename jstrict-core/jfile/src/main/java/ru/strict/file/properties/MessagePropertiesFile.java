package ru.strict.file.properties;

import ru.strict.model.Codeable;

import java.io.InputStream;
import java.util.function.Supplier;

public class MessagePropertiesFile extends PropertiesFile implements MessageFile {

    public MessagePropertiesFile(String filePath, String lang) {
        super(filePath, lang);
    }

    private MessagePropertiesFile(Supplier<InputStream> inputStreamSupplier,
            Supplier<InputStream> inputStreamWithSuffixSupplier) {
        super(inputStreamSupplier, inputStreamWithSuffixSupplier);
    }

    @Override
    public String getMessage(Codeable<String> messageCode) {
        return readValue(messageCode.getCode());
    }
}
