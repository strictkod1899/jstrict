package ru.strict.db.spring.mappers.sql;

import org.springframework.jdbc.core.RowMapper;
import ru.strict.db.jdbc.mappers.sql.MapperSqlPermissionOnRole;
import ru.strict.models.PermissionOnRole;

import java.sql.ResultSet;
import java.sql.SQLType;

public class MapperSpringPermissionOnRole<ID, PERMISSION> extends MapperSqlPermissionOnRole<ID, PERMISSION>
        implements RowMapper<PermissionOnRole<ID, PERMISSION>> {

    public MapperSpringPermissionOnRole(String[] columns, SQLType idType, String idColumnName) {
        super(columns, idType, idColumnName);
    }

    @Override
    public PermissionOnRole<ID, PERMISSION> mapRow(ResultSet resultSet, int i) {
        return map(resultSet);
    }
}
