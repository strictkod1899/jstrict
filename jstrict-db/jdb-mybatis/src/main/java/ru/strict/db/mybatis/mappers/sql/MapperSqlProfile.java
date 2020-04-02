package ru.strict.db.mybatis.mappers.sql;

import org.apache.ibatis.annotations.Param;
import ru.strict.models.Profile;

import java.util.List;

public interface MapperSqlProfile<ID, T extends Profile<ID>> extends MapperSqlExtension<ID, T> {
    List<T> readBySurname(@Param("name") String name, @Param("surname") String surname);
    List<T> readByUserId(@Param("userId") ID userId);
}
