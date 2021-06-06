package ru.strict.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import ru.strict.i18n.LocalizedString;

import java.io.IOException;
import java.util.Map;

public final class LocalizedStringDeserializer extends JsonDeserializer<LocalizedString> {

    public LocalizedString deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        TypeReference<Map<String, String>> typeRef = new TypeReference<Map<String, String>>() {
        };
        return new LocalizedString((Map) jsonParser.readValueAs(typeRef));
    }
}
