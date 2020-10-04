package ru.strict.file.json;

import com.fasterxml.jackson.databind.module.SimpleModule;
import ru.strict.i18n.LocalizedString;
import ru.strict.json.LocalizedStringDeserializer;
import ru.strict.json.LocalizedStringSerializer;

public class StrictModule extends SimpleModule {
    public StrictModule() {
        addSerializer(LocalizedString.class, new LocalizedStringSerializer());
        addDeserializer(LocalizedString.class, new LocalizedStringDeserializer());
    }
}
