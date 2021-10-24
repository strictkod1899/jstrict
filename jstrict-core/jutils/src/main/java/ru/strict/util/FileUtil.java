package ru.strict.util;

import ru.strict.validate.Validator;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

public class FileUtil {

    public static void writeFile(String filepath, String fileContent) {
        Validator.isEmptyOrNull(filepath, "filepath");

        writeFile(new File(filepath), fileContent);
    }

    /**
     * Создать файл в файловой системе. Если файл существует, то он будет перезаписан
     *
     * @param file Файл, который будет создан
     * @param fileContent Стркоове содержимое файла
     */
    public static void writeFile(File file, String fileContent) {
        Validator.isNull(file, "file");
        Validator.isNull(fileContent, "fileContent");

        recreateFile(file);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(fileContent);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void writeFile(String filepath, byte[] fileBytes) throws IOException {
        Validator.isEmptyOrNull(filepath, "filepath");

        writeFile(new File(filepath), fileBytes);
    }

    /**
     * Создать файл в файловой системе. Если файл существует, то он будет перезаписан
     *
     * @param file Файл, который будет создан
     * @param fileBytes Байты, которые записываются в файл
     */
    public static void writeFile(File file, byte[] fileBytes) throws IOException {
        Validator.isNull(file, "file");
        Validator.isNull(fileBytes, "fileBytes");

        recreateFile(file);
        try (FileOutputStream output = new FileOutputStream(file)) {
            output.write(fileBytes);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void writeFile(File file, InputStream in) {
        Validator.isNull(file, "file");
        Validator.isNull(in, "in");

        recreateFile(file);

        try (FileOutputStream out = new FileOutputStream(file)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void recreateFile(String filePath) {
        Validator.isEmptyOrNull(filePath, "filePath");

        recreateFile(new File(filePath));
    }

    /**
     * Создать файл. Если файл существует, то он будет перезаписан
     */
    public static void recreateFile(File file) {
        Validator.isNull(file, "file");

        if (file.exists()) {
            file.delete();
        }
        createFileIfNotExists(file);
    }

    public static void createFileIfNotExists(String filePath) {
        Validator.isEmptyOrNull(filePath, "filePath");

        createFileIfNotExists(new File(filePath));
    }

    /**
     * Создать файл, если он не существует
     */
    public static void createFileIfNotExists(File file) {
        Validator.isNull(file, "file");

        try {
            createDirectory(file.getAbsolutePath());

            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void createDirectory(String filePath) {
        Validator.isNull(filePath, "filePath");

        String directoryPath = getDirectoryPath(filePath);
        File directoryFile = new File(directoryPath);
        if (!directoryFile.exists()) {
            directoryFile.mkdirs();
        }
    }

    public static String getDirectoryPath(String filePath) {
        Validator.isNull(filePath, "filePath");

        int lastSeparator = getLastSeparatorIndex(filePath);

        String dirs;
        if (lastSeparator > 0) {
            dirs = filePath.substring(0, lastSeparator);
        } else {
            dirs = filePath;
        }

        return dirs;
    }

    public static int getLastSeparatorIndex(String filePath) {
        Validator.isNull(filePath, "filePath");

        int lastSeparator = filePath.lastIndexOf(File.separator);

        if (lastSeparator < 0) {
            lastSeparator = filePath.lastIndexOf('/');
        }

        return lastSeparator;
    }

    /**
     * Найти любой файл в папке по указанной части наименования
     */
    public static File getFileByPartName(String folderPath, String fileNamePart) {
        Validator.isEmptyOrNull(folderPath, "folderPath");
        Validator.isNull(fileNamePart, "fileNamePart");

        File result = null;
        File folder = new File(folderPath);
        File[] folderFiles = folder.listFiles();

        if (folderFiles != null) {
            for (File folderItem : folderFiles) {
                if (folderItem.isFile()) {
                    String filePath = folderItem.getAbsolutePath();
                    String fileName =
                            filePath.substring(filePath.lastIndexOf(File.separator) + File.separator.length());
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
     * Найти все файлы в папке по указанной части наименования
     */
    public static Collection<File> getFilesByPartName(String folderPath, String fileNamePart) {
        Validator.isEmptyOrNull(folderPath, "folderPath");
        Validator.isNull(fileNamePart, "fileNamePart");

        Collection<File> result = new ArrayList<>();
        File folder = new File(folderPath);
        File[] folderFiles = folder.listFiles();

        if (folderFiles != null) {
            for (File folderItem : folderFiles) {
                if (folderItem.isFile()) {
                    String filePath = folderItem.getAbsolutePath();
                    String fileName =
                            filePath.substring(filePath.lastIndexOf(File.separator) + File.separator.length());
                    if (fileName.contains(fileNamePart)) {
                        result.add(folderItem);
                    }
                }
            }
        }

        return result;
    }

    public static String getFileExtension(File file) {
        Validator.isNull(file, "file");

        return getFileExtension(file.getAbsolutePath());
    }

    public static String getFileExtension(String filePath) {
        Validator.isNull(filePath, "filePath");

        String result = null;
        int startExtensionsPosition = filePath.lastIndexOf('.');
        if (startExtensionsPosition >= 0) {
            result = filePath.substring(startExtensionsPosition + 1);
        }

        return result;
    }

    public static void removeIfExists(String filePath) {
        Validator.isNull(filePath, "filePath");

        try {
            Path targetFilePath = Paths.get(filePath);
            Files.deleteIfExists(targetFilePath);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}