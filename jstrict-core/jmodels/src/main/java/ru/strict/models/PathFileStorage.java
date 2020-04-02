package ru.strict.models;

import java.util.Date;
import java.util.Objects;

/**
 * Представление FileStorage с путем до файла, игнорируя атрибут content
 */
public class PathFileStorage<ID> extends BaseFileStorage<ID> {
    /**
     * Путь до файла
     */
    private String filePath;

    public PathFileStorage() {
        super();
    }

    public PathFileStorage(String filename, String extension, String displayName, Date createDate, int type, int status, String filePath) {
        super(filename, extension, displayName, createDate, type, status);
        this.filePath = filePath;
    }

    public PathFileStorage(ID id, String filename, String extension, String displayName, Date createDate, int type, int status, String filePath) {
        super(id, filename, extension, displayName, createDate, type, status);
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PathFileStorage<ID> object = (PathFileStorage<ID>) o;
        return Objects.equals(filePath, object.filePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), filePath);
    }

    @Override
    public PathFileStorage<ID> clone(){
        return (PathFileStorage<ID>) super.clone();
    }
    //</editor-fold>
}
