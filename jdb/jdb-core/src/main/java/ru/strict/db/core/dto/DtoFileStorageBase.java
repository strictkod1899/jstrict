package ru.strict.db.core.dto;

import java.util.Date;
import java.util.Objects;

public class DtoFileStorageBase<ID> extends DtoBase<ID> {

    /**
     * Наименование файла
     */
    private String filename;
    /**
     * Дата создания записи о файле
     */
    private Date createDate;
    /**
     * Тип файла
     */
    private String type;

    public DtoFileStorageBase() {
        super();
    }

    public DtoFileStorageBase(String filename, Date createDate, String type) {
        this.filename = filename;
        this.createDate = createDate;
        this.type = type;
    }

    public DtoFileStorageBase(ID id, String filename, Date createDate, String type) {
        super(id);
        this.filename = filename;
        this.createDate = createDate;
        this.type = type;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("file storage [%s]: %s", String.valueOf(getId()), String.valueOf(filename));
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof DtoFileStorageBase) {
            DtoFileStorageBase object = (DtoFileStorageBase) obj;
            return super.equals(obj) && Objects.equals(filename, object.filename)
                    && Objects.equals(createDate, object.createDate)
                    && Objects.equals(type, object.type);
        }else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(getId(), filename, createDate, type);
    }
    //</editor-fold>
}
