package ru.strict.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class UtilJson {

    /**
     * Пример использования:
     * UtilJson.saveToJson(this, pathToFile);
     */
    public static <O extends Object> void saveToJson(O object, String pathToJson){
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(pathToJson), object);
        }catch(IOException ex){
            throw new RuntimeException(ex);
        }
    }

    public static <O> O loadFromJson(String pathToJson, Class<O> clazz){
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File(pathToJson), clazz);
        }catch(IOException ex){
            throw new RuntimeException(ex);
        }
    }

    public static <O> O loadFromJson(InputStream stream, Class<O> clazz){
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(stream, clazz);
        }catch(IOException ex){
            throw new RuntimeException(ex);
        }
    }
}
