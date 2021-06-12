package ru.strict.file.properties;

import ru.strict.validate.CommonValidate;
import ru.strict.validate.Validator;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class PropertiesFile {

    private String filePath;
    private String fileName;
    private String pathToDirectory;
    private String filePathWithSuffix;
    private String fileNameWithSuffix;
    private String suffix;

    public PropertiesFile(String filePath) {
        this(filePath, null);
    }

    public PropertiesFile(String filePath, String suffix) {
        init(filePath, suffix);
    }

    public String readValue(String key) {
        return readValue(key, null, null);
    }

    public String readValueToUTF8(String key) {
        return readValue(key, null, "UTF-8");
    }

    public String readValueToUTF8(String key, String encodingFile) {
        return readValue(key, encodingFile, "UTF-8");
    }

    public String readValue(String key, String encodingFile, String encodingOutput) {
        String result = null;
        if (Files.exists(Paths.get(getFilePathWithSuffix()))) {
            result = PropertiesUtil.getValue(getFilePathWithSuffix(), key, encodingFile, encodingOutput);
        }
        if (CommonValidate.isEmptyOrNull(result)) {
            if (Files.exists(Paths.get(getFilePath()))) {
                result = PropertiesUtil.getValue(getFilePath(), key, encodingFile, encodingOutput);
            }
        }
        return result;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getPathToDirectory() {
        return pathToDirectory;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileNameWithSuffix() {
        return fileNameWithSuffix;
    }

    public String getFilePathWithSuffix() {
        return filePathWithSuffix;
    }

    public String getSuffix() {
        return suffix;
    }

    protected void reload() {
        init(filePath, suffix);
    }

    private void init(String filePath, String suffix) {
        Validator.isEmptyOrNull(filePath, "filePath");

        String absoluteFilePath = new File(filePath).getAbsolutePath();
        String fileName = filePath;
        if (fileName.endsWith(".properties")) {
            fileName = fileName.substring(0, fileName.lastIndexOf(".properties"));
        }

        if (fileName.contains(File.separator)) {
            fileName = fileName.substring(fileName.lastIndexOf(File.separator) + 1);
        }
        if (fileName.contains("/")) {
            fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
        }
        if (fileName.contains("\\")) {
            fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
        }

        this.suffix = suffix;
        this.fileName = createFileName(fileName);
        this.fileNameWithSuffix = createFileNameWithSuffix(fileName, suffix);
        this.pathToDirectory = absoluteFilePath.substring(0, absoluteFilePath.lastIndexOf(File.separator));
        this.filePath = createFilePath(pathToDirectory, fileName);
        this.filePathWithSuffix = createFilePathWithSuffix(pathToDirectory, fileName, suffix);
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

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof PropertiesFile) {
            PropertiesFile object = (PropertiesFile) obj;
            return Objects.equals(pathToDirectory, object.pathToDirectory)
                    && Objects.equals(fileName, object.fileName)
                    && Objects.equals(suffix, object.suffix);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(pathToDirectory, fileName, suffix);
    }
}
