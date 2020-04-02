package ru.strict.db.spring.mappers.sql;

import org.springframework.jdbc.core.RowMapper;
import ru.strict.db.jdbc.mappers.sql.MapperSqlUser;
import ru.strict.models.UserDetails;

import java.sql.ResultSet;
import java.sql.SQLType;

public class MapperSpringUser<ID> extends MapperSqlUser<ID> implements RowMapper<UserDetails<ID>> {

    public MapperSpringUser(String[] columns, SQLType idType, String idColumnName) {
        super(columns, idType, idColumnName);
    }

    @Override
    public UserDetails<ID> mapRow(ResultSet resultSet, int i) {
        return map(resultSet);
    }
}
