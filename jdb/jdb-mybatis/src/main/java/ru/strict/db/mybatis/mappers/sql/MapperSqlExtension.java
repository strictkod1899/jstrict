package ru.strict.db.mybatis.mappers.sql;

import org.apache.ibatis.annotations.Param;
import ru.strict.components.ObjectValue;
import ru.strict.models.ModelBase;

import java.util.List;

public interface MapperSqlExtension<ID, T extends ModelBase<ID>> extends MapperSqlBase<ID, T> {
    void createGenerateId(@Param("model")T model, @Param("generateId")ObjectValue<?> generateId);
    T readFill(@Param("id")ID id);
    List<T> readAllFill();
    int readCount();
    int readCountById(@Param("id")ID id);
}
