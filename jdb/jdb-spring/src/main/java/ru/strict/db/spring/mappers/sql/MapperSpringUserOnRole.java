package ru.strict.db.spring.mappers.sql;

import org.springframework.jdbc.core.RowMapper;
import ru.strict.db.jdbc.mappers.sql.MapperSqlUserOnRole;
import ru.strict.models.UserOnRole;

import java.sql.ResultSet;
import java.sql.SQLType;

public class MapperSpringUserOnRole<ID> extends MapperSqlUserOnRole<ID> implements RowMapper<UserOnRole<ID>> {

    public MapperSpringUserOnRole(String[] columns, SQLType idType, String idColumnName) {
        super(columns, idType, idColumnName);
    }

    @Override
    public UserOnRole<ID> mapRow(ResultSet resultSet, int i) {
        return map(resultSet);
    }
}
