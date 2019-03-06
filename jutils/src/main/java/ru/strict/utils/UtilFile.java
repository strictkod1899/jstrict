package ru.strict.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class UtilFile {
    /**
     * Создать файл в файловой системе. Если файл существует, то он будет удален
     * @param filepath Путь до файла
     * @param fileBytes Байты, которые записываются в файл
     * @throws IOException
     */
    public static void saveFile(String filepath, byte[] fileBytes) throws IOException {
        saveFile(new File(filepath), fileBytes);
    }

    /**
     * Создать файл в файловой системе. Если файл существует, то он будет удален
     * @param file Файл, который будет создан
     * @param fileBytes Байты, которые записываются в файл
     * @throws IOException
     */
    public static void saveFile(File file, byte[] fileBytes) throws IOException {
        recreateFile(file);
        FileOutputStream output = new FileOutputStream(file);
        output.write(fileBytes);
        output.flush();
        output.close();
    }

    /**
     * Создать файл. Если файл существует, то он будет перезаписан
     * @param filePath
     * @throws IOException
     */
    public static void recreateFile(String filePath) throws IOException {
        recreateFile(new File(filePath));
    }

    /**
     * Создать файл. Если файл существует, то он будет перезаписан
     * @param file
     * @throws IOException
     */
    public static void recreateFile(File file) throws IOException {
        if(file.exists()){
            file.delete();
        }
        createFileIfNotExists(file);
    }

    /**
     * Создать файл, если он не существует
     * @param filePath
     * @throws IOException
     */
    public static void createFileIfNotExists(String filePath) throws IOException {
        createFileIfNotExists(new File(filePath));
    }

    /**
     * Создать файл, если он не существует
     * @param file
     * @throws IOException
     */
    public static void createFileIfNotExists(File file) throws IOException {
        int lastSeparator = file.getAbsolutePath().lastIndexOf(File.separator);

        if(lastSeparator<0) {
            lastSeparator = file.getAbsolutePath().lastIndexOf('/');
        }

        if(lastSeparator>0) {
            String dirs = file.getAbsolutePath().substring(0, lastSeparator);
            if (!new File(dirs).exists()) {
                new File(dirs).mkdirs();
            }
        }

        if(!file.exists()) {
            file.createNewFile();
        }
    }

    /**
     * Найти любой файл в папке по части наименования
     * @param folderPath
     * @param fileNamePart
     * @return
     */
    public static File getFileByPartName(String folderPath, String fileNamePart){
        File result = null;
        File folder = new File(folderPath);
        File[] folderFiles = folder.listFiles();

        if(folderFiles != null) {
            for (File folderItem : folderFiles) {
                if (folderItem.isFile()) {
                    String filePath = folderItem.getAbsolutePath();
                    String fileName = filePath.substring(filePath.lastIndexOf(File.separator) + File.separator.length());
                    if (fileName.contains(fileNamePart)) {
                        result = folderItem;
                        break;
                    }
                }
            }
        }

        return result;
    }

    /**
     * Найти все файлы в папке по части наименования
     * @param folderPath
     * @param fileNamePart
     * @return
     */
    public static Collection<File> getFilesByPartName(String folderPath, String fileNamePart){
        Collection<File> result = new ArrayList<>();
        File folder = new File(folderPath);
        File[] folderFiles = folder.listFiles();

        if(folderFiles != null) {
            for (File folderItem : folderFiles) {
                if (folderItem.isFile()) {
                    String filePath = folderItem.getAbsolutePath();
                    String fileName = filePath.substring(filePath.lastIndexOf(File.separator) + File.separator.length());
                    if (fileName.contains(fileNamePart)) {
                        result.add(folderItem);
                    }
                }
            }
        }

        return result;
    }

    public static String getFileExtension(File file){
        return getFileExtension(file.getAbsolutePath());
    }

    public static String getFileExtension(String filePath){
        String result = null;
        int startExtensionsPosition = filePath.lastIndexOf('.');
        if(startExtensionsPosition >= 0) {
            result =  filePath.substring(startExtensionsPosition+1);
        }

        return result;
    }
}
