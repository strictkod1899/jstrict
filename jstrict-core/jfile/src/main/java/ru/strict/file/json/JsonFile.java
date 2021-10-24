package ru.strict.file.json;

import com.fasterxml.jackson.databind.ObjectMapper;

@Deprecated
public class JsonFile<TARGET> extends BaseJsonFile<TARGET> {

    public JsonFile(String filePath, ObjectMapper objectMapper) {
        super(filePath, objectMapper);
    }

    public JsonFile(String filePath, Class<TARGET> targetClass, ObjectMapper objectMapper) {
        super(filePath, targetClass, objectMapper);
    }

    @Override
    protected void mapJsonObject(TARGET loadedObject) {
    }

    @Override
    protected TARGET defaultInitialize() {
        return null;
    }
}
