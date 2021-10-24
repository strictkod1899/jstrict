package ru.strict.file.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ru.strict.util.StringUtil;

import java.util.List;

public final class JacksonObjectMapper extends ObjectMapper {

    public JacksonObjectMapper() {
        super();
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        registerModule(new JavaTimeModule());
        registerModule(new StrictModule());

        enable(SerializationFeature.INDENT_OUTPUT);
    }

    public String convertToJson(Object object) {
        try {
            return writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public <T> T convertToObject(String json, Class<T> castClass) {
        if (StringUtil.isEmptyOrNull(json) || castClass == null) {
            return null;
        }

        try {
            return readValue(json, castClass);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public <T> List<T> convertToList(String json, Class<T> castClass) {
        if (StringUtil.isEmptyOrNull(json) || castClass == null) {
            return null;
        }

        TypeFactory typeFactory = TypeFactory.defaultInstance();
        try {
            return (List<T>) readValue(json, typeFactory.constructCollectionType(List.class, castClass));
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
