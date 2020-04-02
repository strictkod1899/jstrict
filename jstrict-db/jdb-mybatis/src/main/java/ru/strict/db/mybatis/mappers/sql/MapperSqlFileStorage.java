package ru.strict.db.mybatis.mappers.sql;

import org.apache.ibatis.annotations.Param;
import ru.strict.models.FileStorage;

import java.util.List;

public interface MapperSqlFileStorage<ID> extends MapperSqlNamed<ID, FileStorage<ID>> {
    List<FileStorage<ID>> readByDisplayName(@Param("displayName") String displayName);

    List<FileStorage<ID>> readByFileNameAndExtension(@Param("fileName") String fileName, @Param("extension") String extension);

    List<FileStorage<ID>> readByDisplayNameAndExtension(@Param("displayName") String displayName, @Param("extension") String extension);

    FileStorage<ID> readByFilePath(@Param("filePath") String filePath);

    List<FileStorage<ID>> readByType(@Param("type") int type);

    List<FileStorage<ID>> readByStatus(@Param("status") int status);
}
