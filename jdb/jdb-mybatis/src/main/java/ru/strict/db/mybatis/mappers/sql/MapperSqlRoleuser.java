package ru.strict.db.mybatis.mappers.sql;

import ru.strict.db.core.entities.EntityRoleuser;

import java.util.List;

public interface MapperSqlRoleuser<ID> extends MapperSqlNamed<ID, EntityRoleuser<ID>> {
    List<EntityRoleuser<ID>> readByUserId(ID userId);
}
