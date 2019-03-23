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

    public DtoFileStorage(String filename, String filePath, byte[] content, Date createDate, String type) {
        super(filename, filePath, createDate, type);
        this.content = content;
    }

    public DtoFileStorage(ID id, String filename, String filePath, byte[] content, Date createDate, String type) {
        super(id, filename, filePath, createDate, type);
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
    public String toString(){
        return String.format("file storage [%s]: %s", String.valueOf(getId()), String.valueOf(getFilename()));
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof DtoFileStorage) {
            DtoFileStorage object = (DtoFileStorage) obj;
            return super.equals(obj) && Objects.equals(getFilename(), object.getFilename())
                    && Objects.equals(getFilePath(), object.getFilePath())
                    && Objects.equals(content, object.content)
                    && Objects.equals(getCreateDate(), object.getCreateDate())
                    && Objects.equals(getType(), object.getType());
        }else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(getId(), getFilename(), getFilePath(), content, getCreateDate(), getType());
    }
    //</editor-fold>
}
