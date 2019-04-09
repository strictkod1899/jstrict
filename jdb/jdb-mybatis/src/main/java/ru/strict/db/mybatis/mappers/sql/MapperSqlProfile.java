package ru.strict.db.mybatis.mappers.sql;

import org.apache.ibatis.annotations.Param;
import ru.strict.db.core.entities.EntityProfile;

import java.util.List;

public interface MapperSqlProfile<ID> extends MapperSqlExtension<ID, EntityProfile<ID>> {
    List<EntityProfile<ID>> readByFio(@Param("name") String name, @Param("surname") String surname, @Param("middlename") String middlename);
    List<EntityProfile<ID>> readByUserId(@Param("userId") ID userId);
}
