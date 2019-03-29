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
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof DtoFileStorageContent) {
            DtoFileStorageContent object = (DtoFileStorageContent) obj;
            return super.equals(obj) && Arrays.equals(content, object.content);
        }else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(getId(), getFilename(), getExtension(), getDisplayName(), content, getCreateDate(), getType(), getStatus());
    }
    //</editor-fold>
}
