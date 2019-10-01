package ru.strict.db.spring.mappers.sql;

import org.springframework.jdbc.core.RowMapper;

import ru.strict.db.jdbc.mappers.sql.MapperSqlJWTToken;
import ru.strict.models.JWTToken;

import java.sql.ResultSet;
import java.sql.SQLType;

public class MapperSpringJWTToken<ID> extends MapperSqlJWTToken<ID> implements RowMapper<JWTToken<ID>> {

    public MapperSpringJWTToken(String[] columns, SQLType idType, String idColumnName) {
        super(columns, idType, idColumnName);
    }

    @Override
    public JWTToken<ID> mapRow(ResultSet resultSet, int i) {
        return map(resultSet);
    }
}
