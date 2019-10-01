package ru.strict.db.jdbc.mappers.sql;

import ru.strict.models.Profile;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;

public class MapperSqlProfile<ID> extends MapperSqlBase<ID, Profile<ID>> {

    public MapperSqlProfile(String[] columns, SQLType idType, String idColumnName){
        super(columns, idType, idColumnName);
    }

    @Override
    public Profile<ID> implementMap(ResultSet resultSet) throws SQLException {
        Profile<ID> model = new Profile();
        model.setId(mapValueBySqlType(idType, resultSet, idColumnName));
        model.setName(resultSet.getString(columns[0]));
        model.setSurname(resultSet.getString(columns[1]));
        model.setUserId(mapValueBySqlType(idType, resultSet, columns[2]));
        return model;
    }
}
