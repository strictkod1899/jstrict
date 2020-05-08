package ru.strict.models;

import java.util.Date;
import java.util.Objects;

/**
 * Представление FileStorage с путем до файла, игнорируя атрибут content
 */
public class PathFileStorage<ID> extends FileStorage<ID> {

    public PathFileStorage() {
        super();
    }

    public PathFileStorage(String filename,
            String extension,
            String displayName,
            Date createDate,
            int type,
            int status,
            String filePath) {
        super(filename, extension, displayName, createDate, type, status, filePath, null);
    }

    public PathFileStorage(ID id,
            String filename,
            String extension,
            String displayName,
            Date createDate,
            int type,
            int status,
            String filePath) {
        super(id, filename, extension, displayName, createDate, type, status, filePath, null);
    }
}
