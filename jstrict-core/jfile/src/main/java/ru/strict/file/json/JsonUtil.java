package ru.strict.file.json;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import lombok.experimental.UtilityClass;
import ru.strict.util.FileUtil;

import static ru.strict.validate.Validator.*;

@UtilityClass
public class JsonUtil {

    private static final JacksonObjectMapper OBJECT_MAPPER = new JacksonObjectMapper();

    public static <O> void saveToJson(O object, String pathToJson) {
        isNull(object, "object");
        isEmptyOrNull(pathToJson, "pathToJson");

        try {
            if (object instanceof String) {
                FileUtil.writeFile(pathToJson, object.toString());
            } else {
                OBJECT_MAPPER.writeValue(new File(pathToJson), object);
            }
        } catch (IOException ex) {
            throw new JsonException(ex);
        }
    }

    public static <O> O loadFromJson(String pathToJson, Class<O> clazz) {
        isNull(clazz, "clazz");
        isEmptyOrNull(pathToJson, "pathToJson");

        try {
            return OBJECT_MAPPER.readValue(new File(pathToJson), clazz);
        } catch (IOException ex) {
            throw new JsonException(ex);
        }
    }

    public static <O> O loadFromJson(InputStream stream, Class<O> clazz) {
        isNull(clazz, "clazz");
        isNull(stream, "stream");

        try {
            return OBJECT_MAPPER.readValue(stream, clazz);
        } catch (IOException ex) {
            throw new JsonException(ex);
        }
    }
}
