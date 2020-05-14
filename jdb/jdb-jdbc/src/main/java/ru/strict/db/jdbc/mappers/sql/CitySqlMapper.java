package ru.strict.db.jdbc.mappers.sql;

import ru.strict.models.City;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;

import static ru.strict.db.jdbc.utils.JdbcUtil.getValueBySqlType;

public class CitySqlMapper<ID> extends BaseSqlMapper<City<ID>> {

    public CitySqlMapper(String[] columns, SQLType idType, String idColumnName){
        super(columns, idType, idColumnName);
    }

    @Override
    public City<ID> implementMap(ResultSet resultSet) throws SQLException {
        City<ID> model = new City();
        model.setId(getValueBySqlType(idType, resultSet, idColumnName));
        model.setCaption(resultSet.getString(columns[0]));
        model.setCountryId(getValueBySqlType(idType, resultSet, columns[1]));
        return model;
    }
}
