package ru.strict.db.mybatis.mappers.sql;

import org.apache.ibatis.annotations.Param;
import ru.strict.models.ModelBase;

import java.util.List;

public interface MapperSqlBase<ID, T extends ModelBase<ID>> {
    void create(T model);
    T read(@Param("id")ID id);
    List<T> readAll();
    void update(T model);
    void delete(@Param("id")ID id);
}
