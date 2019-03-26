package ru.strict.db.core.entities;

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
    private String type;

    public EntityFileStorage() {
        super();
    }

    public EntityFileStorage(String filename, String extension, String displayName, String filePath, byte[] content, Date createDate, String type) {
        this.filename = filename;
        this.extension = extension;
        this.displayName = displayName;
        this.filePath = filePath;
        this.content = content;
        this.createDate = createDate;
        this.type = type;
    }

    public EntityFileStorage(ID id, String filename, String extension, String displayName, String filePath, byte[] content, Date createDate, String type) {
        super(id);
        this.filename = filename;
        this.extension = extension;
        this.displayName = displayName;
        this.filePath = filePath;
        this.content = content;
        this.createDate = createDate;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
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
        if(obj!=null && obj instanceof EntityFileStorage) {
            EntityFileStorage object = (EntityFileStorage) obj;
            return super.equals(obj) && Objects.equals(filename, object.filename)
                    && Objects.equals(extension, object.extension)
                    && Objects.equals(displayName, object.displayName)
                    && Objects.equals(filePath, object.filePath)
                    && Objects.equals(content, object.content)
                    && Objects.equals(createDate, object.createDate)
                    && Objects.equals(type, object.type);
        }else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(getId(), filename, extension, displayName, filePath, content, createDate, type);
    }
    //</editor-fold>
}
