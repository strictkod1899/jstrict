package ru.strict.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ru.strict.validate.ValidateBaseValue;

public class UtilJson {

    /**
     * Пример использования:
     * UtilJson.saveToJson(this, pathToFile);
     */
    public static <O extends Object> void saveToJson(O object, String pathToJson){
        if(object == null){
            throw new IllegalArgumentException("object for write into json is NULL");
        }
        if(ValidateBaseValue.isEmptyOrNull(pathToJson)){
            throw new IllegalArgumentException("pathToJson is NULL");
        }
        try {
            if(object instanceof String){
                UtilFile.saveFile(pathToJson, object.toString());
            } else {
                ObjectMapper mapper = new ObjectMapper();
                mapper.enable(SerializationFeature.INDENT_OUTPUT);
                mapper.writeValue(new File(pathToJson), object);
            }
        }catch(IOException ex){
            throw new RuntimeException(ex);
        }
    }

    public static <O> O loadFromJson(String pathToJson, Class<O> clazz){
        if(clazz == null){
            throw new IllegalArgumentException("class for read from json is NULL");
        }
        if(ValidateBaseValue.isEmptyOrNull(pathToJson)){
            throw new IllegalArgumentException("pathToJson is NULL");
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File(pathToJson), clazz);
        }catch(IOException ex){
            throw new RuntimeException(ex);
        }
    }

    public static <O> O loadFromJson(InputStream stream, Class<O> clazz){
        if(clazz == null){
            throw new IllegalArgumentException("class for read from json is NULL");
        }
        if(stream == null){
            throw new IllegalArgumentException("stream for read from json is NULL");
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(stream, clazz);
        }catch(IOException ex){
            throw new RuntimeException(ex);
        }
    }
}
