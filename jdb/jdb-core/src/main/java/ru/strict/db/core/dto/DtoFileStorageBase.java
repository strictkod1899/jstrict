package ru.strict.db.core.dto;

import java.util.Date;
import java.util.Objects;

public class DtoFileStorageBase<ID> extends DtoBase<ID> {

    /**
     * Наименование файла
     */
    private String filename;
    /**
     * Расширение файла
     */
    private String extension;
    /**
     * Отображаемое наименование файла. Без расширения
     */
    private String displayName;
    /**
     * Дата создания записи о файле
     */
    private Date createDate;
    /**
     * Тип файла
     */
    private int type;

    public DtoFileStorageBase() {
        super();
    }

    public DtoFileStorageBase(String filename, String extension, String displayName, Date createDate, int type) {
        this.filename = filename;
        this.extension = extension;
        this.displayName = displayName;
        this.createDate = createDate;
        this.type = type;
    }

    public DtoFileStorageBase(ID id, String filename, String extension, String displayName, Date createDate, int type) {
        super(id);
        this.filename = filename;
        this.extension = extension;
        this.displayName = displayName;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("file storage [%s]: %s.%s", String.valueOf(getId()), String.valueOf(filename), String.valueOf(extension));
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof DtoFileStorageBase) {
            DtoFileStorageBase object = (DtoFileStorageBase) obj;
            return super.equals(obj) && Objects.equals(filename, object.filename)
                    && Objects.equals(extension, object.extension)
                    && Objects.equals(displayName, object.displayName)
                    && Objects.equals(createDate, object.createDate)
                    && Objects.equals(type, object.type);
        }else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(getId(), filename, extension, displayName, createDate, type);
    }
    //</editor-fold>
}
