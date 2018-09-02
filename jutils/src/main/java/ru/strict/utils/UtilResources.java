package ru.strict.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Управление файлами ресурсов
 */
public class UtilResources {

    /**
     * Создание физического файла относительно проекта из файла-ресурса
     * @param resourcePath
     * @return
     */
    public static File getResourceAsFile(String resourcePath){
        return getResourceAsFile(resourcePath, null);
    }

    /**
     * Создание физического файла относительно проекта из файла-ресурса
     * @param resourcePath
     * @param classThisJarFile Класс текущего jar(war)-файла.
     *                         Используется для получения ресурса в нужном jar(war)-файле.
     *                         Если передать null, тогда будет использован системный ClassLoader.
     * @return
     */
    public static File getResourceAsFile(String resourcePath, Class classThisJarFile) {
        UtilLogger.info(UtilResources.class, "getResourceAsFile - started");
        try {
            ClassLoader classLoader = null;
            if(classThisJarFile == null) {
                classLoader = ClassLoader.getSystemClassLoader();
            }else{
                classLoader = classThisJarFile.getClassLoader();
            }

            InputStream in = classLoader.getResourceAsStream(resourcePath);
            if (in == null) {
                return null;
            }

            int lastSeparator = resourcePath.lastIndexOf(File.separator);
            if(lastSeparator<0)
                lastSeparator = resourcePath.lastIndexOf('/');

            if(lastSeparator>0) {
                String dirs = resourcePath.substring(0, lastSeparator);
                if (!new File(dirs).exists())
                    new File(dirs).mkdirs();
            }

            File file = new File(resourcePath);
            if(!file.exists())
                file.createNewFile();

            try (FileOutputStream out = new FileOutputStream(file)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
            UtilLogger.info(UtilResources.class, "getResourceAsFile - finished");
            return file;
        } catch (IOException ex) {
            UtilLogger.error(UtilResources.class, ex.getClass().toString(), ex.getMessage());
            return null;
        }
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
        UtilLogger.info(UtilResources.class, "getResourceAsFileTemp - started");
        try {
            ClassLoader classLoader = null;
            if(classThisJarFile == null) {
                classLoader = ClassLoader.getSystemClassLoader();
            }else{
                classLoader = classThisJarFile.getClassLoader();
            }
            InputStream in = classLoader.getResourceAsStream(resourcePath);
            if (in == null) {
                return null;
            }

            File tempFile = File.createTempFile(String.valueOf(in.hashCode()), ".tmp");
            tempFile.deleteOnExit();

            try (FileOutputStream out = new FileOutputStream(tempFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
            UtilLogger.info(UtilResources.class, "getResourceAsFileTemp - finished");
            return tempFile;
        } catch (IOException ex) {
            UtilLogger.error(UtilResources.class, ex.getClass().toString(), ex.getMessage());
            return null;
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
            UtilLogger.error(UtilResources.class, ex.getClass().toString(), ex.getMessage());
            return null;
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
            UtilLogger.error(UtilResources.class, ex.getClass().toString(), ex.getMessage());
            return null;
        }
    }
}
