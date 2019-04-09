package ru.strict.db.mybatis.mappers.sql;

import org.apache.ibatis.annotations.Param;
import ru.strict.db.core.entities.EntityCity;

import java.util.List;

public interface MapperSqlCity<ID> extends MapperSqlNamed<ID, EntityCity<ID>> {
    List<EntityCity<ID>> readByCountryId(@Param("countryId") ID countryId);
}
