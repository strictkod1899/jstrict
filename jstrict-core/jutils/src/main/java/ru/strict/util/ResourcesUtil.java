package ru.strict.util;

import ru.strict.exception.ResourceNotFoundException;
import ru.strict.validate.Validator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Управление файлами ресурсов
 */
public class ResourcesUtil {

    public static File getResourceAsFile(String resourcePath) {
        return getResourceAsFile(resourcePath, resourcePath, null);
    }

    public static File getResourceAsFile(String resourcePath, String targetPath) {
        return getResourceAsFile(resourcePath, targetPath, null);
    }

    public static File getResourceAsFile(String resourcePath, Class classThisJarFile) {
        return getResourceAsFile(resourcePath, resourcePath, classThisJarFile);
    }

    /**
     * Создание физического файла из файла-ресурса. Если файл уже существует, тогда он не будет перезаписан
     *
     * @param resourcePath Путь до файла в ресурсах
     * @param targetPath Путь по которому будет распакован файл из ресурсов
     * @param classThisJarFile Класс текущего jar-файла
     * Используется для получения ресурса в нужном jar-файле
     * Если передать null, тогда будет использован системный ClassLoader
     */
    public static File getResourceAsFile(String resourcePath, String targetPath, Class<?> classThisJarFile) {
        Validator.isEmptyOrNull(resourcePath, "resourcePath");
        Validator.isEmptyOrNull(targetPath, "targetPath");

        File file = new File(targetPath);

        if (!file.exists()) {
            ClassLoader classLoader = classThisJarFile == null
                    ? ClassLoader.getSystemClassLoader()
                    : classThisJarFile.getClassLoader();

            try (InputStream in = classLoader.getResourceAsStream(resourcePath)) {
                if (in == null) {
                    return null;
                }

                FileUtil.createFileIfNotExists(file);

                FileUtil.writeFile(file, in);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        return file;
    }

    public static File getResourceAsTempFile(String resourcePath) {
        return getResourceAsTempFile(resourcePath, null);
    }

    public static File getResourceAsTempFile(String resourcePath, Class<?> classThisJarFile) {
        Validator.isEmptyOrNull(resourcePath, "resourcePath");

        ClassLoader classLoader = classThisJarFile == null
                ? ClassLoader.getSystemClassLoader()
                : classThisJarFile.getClassLoader();

        try (InputStream in = classLoader.getResourceAsStream(resourcePath)) {
            if (in == null) {
                return null;
            }

            File tempFile = File.createTempFile(String.valueOf(resourcePath.hashCode()), ".tmp");
            tempFile.deleteOnExit();

            FileUtil.writeFile(tempFile, in);

            return tempFile;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static File getResourceOrThrow(String resourcePath) {
        return getResourceOrThrow(resourcePath, null);
    }

    /**
     * Получить файл-ресурс
     *
     * @param resourcePath Путь к файлу-ресурсу относительно папки resources
     * @param classThisJarFile Класс текущего jar(war)-файла.
     * Используется для получения ресурса в нужном jar(war)-файле.
     * Если передать null, тогда будет использован системный ClassLoader.
     * @return Файл-ресурс
     */
    public static File getResourceOrThrow(String resourcePath, Class<?> classThisJarFile) {
        File file = getResource(resourcePath, classThisJarFile);

        if (file == null) {
            throw new ResourceNotFoundException(resourcePath);
        }

        return file;
    }

    public static File getResource(String pathFile) {
        return getResource(pathFile, null);
    }

    /**
     * Получить файл-ресурс
     *
     * @param resourcePath Путь к файлу-ресурсу относительно папки resources
     * @param classThisJarFile Класс текущего jar(war)-файла.
     * Используется для получения ресурса в нужном jar(war)-файле.
     * Если передать null, тогда будет использован системный ClassLoader.
     * @return Файл-ресурс
     */
    public static File getResource(String resourcePath, Class<?> classThisJarFile) {
        Validator.isEmptyOrNull(resourcePath, "resourcePath");

        ClassLoader classLoader = classThisJarFile == null
                ? ClassLoader.getSystemClassLoader()
                : classThisJarFile.getClassLoader();

        URL fileUrl = classLoader.getResource(resourcePath);
        return fileUrl == null ? null : new File(fileUrl.getFile());
    }

    public static InputStream getResourceStream(String resourcePath) {
        return getResourceStream(resourcePath, null);
    }

    /**
     * Получить файл-ресурс в качестве входного потока
     *
     * @param resourcePath Путь к файлу-ресурсу относительно папки resources
     * @param classThisJarFile Класс текущего jar(war)-файла.
     * Используется для получения ресурса в нужном jar(war)-файле.
     * Если передать null, тогда будет использован системный ClassLoader.
     * @return Входной поток файла-ресурса
     */
    public static InputStream getResourceStream(String resourcePath, Class<?> classThisJarFile) {
        Validator.isEmptyOrNull(resourcePath, "resourcePath");

        ClassLoader classLoader = classThisJarFile == null
                ? ClassLoader.getSystemClassLoader()
                : classThisJarFile.getClassLoader();

        return classLoader.getResourceAsStream(resourcePath);
    }
}
