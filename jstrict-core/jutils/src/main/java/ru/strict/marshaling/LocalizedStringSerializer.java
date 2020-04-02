package ru.strict.marshaling;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ru.strict.i18n.LocalizedString;

import java.io.IOException;

public final class LocalizedStringSerializer extends JsonSerializer<LocalizedString> {

    public void serialize(LocalizedString localizedString,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeObject(localizedString.getStrings());
    }
}
