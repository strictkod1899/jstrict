package ru.strict.office.doc;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import ru.strict.office.OfficeFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WordFile extends OfficeFile<XWPFDocument, WordFormat> {

    public WordFile(String filePath) {
        super(filePath);
    }

    public WordFile(String filePath, WordFormat format) {
        super(filePath, format);
    }

    @Override
    protected XWPFDocument initializeSource() {
        try {
            XWPFDocument source = null;
            if(!Files.exists(Paths.get(getFilePath()))){
                source = createWorkbook(null);
                source.write(new FileOutputStream(getFilePath()));
            }else{
                source = createWorkbook(getFilePath());
            }

            return source;
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    private XWPFDocument createWorkbook(String filePath){
        XWPFDocument source = null;
        try {
            if (filePath == null) {
                source = new XWPFDocument();
            } else {
                source = new XWPFDocument(new FileInputStream(filePath));
            }
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }

        return source;
    }

    @Override
    protected WordFormat getFormatByCaption(String format) {
        WordFormat resultFormat = WordFormat.getByCaption(format);
        if(resultFormat == null){
            resultFormat = WordFormat.DOC;
        }
        return resultFormat;
    }

    @Override
    public void recreateFile() {
        try {
            Files.deleteIfExists(Paths.get(getFilePath()));
            initializeSource().close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void write() {
        try {
            source.write(new FileOutputStream(getFilePath()));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void close() throws Exception {
        source.close();
        source = null;
    }
}
