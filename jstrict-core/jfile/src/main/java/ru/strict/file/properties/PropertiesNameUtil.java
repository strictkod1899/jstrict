package ru.strict.file.properties;

import lombok.experimental.UtilityClass;

import java.io.File;
import java.util.stream.Stream;

@UtilityClass
public class PropertiesNameUtil {

    public String getFilePathWithSuffix(String filePath, String suffix) {
        return getPathWithSuffix(filePath, suffix, File.separator);
    }

    public String getResourcePathWithSuffix(String filePath, String suffix) {
        return getPathWithSuffix(filePath, suffix, "/");
    }

    public String getPathWithSuffix(String path, String suffix, String pathSeparator) {
        var separateLastIndex = getSeparateLastIndexOf(path);
        var pathToDirectory = separateLastIndex < 0 ? "" : path.substring(0, separateLastIndex);
        String fileName;
        fileName = path.substring(separateLastIndex + 1);
        fileName = fileName.substring(0, fileName.lastIndexOf(".properties"));

        return createPathWithSuffix(pathToDirectory, fileName, suffix, pathSeparator);
    }

    private int getSeparateLastIndexOf(String filePath) {
        int systemLastIndexOf = filePath.lastIndexOf(File.separator);
        int separate1LastIndexOf = filePath.lastIndexOf("/");
        int separate2LastIndexOf = filePath.lastIndexOf("\\");

        return Stream.of(systemLastIndexOf, separate1LastIndexOf, separate2LastIndexOf)
                .max(Integer::compareTo)
                .get();
    }

    private String createPathWithSuffix(String pathToDirectory, String fileName, String suffix, String pathSeparator) {
        return pathToDirectory + pathSeparator + createFileNameWithSuffix(fileName, suffix);
    }

    private String createFileNameWithSuffix(String fileName, String suffix) {
        return String.format("%s_%s.properties", fileName, suffix);
    }
}
