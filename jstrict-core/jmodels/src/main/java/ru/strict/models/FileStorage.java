package ru.strict.models;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class FileStorage<ID> extends BaseModel<ID> {

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
    /**
     * Путь до файла
     */
    private String filePath;
    /**
     * Байтовое представление файла
     */
    private byte[] content;

    public FileStorage() {
        super();
    }

    public FileStorage(String filename,
            String extension,
            String displayName,
            Date createDate,
            int type,
            int status,
            String filePath, byte[] content) {
        this.filename = filename;
        this.extension = extension;
        this.displayName = displayName;
        this.createDate = createDate;
        this.type = type;
        this.status = status;
        this.filePath = filePath;
        this.content = content;
    }

    public FileStorage(ID id,
            String filename,
            String extension,
            String displayName,
            Date createDate,
            int type,
            int status,
            String filePath, byte[] content) {
        super(id);
        this.filename = filename;
        this.extension = extension;
        this.displayName = displayName;
        this.createDate = createDate;
        this.type = type;
        this.status = status;
        this.filePath = filePath;
        this.content = content;
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

    public String getFilePath() {
        return filePath;
    }

    public FileStorage<ID> setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public byte[] getContent() {
        return content;
    }

    public FileStorage<ID> setContent(byte[] content) {
        this.content = content;
        return this;
    }

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString() {
        return String.format("file storage [%s]: %s.%s",
                String.valueOf(getId()),
                String.valueOf(filename),
                String.valueOf(extension));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FileStorage<ID> object = (FileStorage<ID>) o;
        return type == object.type &&
                status == object.status &&
                Objects.equals(filename, object.filename) &&
                Objects.equals(extension, object.extension) &&
                Objects.equals(displayName, object.displayName) &&
                Objects.equals(createDate, object.createDate) &&
                Objects.equals(filePath, object.filePath) &&
                Arrays.equals(content, object.content);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(filename, extension, displayName, createDate, type, status, filePath);
        result = 31 * result + Arrays.hashCode(content);
        return result;
    }

    @Override
    public FileStorage<ID> clone() {
        try {
            FileStorage<ID> clone = (FileStorage<ID>) super.clone();

            clone.setCreateDate(createDate == null ? null : (Date) createDate.clone());
            clone.setContent(content == null ? null : content.clone());
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
    //</editor-fold>
}
