package ru.strict.file.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.strict.utils.UtilClass;
import ru.strict.utils.UtilJson;
import ru.strict.validates.ValidateBaseValue;

import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class JsonFileBase<TARGET> implements IJsonFile<TARGET> {

    @JsonIgnore
    private String filePath;
    @JsonIgnore
    private Class<TARGET> targetClass;
    @JsonIgnore
    private Object content;

    public JsonFileBase(String filePath) {
        if(ValidateBaseValue.isEmptyOrNull(filePath)){
            throw new IllegalArgumentException("filePath is NULL");
        }
        this.filePath = filePath;
    }

    public JsonFileBase(String filePath, Class<TARGET> targetClass) {
        if(ValidateBaseValue.isEmptyOrNull(filePath)){
            throw new IllegalArgumentException("filePath is NULL");
        }
        if(targetClass == null){
            throw new IllegalArgumentException("targetClass is NULL");
        }
        this.filePath = filePath;
        this.targetClass = targetClass;
    }

    @Override
    public void loadFromFileOrInitialize(){
        if(Files.exists(Paths.get(filePath))){
            TARGET loadedObject = readToTargetClass();
            mapJsonObject(loadedObject);
        }else{
            write(defaultInitialize());
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
     * @return
     */
    @Override
    public Object read(){
        if(!Files.exists(Paths.get(filePath))){
            return null;
        }
        content = UtilJson.loadFromJson(filePath, UtilClass.castClass(Object.class));
        return content;
    }

    @Override
    public TARGET readToTargetClass(){
        if(targetClass == null){
            throw new NullPointerException("targetClass is NULL");
        }
        if(!Files.exists(Paths.get(filePath))){
            return null;
        }
        return UtilJson.loadFromJson(filePath, targetClass);
    }

    @Override
    public void write(TARGET object){
        UtilJson.saveToJson(object, filePath);
    }

    @Override
    public void write(){
        UtilJson.saveToJson(content, filePath);
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
