package ru.strict.db.core.dto;

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

    public DtoFileStorage(String filename, String extension, String displayName, Date createDate, int type, String filePath, byte[] content) {
        super(filename, extension, displayName, createDate, type, filePath);
        this.content = content;
    }

    public DtoFileStorage(ID id, String filename, String extension, String displayName, Date createDate, int type, String filePath, byte[] content) {
        super(id, filename, extension, displayName, createDate, type, filePath);
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
        if(obj!=null && obj instanceof DtoFileStorage) {
            DtoFileStorage object = (DtoFileStorage) obj;
            return super.equals(obj) && Objects.equals(content, object.content);
        }else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(getId(), getFilename(), getExtension(), getDisplayName(), getFilePath(), content, getCreateDate(), getType());
    }
    //</editor-fold>
}
