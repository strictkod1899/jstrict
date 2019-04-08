package ru.strict.db.mybatis.mappers.sql;

import org.apache.ibatis.annotations.Param;
import ru.strict.db.core.entities.EntityBase;

import java.util.List;
import java.util.UUID;

public interface MapperSqlBase<ID, ENTITY extends EntityBase<ID>> {
    void create(ENTITY entity);
    ENTITY read(@Param("id")ID id);
    List<ENTITY> readAll();
    void update(ENTITY entity);
    void delete(@Param("id")ID id);
}
