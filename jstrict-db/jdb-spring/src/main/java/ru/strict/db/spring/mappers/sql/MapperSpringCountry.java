package ru.strict.db.spring.mappers.sql;

import org.springframework.jdbc.core.RowMapper;
import ru.strict.db.jdbc.mappers.sql.MapperSqlCountry;
import ru.strict.models.Country;

import java.sql.ResultSet;
import java.sql.SQLType;

public class MapperSpringCountry<ID> extends MapperSqlCountry<ID> implements RowMapper<Country<ID>> {

    public MapperSpringCountry(String[] columns, SQLType idType, String idColumnName) {
        super(columns, idType, idColumnName);
    }

    @Override
    public Country<ID> mapRow(ResultSet resultSet, int i) {
        return map(resultSet);
    }
}
