package ru.strict.file.json;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import ru.strict.utils.FileUtil;

import static ru.strict.validate.Validator.*;

public final class JsonUtil {

    private static final JacksonObjectMapper OBJECT_MAPPER = new JacksonObjectMapper();

    private JsonUtil() {}

    public static <O> void saveToJson(O object, String pathToJson) {
        isNull(object, "object");
        isEmptyOrNull(pathToJson, "pathToJson");

        try {
            if (object instanceof String) {
                FileUtil.saveFile(pathToJson, object.toString());
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
