package ru.strict.models;

import java.util.Arrays;
import java.util.Date;

/**
 * Полное представление FileStorage
 */
public class FileStorage<ID> extends PathFileStorage<ID> {

    /**
     * Байтовое представление файла
     */
    private byte[] content;

    public FileStorage() {
        super();
    }

    public FileStorage(String filename, String extension, String displayName, String filePath, byte[] content, Date createDate, int type, int status) {
        super(filename, extension, displayName, createDate, type, status, filePath);
        this.content = content;
    }

    public FileStorage(ID id, String filename, String extension, String displayName, String filePath, byte[] content, Date createDate, int type, int status) {
        super(id, filename, extension, displayName, createDate, type, status, filePath);
        this.content = content;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FileStorage<ID> object = (FileStorage<ID>) o;
        return Arrays.equals(content, object.content);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode(content);
        return result;
    }

    @Override
    public FileStorage<ID> clone(){
        FileStorage<ID> clone = (FileStorage<ID>) super.clone();

        clone.setContent(content == null ? null : content.clone());
        return clone;
    }
    //</editor-fold>
}
