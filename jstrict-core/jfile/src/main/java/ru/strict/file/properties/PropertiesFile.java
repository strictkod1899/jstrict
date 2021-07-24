package ru.strict.file.properties;

import lombok.Getter;
import ru.strict.utils.PropertiesUtil;
import ru.strict.validate.CommonValidate;
import ru.strict.validate.Validator;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Getter
public class PropertiesFile {
    private String filePath;
    private String filePathWithSuffix;
    private Path path;
    private Path pathWithSuffix;
    private String suffix;

    private InputStream inputStream;

    public PropertiesFile(String filePath) {
        this(filePath, null);
    }

    public PropertiesFile(String filePath, String suffix) {
        init(filePath, suffix);
    }

    public PropertiesFile(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String readValue(String key) {
        return readValue(key, null, null);
    }

    public String readValueToUTF8(String key) {
        return readValue(key, null, "UTF-8");
    }

    public String readValueToUTF8(String key, String fileEncoding) {
        return readValue(key, fileEncoding, "UTF-8");
    }

    public String readValue(String key, String fileEncoding, String targetEncoding) {
        String value = null;

        if (inputStream != null) {
            value = PropertiesUtil.getValue(inputStream, key, fileEncoding, targetEncoding);
        } else {
            if (Files.exists(pathWithSuffix)) {
                value = PropertiesUtil.getValue(getFilePathWithSuffix(), key, fileEncoding, targetEncoding);
            }
            if (CommonValidate.isEmptyOrNull(value) && Files.exists(path)) {
                value = PropertiesUtil.getValue(getFilePath(), key, fileEncoding, targetEncoding);
            }
        }
        return value;
    }

    protected void reload() {
        init(filePath, suffix);
    }

    private void init(String propertiesFile, String suffix) {
        Validator.isEmptyOrNull(propertiesFile, "propertiesFile");

        String absoluteFilePath = new File(propertiesFile).getAbsolutePath();
        int separateLastIndex = getSeparateLastIndexOf(absoluteFilePath);
        String pathToDirectory = absoluteFilePath.substring(0, separateLastIndex);
        String fileName;
        fileName = absoluteFilePath.substring(separateLastIndex + 1);
        fileName = fileName.substring(0, fileName.lastIndexOf(".properties"));

        this.suffix = suffix;
        this.filePath = createFilePath(pathToDirectory, fileName);
        this.filePathWithSuffix = createFilePathWithSuffix(pathToDirectory, fileName, suffix);
        this.path = Paths.get(this.filePath);
        this.pathWithSuffix = Paths.get(this.filePathWithSuffix);
    }

    private int getSeparateLastIndexOf(String filePath) {
        int systemLastIndexOf = filePath.lastIndexOf(File.separator);
        int separate1LastIndexOf = filePath.lastIndexOf("/");
        int separate2LastIndexOf = filePath.lastIndexOf("\\");

        return Stream.of(systemLastIndexOf, separate1LastIndexOf, separate2LastIndexOf)
                .max(Integer::compareTo)
                .get();
    }

    private String createFileNameWithSuffix(String fileName, String suffix) {
        return CommonValidate.isEmptyOrNull(suffix)
                ? createFileName(fileName)
                : String.format("%s_%s.properties", fileName, suffix);
    }

    private String createFileName(String fileName) {
        return String.format("%s.properties", fileName);
    }

    private String createFilePathWithSuffix(String pathToDirectory, String fileName, String suffix) {
        return CommonValidate.isEmptyOrNull(suffix)
                ? createFilePath(pathToDirectory, fileName)
                : String.format("%s%s%s", pathToDirectory, File.separator, createFileNameWithSuffix(fileName, suffix));
    }

    private String createFilePath(String pathToDirectory, String fileName) {
        return String.format("%s%s%s", pathToDirectory, File.separator, createFileName(fileName));
    }
}
