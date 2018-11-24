package ru.strict.file.data;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import ru.strict.utils.UtilFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FormFile {

    private String filePath;
    private String extension;
    private byte[] content;

    public FormFile() {
    }

    public FormFile(String filePath) throws IOException {
        this(filePath, false);
    }

    public FormFile(String filePath, boolean isReadFile) throws IOException {
        this.filePath = filePath;
        this.extension = UtilFile.getFileExtension(filePath);
        if(isReadFile) {
            this.content = Files.readAllBytes(Paths.get(filePath));
        }
    }

    public FormFile(byte[] content, String extension) {
        this.content = content;
        this.extension = extension;
    }

    public byte[] getContent() throws IOException {
        if(content == null){
            content = Files.readAllBytes(Paths.get(filePath));
        }
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getContentBase64(){
        if(content == null){
            return null;
        }
        return Base64.encode(content);
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
