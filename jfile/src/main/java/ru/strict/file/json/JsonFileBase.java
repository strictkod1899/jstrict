package ru.strict.file.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.strict.utils.UtilClass;
import ru.strict.utils.UtilJson;
import ru.strict.validates.ValidateBaseValue;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public abstract class JsonFileBase<TARGET> implements IJsonFile<TARGET> {

    @JsonIgnore
    private String filePath;
    @JsonIgnore
    private Class<TARGET> targetClass;
    @JsonIgnore
    private List<Map<String, Object>> source;

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

    @Override
    public List<Map<String, Object>> read(){
        if(!Files.exists(Paths.get(filePath))){
            return null;
        }
        source = UtilJson.loadFromJson(filePath, UtilClass.castClass(List.class));
        return source;
    }

    @Override
    public TARGET readToTargetClass(){
        if(targetClass == null || !Files.exists(Paths.get(filePath))){
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
        UtilJson.saveToJson(source, filePath);
    }

    public String getFilePath() {
        return filePath;
    }

    public List<Map<String, Object>> getSource() {
        return source;
    }

    public Class getTargetClass() {
        return targetClass;
    }
}
