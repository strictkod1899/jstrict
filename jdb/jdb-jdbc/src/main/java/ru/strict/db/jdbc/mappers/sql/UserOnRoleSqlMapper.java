package ru.strict.db.jdbc.mappers.sql;

import ru.strict.models.UserOnRole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;

import static ru.strict.db.jdbc.utils.JdbcUtil.getValueBySqlType;

public class UserOnRoleSqlMapper<ID> extends BaseSqlMapper<UserOnRole<ID>> {

    public UserOnRoleSqlMapper(String[] columns, SQLType idType, String idColumnName){
        super(columns, idType, idColumnName);
    }

    @Override
    public UserOnRole<ID> implementMap(ResultSet resultSet) throws SQLException {
        UserOnRole<ID> model = new UserOnRole();
        model.setId(getValueBySqlType(resultSet, idColumnName, idType));
        model.setUserId(getValueBySqlType(resultSet, columns[0], idType));
        model.setRoleId(getValueBySqlType(resultSet, columns[1], idType));
        return model;
    }
}
