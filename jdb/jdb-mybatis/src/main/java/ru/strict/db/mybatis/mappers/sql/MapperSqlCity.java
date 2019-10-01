package ru.strict.db.mybatis.mappers.sql;

import org.apache.ibatis.annotations.Param;
import ru.strict.models.City;

import java.util.List;

public interface MapperSqlCity<ID> extends MapperSqlNamed<ID, City<ID>> {
    List<City<ID>> readByCountryId(@Param("countryId") ID countryId);
}
