package ru.strict.models;

import java.util.Date;

/**
 * Представление FileStorage с содержимым файла, игнорируя атрибут filePath
 */
public class ContentFileStorage<ID> extends FileStorage<ID> {

    public ContentFileStorage() {
        super();
    }

    public ContentFileStorage(String filename,
            String extension,
            String displayName,
            Date createDate,
            int type,
            int status,
            byte[] content) {
        super(filename, extension, displayName, createDate, type, status, null, content);
    }

    public ContentFileStorage(ID id,
            String filename,
            String extension,
            String displayName,
            Date createDate,
            int type,
            int status,
            byte[] content) {
        super(id, filename, extension, displayName, createDate, type, status, null, content);
    }
}
