package ru.strict.db.core.dto;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/**
 * Представление FileStorage с содержимым файла, игнорируя атрибут filePath
 */
public class DtoFileStorageContent<ID> extends DtoFileStorageBase<ID> {

    /**
     * Байтовое представление файла
     */
    private byte[] content;

    public DtoFileStorageContent() {
        super();
    }

    public DtoFileStorageContent(String filename, String extension, String displayName, Date createDate, int type, int status, byte[] content) {
        super(filename, extension, displayName, createDate, type, status);
        this.content = content;
    }

    public DtoFileStorageContent(ID id, String filename, String extension, String displayName, Date createDate, int type, int status, byte[] content) {
        super(id, filename, extension, displayName, createDate, type, status);
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
        DtoFileStorageContent<ID> object = (DtoFileStorageContent<ID>) o;
        return Arrays.equals(content, object.content);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode(content);
        return result;
    }

    @Override
    public DtoFileStorageContent<ID> clone(){
        DtoFileStorageContent<ID> clone = (DtoFileStorageContent<ID>) super.clone();

        clone.setContent(content == null ? null : content.clone());
        return clone;
    }
    //</editor-fold>
}
