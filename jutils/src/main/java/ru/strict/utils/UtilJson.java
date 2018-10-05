package ru.strict.utils;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UtilJson {

    public static <O extends Object> void saveToJson(O object, String pathToJson){
        try {
            ObjectMapper mapper = new ObjectMapper();
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
}
