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

    public DtoFileStoragePath(String filename, String filePath, Date createDate, String type) {
        super(filename, createDate, type);
        this.filePath = filePath;
    }

    public DtoFileStoragePath(ID id, String filename, String filePath, Date createDate, String type) {
        super(id, filename, createDate, type);
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
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof DtoFileStoragePath) {
            DtoFileStoragePath object = (DtoFileStoragePath) obj;
            return super.equals(obj) && Objects.equals(filePath, object.filePath);
        }else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(getId(), getFilename(), filePath, getCreateDate(), getType());
    }
    //</editor-fold>
}
