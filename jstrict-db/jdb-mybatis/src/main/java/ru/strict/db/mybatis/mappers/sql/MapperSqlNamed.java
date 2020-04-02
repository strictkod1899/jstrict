package ru.strict.db.mybatis.mappers.sql;

import org.apache.ibatis.annotations.Param;
import ru.strict.models.ModelBase;

import java.util.List;

public interface MapperSqlNamed<ID, T extends ModelBase<ID>> extends MapperSqlExtension<ID, T> {
    T readByName(@Param("caption") String caption);
    List<T> readAllByName(@Param("caption") String caption);
    T readByNameFill(@Param("caption") String caption);
    List<T> readAllByNameFill(@Param("caption") String caption);
}
