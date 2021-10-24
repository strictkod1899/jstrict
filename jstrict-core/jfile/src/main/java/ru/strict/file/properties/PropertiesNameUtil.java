package ru.strict.file.properties;

import lombok.experimental.UtilityClass;

import java.io.File;
import java.util.stream.Stream;

@UtilityClass
public class PropertiesNameUtil {

    public String getFilePathWithSuffix(String filePath, String suffix) {
        var separateLastIndex = getSeparateLastIndexOf(filePath);
        var pathToDirectory = separateLastIndex < 0 ? "" : filePath.substring(0, separateLastIndex);
        String fileName;
        fileName = filePath.substring(separateLastIndex + 1);
        fileName = fileName.substring(0, fileName.lastIndexOf(".properties"));

        return createFilePathWithSuffix(pathToDirectory, fileName, suffix);
    }

    private int getSeparateLastIndexOf(String filePath) {
        int systemLastIndexOf = filePath.lastIndexOf(File.separator);
        int separate1LastIndexOf = filePath.lastIndexOf("/");
        int separate2LastIndexOf = filePath.lastIndexOf("\\");

        return Stream.of(systemLastIndexOf, separate1LastIndexOf, separate2LastIndexOf)
                .max(Integer::compareTo)
                .get();
    }

    private String createFilePathWithSuffix(String pathToDirectory, String fileName, String suffix) {
        return pathToDirectory + File.separator + createFileNameWithSuffix(fileName, suffix);
    }

    private String createFileNameWithSuffix(String fileName, String suffix) {
        return String.format("%s_%s.properties", fileName, suffix);
    }
}
