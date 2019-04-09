package ru.strict.db.mybatis.mappers.sql;

import org.apache.ibatis.annotations.Param;
import ru.strict.db.core.entities.EntityProfileInfo;

import java.util.List;

public interface MapperSqlProfileInfo<ID> extends MapperSqlExtension<ID, EntityProfileInfo<ID>> {
    List<EntityProfileInfo<ID>> readByFio(@Param("name") String name, @Param("surname") String surname, @Param("middlename") String middlename);
    List<EntityProfileInfo<ID>> readByUserId(@Param("userId") ID userId);
}
