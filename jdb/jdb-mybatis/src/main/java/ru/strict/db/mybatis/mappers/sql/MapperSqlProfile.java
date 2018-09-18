package ru.strict.db.mybatis.mappers.sql;

import ru.strict.db.core.entities.EntityProfile;

public interface MapperSqlProfile<ID> extends MapperSqlExtension<ID, EntityProfile<ID>> {
    EntityProfile<ID> readByUserId(ID userId);
}
