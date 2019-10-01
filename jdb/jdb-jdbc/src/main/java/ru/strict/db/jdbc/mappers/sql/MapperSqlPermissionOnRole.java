package ru.strict.db.jdbc.mappers.sql;

import ru.strict.models.PermissionOnRole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;

public class MapperSqlPermissionOnRole<ID, PERMISSION> extends MapperSqlBase<ID, PermissionOnRole<ID, PERMISSION>> {

    public MapperSqlPermissionOnRole(String[] columns, SQLType idType, String idColumnName){
        super(columns, idType, idColumnName);
    }

    @Override
    public PermissionOnRole<ID, PERMISSION> implementMap(ResultSet resultSet) throws SQLException {
        PermissionOnRole<ID, PERMISSION> model = new PermissionOnRole();
        model.setId(mapValueBySqlType(idType, resultSet, idColumnName));
        model.setPermissionId(resultSet.getInt(columns[0]));
        model.setRoleId(mapValueBySqlType(idType, resultSet, columns[1]));
        return model;
    }
}
