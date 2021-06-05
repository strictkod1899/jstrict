package ru.strict.file.data;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

// TODO: сделать builder
public class UnitFile {
    private String filePath;
    private String extension;
    private byte[] content;

    public String getFilePath() {
        return filePath;
    }

    public String getExtension() {
        return extension;
    }

    public byte[] getContent() throws IOException {
        return content;
    }

    public String getBase64Content() {
        if (content == null) {
            return null;
        }
        return Base64.getEncoder().encodeToString(content);
    }

    public String getUTF8Content() {
        return getContentByEncoding("UTF-8");
    }

    public String getContentByEncoding(String encoding) {
        if (content == null) {
            return null;
        }
        try {
            return new String(content, encoding);
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void readContent() {
        try {
            content = Files.readAllBytes(Paths.get(filePath));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
