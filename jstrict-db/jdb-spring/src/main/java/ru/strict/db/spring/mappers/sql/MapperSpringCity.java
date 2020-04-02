package ru.strict.db.spring.mappers.sql;

import org.springframework.jdbc.core.RowMapper;
import ru.strict.db.jdbc.mappers.sql.MapperSqlCity;
import ru.strict.models.City;

import java.sql.ResultSet;
import java.sql.SQLType;

public class MapperSpringCity<ID> extends MapperSqlCity<ID> implements RowMapper<City<ID>> {

    public MapperSpringCity(String[] columns, SQLType idType, String idColumnName) {
        super(columns, idType, idColumnName);
    }

    @Override
    public City<ID> mapRow(ResultSet resultSet, int i) {
        return map(resultSet);
    }
}
