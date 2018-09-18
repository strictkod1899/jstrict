package ru.strict.db.mybatis.mappers.sql;

import ru.strict.db.core.entities.EntityUser;

import java.util.List;

public interface MapperSqlUser<ID> extends MapperSqlExtension<ID, EntityUser<ID>> {
    List<EntityUser<ID>> readByRoleId(ID roleId);
}
