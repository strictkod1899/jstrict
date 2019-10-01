package ru.strict.db.jdbc.mappers.sql;

import ru.strict.models.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;

public class MapperSqlRole<ID> extends MapperSqlBase<ID, Role<ID>> {

    public MapperSqlRole(String[] columns, SQLType idType, String idColumnName){
        super(columns, idType, idColumnName);
    }

    @Override
    public Role<ID> implementMap(ResultSet resultSet) throws SQLException {
        Role<ID> model = new Role();
        model.setId(mapValueBySqlType(idType, resultSet, idColumnName));
        model.setCode(resultSet.getString(columns[0]));
        model.setDescription(resultSet.getString(columns[1]));
        return model;
    }
}
