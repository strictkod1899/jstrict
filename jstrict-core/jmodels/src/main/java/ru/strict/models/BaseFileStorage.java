package ru.strict.models;

import java.util.Date;
import java.util.Objects;

public class BaseFileStorage<ID> extends BaseModel<ID> {

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
    /**
     * Статус
     */
    private int status;

    public BaseFileStorage() {
        super();
    }

    public BaseFileStorage(String filename, String extension, String displayName, Date createDate, int type, int status) {
        this.filename = filename;
        this.extension = extension;
        this.displayName = displayName;
        this.createDate = createDate;
        this.type = type;
        this.status = status;
    }

    public BaseFileStorage(ID id, String filename, String extension, String displayName, Date createDate, int type, int status) {
        super(id);
        this.filename = filename;
        this.extension = extension;
        this.displayName = displayName;
        this.createDate = createDate;
        this.type = type;
        this.status = status;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("file storage [%s]: %s.%s", String.valueOf(getId()), String.valueOf(filename), String.valueOf(extension));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseFileStorage<ID> object = (BaseFileStorage<ID>) o;
        return type == object.type &&
                status == object.status &&
                Objects.equals(filename, object.filename) &&
                Objects.equals(extension, object.extension) &&
                Objects.equals(displayName, object.displayName) &&
                Objects.equals(createDate, object.createDate);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(filename, extension, displayName, createDate, type, status);
        return result;
    }

    @Override
    public BaseFileStorage<ID> clone(){
        try {
            BaseFileStorage<ID> clone = (BaseFileStorage<ID>) super.clone();

            clone.setCreateDate(createDate == null ? null : (Date)createDate.clone());
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
    //</editor-fold>
}
