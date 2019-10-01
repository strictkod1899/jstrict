package ru.strict.db.spring.mappers.sql;

import org.springframework.jdbc.core.RowMapper;
import ru.strict.db.jdbc.mappers.sql.MapperSqlFileStorage;
import ru.strict.models.FileStorage;

import java.sql.ResultSet;
import java.sql.SQLType;

public class MapperSpringFileStorage<ID> extends MapperSqlFileStorage<ID> implements RowMapper<FileStorage<ID>> {

    public MapperSpringFileStorage(String[] columns, SQLType idType, String idColumnName) {
        super(columns, idType, idColumnName);
    }

    @Override
    public FileStorage<ID> mapRow(ResultSet resultSet, int i) {
        return map(resultSet);
    }
}
