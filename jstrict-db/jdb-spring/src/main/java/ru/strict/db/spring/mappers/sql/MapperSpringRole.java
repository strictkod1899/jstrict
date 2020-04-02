package ru.strict.db.spring.mappers.sql;

import org.springframework.jdbc.core.RowMapper;
import ru.strict.db.jdbc.mappers.sql.MapperSqlRole;
import ru.strict.models.Role;

import java.sql.ResultSet;
import java.sql.SQLType;

public class MapperSpringRole<ID> extends MapperSqlRole<ID> implements RowMapper<Role<ID>> {

    public MapperSpringRole(String[] columns, SQLType idType, String idColumnName) {
        super(columns, idType, idColumnName);
    }

    @Override
    public Role<ID> mapRow(ResultSet resultSet, int i) {
        return map(resultSet);
    }
}
