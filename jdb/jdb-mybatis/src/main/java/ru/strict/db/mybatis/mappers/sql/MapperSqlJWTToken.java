package ru.strict.db.mybatis.mappers.sql;

import ru.strict.db.core.entities.EntityJWTToken;
import ru.strict.db.core.entities.EntityUser;

import java.util.List;

public interface MapperSqlJWTToken<ID> extends MapperSqlExtension<ID, EntityJWTToken<ID>> {
    List<EntityUser<ID>> readByUserId(ID userId);
}
