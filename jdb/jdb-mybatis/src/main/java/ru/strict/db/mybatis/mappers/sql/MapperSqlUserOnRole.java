package ru.strict.db.mybatis.mappers.sql;

import org.apache.ibatis.annotations.Param;
import ru.strict.db.core.entities.EntityUserOnRole;

import java.util.List;

public interface MapperSqlUserOnRole<ID> extends MapperSqlExtension<ID, EntityUserOnRole<ID>> {
    List<EntityUserOnRole<ID>> readByUserId(@Param("userId") ID userId);
    List<EntityUserOnRole<ID>> readByRoleId(@Param("roleId") ID roleId);
}
