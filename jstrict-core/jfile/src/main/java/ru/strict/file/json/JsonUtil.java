package ru.strict.file.json;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import lombok.experimental.UtilityClass;
import ru.strict.file.FileProcessingException;
import ru.strict.util.FileUtil;

import static ru.strict.validate.Validator.*;

@UtilityClass
public class JsonUtil {

    private static final JacksonObjectMapper OBJECT_MAPPER = new JacksonObjectMapper();

    public <T> void saveToJson(T object, File file) {
        isNull(object, "object");
        isNull(file, "file");

        try {
            if (object instanceof String) {
                FileUtil.writeFile(file, object.toString());
            } else {
                OBJECT_MAPPER.writeValue(file, object);
            }
        } catch (IOException ex) {
            throw new FileProcessingException(file.getAbsolutePath(), ex);
        }
    }

    public <T> T loadFromJson(String filePath, Class<T> objectClass) {
        isNull(objectClass, "objectClass");
        isEmptyOrNull(filePath, "filePath");

        try {
            return OBJECT_MAPPER.readValue(new File(filePath), objectClass);
        } catch (IOException ex) {
            throw new FileProcessingException(filePath, ex);
        }
    }

    public <T> T loadFromJson(InputStream inputStream, Class<T> objectClass) {
        isNull(objectClass, "objectClass");
        isNull(inputStream, "inputStream");

        try {
            return OBJECT_MAPPER.readValue(inputStream, objectClass);
        } catch (IOException ex) {
            throw new FileProcessingException(inputStream.toString(), ex);
        }
    }
}
