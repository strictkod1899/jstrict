package ru.strict.file.json;

public class JsonFile<TARGET> extends JsonFileBase<TARGET> {

    public JsonFile(String filePath) {
        super(filePath);
    }

    public JsonFile(String filePath, Class<TARGET> targetClass) {
        super(filePath, targetClass);
    }

    @Override
    protected void mapJsonObject(TARGET loadedObject) {}

    @Override
    protected TARGET defaultInitialize() {
        return null;
    }
}
