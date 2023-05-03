package ru.strict.file.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ru.strict.file.FileProcessingException;
import ru.strict.validate.CommonValidator;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class JacksonObjectMapper extends ObjectMapper {

    public JacksonObjectMapper() {
        super();
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        registerModule(new JavaTimeModule());
        registerModule(new StrictModule());

        enable(SerializationFeature.INDENT_OUTPUT);
    }

    public <T> T readFromFile(String filePath, Class<T> castClass) {
        try {
            return this.readValue(new File(filePath), castClass);
        } catch (IOException ex) {
            throw new FileProcessingException(filePath, ex);
        }
    }

    public void writeToFile(String filePath, Object objectJson) {
        try {
            this.writeValue(new File(filePath), objectJson);
        } catch (IOException ex) {
            throw new FileProcessingException(filePath, ex);
        }
    }

    public <T> T readFromResource(String resourceFilePath, Class<T> castClass) {
        try (InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(resourceFilePath);) {
            String json = readToJson(inputStream);

            return convertToObject(json, castClass);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public <T> List<T> readListFromResource(String resourceFilePath, Class<T> castClass) {
        try (InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(resourceFilePath);) {
            String json = readToJson(inputStream);

            return convertToList(json, castClass);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String convertToJson(Object object) {
        try {
            return writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public <T> T convertToObject(String json, Class<T> castClass) {
        if (CommonValidator.isNullOrEmpty(json) || castClass == null) {
            return null;
        }

        try {
            return readValue(json, castClass);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public <T> List<T> convertToList(String json, Class<T> castClass) {
        if (CommonValidator.isNullOrEmpty(json) || castClass == null) {
            return Collections.emptyList();
        }

        TypeFactory typeFactory = TypeFactory.defaultInstance();
        try {
            return readValue(json, typeFactory.constructCollectionType(List.class, castClass));
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    private String readToJson(InputStream inputStream) throws IOException {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            for (int length; (length = inputStream.read(buffer)) != -1; ) {
                outputStream.write(buffer, 0, length);
            }
            return outputStream.toString("UTF-8");
        }
    }
}
