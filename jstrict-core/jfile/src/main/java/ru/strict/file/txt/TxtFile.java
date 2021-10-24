package ru.strict.file.txt;

import ru.strict.file.FileProcessingException;
import ru.strict.file.ReadonlyException;
import ru.strict.util.FileUtil;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Supplier;

public class TxtFile {
    private final File file;
    private final Path path;
    private final Supplier<InputStream> inputStreamSupplier;

    public TxtFile(String filePath) {
        this.file = new File(filePath);
        this.path = Paths.get(file.getAbsolutePath());
        this.inputStreamSupplier = null;
    }

    public TxtFile(Supplier<InputStream> inputStreamSupplier) {
        this.file = null;
        this.path = null;
        this.inputStreamSupplier = inputStreamSupplier;
    }

    /**
     * Записать переданный текст в конец файла
     */
    public void writeAppend(String text) {
        var currentContent = read();
        var newContent = currentContent + text;
        write(newContent);
    }

    public String read() {
        return file == null ? readByInputStream() : readByFilePath();
    }

    public void write(String text) {
        if (file == null) {
            throw new ReadonlyException();
        }

        FileUtil.writeFile(file, text);
    }

    private String readByInputStream() {
        var fileContent = new StringBuilder();
        try (var inputStream = inputStreamSupplier.get()) {
            for (int ch; (ch = inputStream.read()) != -1; ) {
                fileContent.append((char) ch);
            }
        } catch (Exception ex) {
            throw new FileProcessingException(ex);
        }
        return fileContent.toString();
    }

    private String readByFilePath() {
        if (!Files.exists(path)) {
            return null;
        }

        var fileContent = new StringBuilder();
        try (var reader = new BufferedReader(new java.io.FileReader(file))) {
            while (reader.ready()) {
                fileContent.append(reader.readLine());
            }
        } catch (Exception ex) {
            throw new FileProcessingException(file.getAbsolutePath(), ex);
        }

        return fileContent.toString();
    }
}
