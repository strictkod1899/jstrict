package ru.strict.db.jdbc.mappers.sql;

import ru.strict.models.City;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;

public class MapperSqlCity<ID> extends MapperSqlBase<ID, City<ID>> {

    public MapperSqlCity(String[] columns, SQLType idType, String idColumnName){
        super(columns, idType, idColumnName);
    }

    @Override
    public City<ID> implementMap(ResultSet resultSet) throws SQLException {
        City<ID> model = new City();
        model.setId(mapValueBySqlType(idType, resultSet, idColumnName));
        model.setCaption(resultSet.getString(columns[0]));
        model.setCountryId(mapValueBySqlType(idType, resultSet, columns[1]));
        return model;
    }
}
