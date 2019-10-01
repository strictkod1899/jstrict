package ru.strict.db.jdbc.mappers.sql;

import ru.strict.models.Country;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;

public class MapperSqlCountry<ID> extends MapperSqlBase<ID, Country<ID>> {

    public MapperSqlCountry(String[] columns, SQLType idType, String idColumnName){
        super(columns, idType, idColumnName);
    }

    @Override
    public Country<ID> implementMap(ResultSet resultSet) throws SQLException {
        Country<ID> model = new Country();
        model.setId(mapValueBySqlType(idType, resultSet, idColumnName));
        model.setCaption(resultSet.getString(columns[0]));
        return model;
    }
}
