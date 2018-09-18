package ru.strict.db.mybatis.mappers.sql;

import ru.strict.db.core.entities.EntityBase;

import java.util.List;
import java.util.UUID;

public interface MapperSqlBase<ID, ENTITY extends EntityBase<ID>> {
    void create(ENTITY entity);
    ENTITY read(ID id);
    List<ENTITY> readAll(String requests);
    void update(ENTITY entity);
    void delete(ID id);
}
