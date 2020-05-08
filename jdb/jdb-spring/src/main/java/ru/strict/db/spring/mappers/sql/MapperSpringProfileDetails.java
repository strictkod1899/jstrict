package ru.strict.db.spring.mappers.sql;

import org.springframework.jdbc.core.RowMapper;
import ru.strict.db.jdbc.mappers.sql.MapperSqlProfileDetails;
import ru.strict.models.DetailsProfile;

import java.sql.ResultSet;
import java.sql.SQLType;

public class MapperSpringProfileDetails<ID> extends MapperSqlProfileDetails<ID> implements RowMapper<DetailsProfile<ID>> {

    public MapperSpringProfileDetails(String[] columns, SQLType idType, String idColumnName) {
        super(columns, idType, idColumnName);
    }

    @Override
    public DetailsProfile<ID> mapRow(ResultSet resultSet, int i) {
        return map(resultSet);
    }
}
