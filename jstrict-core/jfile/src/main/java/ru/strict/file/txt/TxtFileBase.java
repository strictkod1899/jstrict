package ru.strict.file.txt;

import ru.strict.file.IFileReader;
import ru.strict.file.IFileWriter;
import ru.strict.utils.FileUtil;
import ru.strict.validate.ValidateBaseValue;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class TxtFileBase<SOURCE> implements IFileReader<SOURCE>, IFileWriter<SOURCE> {

    private String filePath;
    private String content;

    public TxtFileBase(String filePath) {
        if(ValidateBaseValue.isEmptyOrNull(filePath)){
            throw new IllegalArgumentException("filePath is NULL");
        }
        this.filePath = filePath;
    }

    protected abstract SOURCE mapToSource(String fileContent);
    protected abstract String mapToString(SOURCE source);

    @Override
    public SOURCE read() {
        SOURCE result = null;
        if(Files.exists(Paths.get(filePath))) {
            StringBuilder stringBuilder = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                while (reader.ready()) {
                    stringBuilder.append(reader.readLine());
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            result = mapToSource(stringBuilder.toString());
            content = stringBuilder.toString();
        }
        return result;
    }

    @Override
    public void write() {
        try{
            FileUtil.saveFile(filePath, content);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void write(SOURCE source) {
        content = mapToString(source);
        write();
    }

    protected BufferedReader createReader() throws FileNotFoundException {
        return new BufferedReader(new FileReader(filePath));
    }

    public String getFilePath() {
        return filePath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
