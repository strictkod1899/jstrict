package ru.strict.file.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public final class JacksonObjectMapper extends ObjectMapper {

    public JacksonObjectMapper() {
        registerModule(new JavaTimeModule());
        registerModule(new StrictModule());

        enable(SerializationFeature.INDENT_OUTPUT);
    }
}
