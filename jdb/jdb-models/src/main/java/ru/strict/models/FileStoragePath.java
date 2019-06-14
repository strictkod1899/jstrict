package ru.strict.models;

import java.util.Date;
import java.util.Objects;

/**
 * Представление FileStorage с путем до файла, игнорируя атрибут content
 */
public class FileStoragePath<ID> extends FileStorageBase<ID> {
    /**
     * Путь до файла
     */
    private String filePath;

    public FileStoragePath() {
        super();
    }

    public FileStoragePath(String filename, String extension, String displayName, Date createDate, int type, int status, String filePath) {
        super(filename, extension, displayName, createDate, type, status);
        this.filePath = filePath;
    }

    public FileStoragePath(ID id, String filename, String extension, String displayName, Date createDate, int type, int status, String filePath) {
        super(id, filename, extension, displayName, createDate, type, status);
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FileStoragePath<ID> object = (FileStoragePath<ID>) o;
        return Objects.equals(filePath, object.filePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), filePath);
    }

    @Override
    public FileStoragePath<ID> clone(){
        return (FileStoragePath<ID>) super.clone();
    }
    //</editor-fold>
}
