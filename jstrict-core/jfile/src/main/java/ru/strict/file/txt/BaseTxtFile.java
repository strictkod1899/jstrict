package ru.strict.file.txt;

import ru.strict.file.IFileReader;
import ru.strict.file.IFileWriter;
import ru.strict.util.FileUtil;
import ru.strict.validate.Validator;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public abstract class BaseTxtFile<SOURCE> implements IFileReader<SOURCE>, IFileWriter<SOURCE> {

    private String filePath;
    private StringBuilder content;

    public BaseTxtFile(String filePath) {
        Validator.isEmptyOrNull(filePath, "filePath");

        this.filePath = filePath;
        this.content = new StringBuilder();
    }

    protected abstract SOURCE mapToSource(StringBuilder fileContent);

    protected abstract StringBuilder mapToString(SOURCE source);

    @Override
    public SOURCE read() {
        SOURCE result = null;
        if (Files.exists(Paths.get(filePath))) {
            StringBuilder stringBuilder = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                while (reader.ready()) {
                    stringBuilder.append(reader.readLine());
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            result = mapToSource(stringBuilder);
        }
        return result;
    }

    @Override
    public void write() {
        FileUtil.writeFile(filePath, content.toString());
    }

    @Override
    public void write(SOURCE source) {
        content = mapToString(source);
        write();
    }

    public void writeToLastFile() {
        try {
            FileUtil.createFileIfNotExists(filePath);
            Files.write(Paths.get(filePath), content.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public String getContent() {
        return content.toString();
    }

    public void addContent(String text) {
        content.append(text);
    }

    public void setContent(String content) {
        this.content = new StringBuilder(content);
    }
}
