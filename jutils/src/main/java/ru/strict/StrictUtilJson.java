package ru.strict;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StrictUtilJson {

    public static <O extends Object> void saveToJson(O object, String pathToJson){
        StrictUtilLogger.info(StrictUtilJson.class, "saveToJson - started");
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(pathToJson), object);
            StrictUtilLogger.info(StrictUtilJson.class, "saveToJson - finished");
        }catch(IOException ex){
            StrictUtilLogger.error(StrictUtilJson.class
                    , String.format("Error a saving object to json-file \n %s - %s"
                            , ex.getClass().toString(), ex.getMessage()));
        }
    }

    public static <O> O loadFromJson(String pathToJson, Class<O> clazz){
        StrictUtilLogger.info(StrictUtilJson.class, "loadFromJson - started");
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File(pathToJson), clazz);
        }catch(IOException ex){
            StrictUtilLogger.error(StrictUtilJson.class
                    , String.format("Error a loading object to json-file \n %s - %s"
                            , ex.getClass().toString(), ex.getMessage()));
            return null;
        }
    }
}
