package ru.strict.db.jdbc.mappers.sql;

import ru.strict.patterns.model.IModel;
import ru.strict.models.PermissionOnRole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;

import static ru.strict.db.jdbc.utils.JdbcUtil.getValueBySqlType;

public class PermissionOnRoleSqlMapper<ID, PERMISSION extends IModel<Integer>>
        extends BaseSqlMapper<PermissionOnRole<ID, PERMISSION>> {

    public PermissionOnRoleSqlMapper(String[] columns, SQLType idType, String idColumnName){
        super(columns, idType, idColumnName);
    }

    @Override
    public PermissionOnRole<ID, PERMISSION> implementMap(ResultSet resultSet) throws SQLException {
        PermissionOnRole<ID, PERMISSION> model = new PermissionOnRole<>();
        model.setId(getValueBySqlType(resultSet, idColumnName, idType));
        model.setPermissionId(resultSet.getInt(columns[0]));
        model.setRoleId(getValueBySqlType(resultSet, columns[1], idType));
        return model;
    }
}
