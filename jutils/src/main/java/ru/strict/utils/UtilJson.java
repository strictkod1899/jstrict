package ru.strict.utils;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UtilJson {

    public static <O extends Object> void saveToJson(O object, String pathToJson){
        UtilLogger.info(UtilJson.class, "saveToJson - started");
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(pathToJson), object);
            UtilLogger.info(UtilJson.class, "saveToJson - finished");
        }catch(IOException ex){
            UtilLogger.error(UtilJson.class
                    , String.format("Error a saving object to json-file \n %s - %s"
                            , ex.getClass().toString(), ex.getMessage()));
        }
    }

    public static <O> O loadFromJson(String pathToJson, Class<O> clazz){
        UtilLogger.info(UtilJson.class, "loadFromJson - started");
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File(pathToJson), clazz);
        }catch(IOException ex){
            UtilLogger.error(UtilJson.class
                    , String.format("Error a loading object to json-file \n %s - %s"
                            , ex.getClass().toString(), ex.getMessage()));
            return null;
        }
    }
}
