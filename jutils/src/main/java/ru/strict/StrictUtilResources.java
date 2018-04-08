package ru.strict;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Управление файлами ресурсов
 */
public class StrictUtilResources {

    /**
     * Создание физического файла относительно проекта из файла-ресурса
     * @param resourcePath
     * @return
     */
    public static File getResourceAsFile(String resourcePath) {
        StrictUtilLogger.info(StrictUtilResources.class, "getResourceAsFile - started");
        try {
            InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(resourcePath);
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
            StrictUtilLogger.info(StrictUtilResources.class, "getResourceAsFile - finished");
            return file;
        } catch (IOException ex) {
            StrictUtilLogger.error(StrictUtilResources.class, ex.getClass().toString(), ex.getMessage());
            return null;
        }
    }

    /**
     * Создание физического временного файла относительно проекта из файла-ресурса
     * @param resourcePath
     * @return
     */
    public static File getResourceAsFileTemp(String resourcePath) {
        StrictUtilLogger.info(StrictUtilResources.class, "getResourceAsFileTemp - started");
        try {
            InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(resourcePath);
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
            StrictUtilLogger.info(StrictUtilResources.class, "getResourceAsFileTemp - finished");
            return tempFile;
        } catch (IOException ex) {
            StrictUtilLogger.error(StrictUtilResources.class, ex.getClass().toString(), ex.getMessage());
            return null;
        }
    }

    /**
     * Получить файл-ресурс
     * @param pathFile Путь к файлу-ресурсу относительно папки resources
     * @return Файл-ресурс
     */
    public static File getResource(String pathFile){
        ClassLoader classLoader = StrictUtilResources.class.getClassLoader();
        try {
            return new File(classLoader.getResource(pathFile).getFile());
        }catch(java.lang.NullPointerException ex){
            StrictUtilLogger.error(StrictUtilResources.class, ex.getClass().toString(), ex.getMessage());
            return null;
        }
    }

    /**
     * Получить файл-ресурс в качестве входного потока
     * @param pathFile Путь к файлу-ресурсу относительно папки resources
     * @return Входной поток файла-ресурса
     */
    public static InputStream getResourceStream(String pathFile){
        ClassLoader classLoader = StrictUtilResources.class.getClassLoader();
        try{
            return classLoader.getResourceAsStream(pathFile);
        }catch(java.lang.NullPointerException ex){
            StrictUtilLogger.error(StrictUtilResources.class, ex.getClass().toString(), ex.getMessage());
            return null;
        }
    }
}
