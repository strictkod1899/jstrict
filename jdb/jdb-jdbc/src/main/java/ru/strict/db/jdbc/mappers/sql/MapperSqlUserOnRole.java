package ru.strict.db.jdbc.mappers.sql;

import ru.strict.models.UserOnRole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;

public class MapperSqlUserOnRole<ID> extends SqlMapperBase<ID, UserOnRole<ID>> {

    public MapperSqlUserOnRole(String[] columns, SQLType idType, String idColumnName){
        super(columns, idType, idColumnName);
    }

    @Override
    public UserOnRole<ID> implementMap(ResultSet resultSet) throws SQLException {
        UserOnRole<ID> model = new UserOnRole();
        model.setId(mapValueBySqlType(idType, resultSet, idColumnName));
        model.setUserId(mapValueBySqlType(idType, resultSet, columns[0]));
        model.setRoleId(mapValueBySqlType(idType, resultSet, columns[1]));
        return model;
    }
}
