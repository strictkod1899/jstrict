package ru.strict.db.spring.mappers.sql;

import org.springframework.jdbc.core.RowMapper;
import ru.strict.db.jdbc.mappers.sql.MapperSqlProfile;
import ru.strict.models.Profile;

import java.sql.ResultSet;
import java.sql.SQLType;

public class MapperSpringProfile<ID> extends MapperSqlProfile<ID> implements RowMapper<Profile<ID>> {

    public MapperSpringProfile(String[] columns, SQLType idType, String idColumnName) {
        super(columns, idType, idColumnName);
    }

    @Override
    public Profile<ID> mapRow(ResultSet resultSet, int i) {
        return map(resultSet);
    }
}
