package ru.strict.db.mybatis.mappers.sql;

import org.apache.ibatis.annotations.Param;
import ru.strict.db.core.entities.EntityFileStorage;

import java.util.List;

public interface MapperSqlFileStorage<ID> extends MapperSqlNamed<ID, EntityFileStorage<ID>> {
    List<EntityFileStorage<ID>> readByDisplayName(@Param("displayName") String displayName);

    List<EntityFileStorage<ID>> readByFileNameAndExtension(@Param("fileName") String fileName, @Param("extension") String extension);

    List<EntityFileStorage<ID>> readByDisplayNameAndExtension(@Param("displayName") String displayName, @Param("extension") String extension);

    EntityFileStorage<ID> readByFilePath(@Param("filePath") String filePath);

    List<EntityFileStorage<ID>> readByType(@Param("type") int type);

    List<EntityFileStorage<ID>> readByStatus(@Param("status") int status);
}
