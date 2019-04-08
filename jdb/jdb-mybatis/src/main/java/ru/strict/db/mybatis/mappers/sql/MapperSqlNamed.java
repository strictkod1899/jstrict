package ru.strict.db.mybatis.mappers.sql;

import org.apache.ibatis.annotations.Param;
import ru.strict.db.core.entities.EntityBase;

import java.util.List;

public interface MapperSqlNamed<ID, ENTITY extends EntityBase<ID>> extends MapperSqlExtension<ID, ENTITY> {
    ENTITY readByName(@Param("caption") String caption);
    List<ENTITY> readAllByName(@Param("caption") String caption);
}
