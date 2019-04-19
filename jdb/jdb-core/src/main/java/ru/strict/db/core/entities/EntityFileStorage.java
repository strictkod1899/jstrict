package ru.strict.db.core.entities;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class EntityFileStorage<ID> extends EntityBase<ID> {

    /**
     * Наименование файла. Без расширения
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
     * Путь до файла
     */
    private String filePath;
    /**
     * Байтовое представление файла
     */
    private byte[] content;
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

    public EntityFileStorage() {
        super();
    }

    public EntityFileStorage(String filename, String extension, String displayName, String filePath, byte[] content, Date createDate, int type, int status) {
        this.filename = filename;
        this.extension = extension;
        this.displayName = displayName;
        this.filePath = filePath;
        this.content = content;
        this.createDate = createDate;
        this.type = type;
        this.status = status;
    }

    public EntityFileStorage(ID id, String filename, String extension, String displayName, String filePath, byte[] content, Date createDate, int type, int status) {
        super(id);
        this.filename = filename;
        this.extension = extension;
        this.displayName = displayName;
        this.filePath = filePath;
        this.content = content;
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
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
        if (!super.equals(o)) return false;
        EntityFileStorage<?> object = (EntityFileStorage<?>) o;
        return type == object.type &&
                status == object.status &&
                Objects.equals(filename, object.filename) &&
                Objects.equals(extension, object.extension) &&
                Objects.equals(displayName, object.displayName) &&
                Objects.equals(filePath, object.filePath) &&
                Arrays.equals(content, object.content) &&
                Objects.equals(createDate, object.createDate);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), filename, extension, displayName, filePath, createDate, type, status);
        result = 31 * result + Arrays.hashCode(content);
        return result;
    }

    @Override
    public EntityFileStorage<ID> clone(){
        try {
            EntityFileStorage<ID> clone = (EntityFileStorage<ID>) super.clone();

            clone.setContent(content == null ? null : content.clone());
            clone.setCreateDate(createDate == null ? null : (Date)createDate.clone());
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
    //</editor-fold>
}
