package ru.strict.file.data;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import ru.strict.utils.FileUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UnitFile {

    private String filePath;
    private String extension;
    private byte[] content;

    public UnitFile() {
    }

    public UnitFile(String filePath) throws IOException {
        this(filePath, false);
    }

    public UnitFile(String filePath, boolean isReadFile) throws IOException {
        this.filePath = filePath;
        this.extension = FileUtil.getFileExtension(filePath);
        if(isReadFile) {
            this.content = Files.readAllBytes(Paths.get(filePath));
        }
    }

    public UnitFile(byte[] content, String extension) {
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

    public String getContentUTF8(){
        return getContentByEncoding("UTF-8");
    }

    public String getContentByEncoding(String encoding){
        if(content == null){
            return null;
        }
        try {
            return new String(content, encoding);
        }catch(UnsupportedEncodingException ex){
            throw new RuntimeException(ex);
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
