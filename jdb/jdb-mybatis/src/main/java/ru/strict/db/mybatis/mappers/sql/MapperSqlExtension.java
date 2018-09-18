package ru.strict.db.mybatis.mappers.sql;

import ru.strict.db.core.entities.EntityBase;

import java.util.List;

public interface MapperSqlExtension<ID, ENTITY extends EntityBase<ID>> extends MapperSqlBase<ID, ENTITY> {
    ENTITY readFill(ID id);
    List<ENTITY> readAllFill(String requests);
    int readCount(String requests);
}
