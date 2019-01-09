package ru.strict.db.mybatis.mappers.sql;

import org.apache.ibatis.annotations.Param;
import ru.strict.db.core.entities.EntityBase;

import java.util.List;

public interface MapperSqlExtension<ID, ENTITY extends EntityBase<ID>> extends MapperSqlBase<ID, ENTITY> {
    void createGenerateId(ENTITY entity);
    ENTITY readFill(@Param("id")ID id);
    List<ENTITY> readAllFill(String requests);
    int readCount(String requests);
}
