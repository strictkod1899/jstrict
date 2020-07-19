package ru.strict.utils;

import ru.strict.validate.BaseValidate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Управление файлами ресурсов
 */
public class ResourcesUtil {

    /**
     * Создание физического файла относительно проекта из файла-ресурса. Текущий файл будет перезаписан
     * @param resourcePath
     * @return
     */
    public static File getResourceAsFile(String resourcePath){
        return getResourceAsFile(resourcePath, null, null);
    }

    /**
     * Создание физического файла относительно проекта из файла-ресурса
     * @param resourcePath
     * @return
     */
    public static File getResourceAsFile(String resourcePath, String targetPath){
        return getResourceAsFile(resourcePath, targetPath, null);
    }

    /**
     * Создание физического файла относительно проекта из файла-ресурса. Текущий файл будет перезаписан
     * @param resourcePath
     * @param classThisJarFile Класс текущего jar(war)-файла.
     *                         Используется для получения ресурса в нужном jar(war)-файле.
     *                         Если передать null, тогда будет использован системный ClassLoader.
     * @return
     */
    public static File getResourceAsFile(String resourcePath, Class classThisJarFile){
        return getResourceAsFile(resourcePath, null, classThisJarFile);
    }

    /**
     * Создание физического файла относительно проекта из файла-ресурса. Текущий файл будет перезаписан
     * @param resourcePath
     * @param classThisJarFile Класс текущего jar(war)-файла.
     *                         Используется для получения ресурса в нужном jar(war)-файле.
     *                         Если передать null, тогда будет использован системный ClassLoader.
     * @return
     */
    public static File getResourceAsFile(String resourcePath, String targetPath, Class classThisJarFile) {
        targetPath = BaseValidate.isEmptyOrNull(targetPath) ? resourcePath : targetPath;
        File file = new File(targetPath);

        if (!file.exists()) {
            try {
                ClassLoader classLoader = null;
                if (classThisJarFile == null) {
                    classLoader = ClassLoader.getSystemClassLoader();
                } else {
                    classLoader = classThisJarFile.getClassLoader();
                }

                try (InputStream in = classLoader.getResourceAsStream(resourcePath)) {
                    if (in == null) {
                        return null;
                    }

                    FileUtil.createFileIfNotExists(file);

                    try (FileOutputStream out = new FileOutputStream(file)) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = in.read(buffer)) != -1) {
                            out.write(buffer, 0, bytesRead);
                        }
                    }
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        return file;
    }

    /**
     * Создание физического временного файла относительно проекта из файла-ресурса
     * @param resourcePath
     * @return
     */
    public static File getResourceAsFileTemp(String resourcePath){
        return getResourceAsFileTemp(resourcePath, null);
    }

    /**
     * Создание физического временного файла относительно проекта из файла-ресурса
     * @param resourcePath
     * @param classThisJarFile Класс текущего jar(war)-файла.
     *                         Используется для получения ресурса в нужном jar(war)-файле.
     *                         Если передать null, тогда будет использован системный ClassLoader.
     * @return
     */
    public static File getResourceAsFileTemp(String resourcePath, Class classThisJarFile) {
        try {
            ClassLoader classLoader = null;
            if(classThisJarFile == null) {
                classLoader = ClassLoader.getSystemClassLoader();
            }else{
                classLoader = classThisJarFile.getClassLoader();
            }

            File tempFile = null;

            try(InputStream in = classLoader.getResourceAsStream(resourcePath)) {
                if (in == null) {
                    return null;
                }

                tempFile = File.createTempFile(String.valueOf(in.hashCode()), ".tmp");
                tempFile.deleteOnExit();

                try (FileOutputStream out = new FileOutputStream(tempFile)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = in.read(buffer)) != -1) {
                        out.write(buffer, 0, bytesRead);
                    }
                }
            }
            return tempFile;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Получить файл-ресурс
     * @param pathFile Путь к файлу-ресурсу относительно папки resources
     * @return Файл-ресурс
     */
    public static File getResource(String pathFile){
        return getResource(pathFile, null);
    }

    /**
     * Получить файл-ресурс
     * @param pathFile Путь к файлу-ресурсу относительно папки resources
     * @param classThisJarFile Класс текущего jar(war)-файла.
     *                         Используется для получения ресурса в нужном jar(war)-файле.
     *                         Если передать null, тогда будет использован системный ClassLoader.
     * @return Файл-ресурс
     */
    public static File getResource(String pathFile, Class classThisJarFile){
        ClassLoader classLoader = null;
        if(classThisJarFile == null) {
            classLoader = ClassLoader.getSystemClassLoader();
        }else{
            classLoader = classThisJarFile.getClassLoader();
        }
        try {
            return new File(classLoader.getResource(pathFile).getFile());
        }catch(java.lang.NullPointerException ex){
            throw new RuntimeException(ex);
        }
    }

    /**
     * Получить файл-ресурс в качестве входного потока
     * @param pathFile Путь к файлу-ресурсу относительно папки resources
     * @return Входной поток файла-ресурса
     */
    public static InputStream getResourceStream(String pathFile){
        return getResourceStream(pathFile, null);
    }

    /**
     * Получить файл-ресурс в качестве входного потока
     * @param pathFile Путь к файлу-ресурсу относительно папки resources
     * @param classThisJarFile Класс текущего jar(war)-файла.
     *                         Используется для получения ресурса в нужном jar(war)-файле.
     *                         Если передать null, тогда будет использован системный ClassLoader.
     * @return Входной поток файла-ресурса
     */
    public static InputStream getResourceStream(String pathFile, Class classThisJarFile){
        ClassLoader classLoader = null;
        if(classThisJarFile == null) {
            classLoader = ClassLoader.getSystemClassLoader();
        }else{
            classLoader = classThisJarFile.getClassLoader();
        }
        try{
            return classLoader.getResourceAsStream(pathFile);
        }catch(java.lang.NullPointerException ex){
            throw new RuntimeException(ex);
        }
    }
}
