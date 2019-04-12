package ru.strict.db.core.dto;

import java.util.Date;
import java.util.Objects;

/**
 * Представление FileStorage с путем до файла, игнорируя атрибут content
 */
public class DtoFileStoragePath<ID> extends DtoFileStorageBase<ID> {
    /**
     * Путь до файла
     */
    private String filePath;

    public DtoFileStoragePath() {
        super();
    }

    public DtoFileStoragePath(String filename, String extension, String displayName, Date createDate, int type, int status, String filePath) {
        super(filename, extension, displayName, createDate, type, status);
        this.filePath = filePath;
    }

    public DtoFileStoragePath(ID id, String filename, String extension, String displayName, Date createDate, int type, int status, String filePath) {
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
        DtoFileStoragePath<ID> that = (DtoFileStoragePath<ID>) o;
        return Objects.equals(filePath, that.filePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), filePath);
    }
    //</editor-fold>
}
