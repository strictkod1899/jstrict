package ru.strict.file.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.strict.util.ClassUtil;
import ru.strict.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ru.strict.validate.Validator.*;

public abstract class BaseJsonFile<TARGET> implements IJsonFile<TARGET> {

    @JsonIgnore
    private ObjectMapper objectMapper;

    @JsonIgnore
    private String filePath;
    @JsonIgnore
    private Class<TARGET> targetClass;
    @JsonIgnore
    private Object content;

    /**
     * Конструктор для десериализации
     */
    protected BaseJsonFile() {}

    public BaseJsonFile(String filePath, ObjectMapper objectMapper) {
        isEmptyOrNull(filePath, "filePath");

        this.filePath = filePath;
        this.objectMapper = objectMapper;
    }

    public BaseJsonFile(String filePath, Class<TARGET> targetClass, ObjectMapper objectMapper) {
        isEmptyOrNull(filePath, "filePath");
        isNull(targetClass, "targetClass");

        this.filePath = filePath;
        this.targetClass = targetClass;
        this.objectMapper = objectMapper;
    }

    @Override
    public void loadFromFileOrInitialize() {
        if (Files.exists(Paths.get(filePath))) {
            TARGET loadedObject = readToTargetClass();
            mapJsonObject(loadedObject);
        } else {
            TARGET defaultObject = defaultInitialize();
            write(defaultObject);
        }
    }

    /**
     * Сопоставление объекта загруженного из json-файла к текущему объекту
     */
    protected abstract void mapJsonObject(TARGET loadedObject);

    /**
     * Инциализация объекта, если json-файл не существует
     */
    protected abstract TARGET defaultInitialize();

    /**
     * <pre>
     * Прочитать файл.
     * Если читаем массив, то на выходе будет объект List<Map<String, Object>>
     * Если читаем один объект, то на выходе будет объект Map<String, Object>
     * </pre>
     */
    @Override
    public Object read() {
        if (!Files.exists(Paths.get(filePath))) {
            return null;
        }
        try {
            content = objectMapper.readValue(new File(filePath), ClassUtil.castClass(Object.class));
        } catch (IOException ex) {
            throw new JsonException(ex);
        }
        return content;
    }

    @Override
    public TARGET readToTargetClass() {
        isNull(targetClass, "targetClass");

        if (!Files.exists(Paths.get(filePath))) {
            return null;
        }

        try {
            return objectMapper.readValue(new File(filePath), targetClass);
        } catch (IOException ex) {
            throw new JsonException(ex);
        }
    }

    @Override
    public void write(TARGET object) {
        try {
            objectMapper.writeValue(new File(filePath), object);
        } catch (IOException ex) {
            throw new JsonException(ex);
        }
        JsonUtil.saveToJson(object, filePath);
    }

    @Override
    public void write() {
        FileUtil.writeFile(filePath, content.toString());
    }

    public String getFilePath() {
        return filePath;
    }

    /**
     * Получить данные прочитанный из файла.
     * Обновляются только после вызова метода read()
     */
    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public Class getTargetClass() {
        return targetClass;
    }
}
