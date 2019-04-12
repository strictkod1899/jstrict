package ru.strict.db.core.dto;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/**
 * Полное представление FileStorage
 */
public class DtoFileStorage<ID> extends DtoFileStoragePath<ID> {

    /**
     * Байтовое представление файла
     */
    private byte[] content;

    public DtoFileStorage() {
        super();
    }

    public DtoFileStorage(String filename, String extension, String displayName, Date createDate, int type, int status, String filePath, byte[] content) {
        super(filename, extension, displayName, createDate, type, status, filePath);
        this.content = content;
    }

    public DtoFileStorage(ID id, String filename, String extension, String displayName, Date createDate, int type, int status, String filePath, byte[] content) {
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
        DtoFileStorage<ID> that = (DtoFileStorage<ID>) o;
        return Arrays.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode(content);
        return result;
    }
    //</editor-fold>
}
