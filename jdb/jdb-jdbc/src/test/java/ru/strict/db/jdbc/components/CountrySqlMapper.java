package ru.strict.db.jdbc.mappers.sql;

import ru.strict.models.Country;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;

import static ru.strict.db.jdbc.utils.JdbcUtil.getValueBySqlType;

public class CountrySqlMapper<ID> extends BaseSqlMapper<Country<ID>> {

    public CountrySqlMapper(String[] columns, SQLType idType, String idColumnName){
        super(columns, idType, idColumnName);
    }

    @Override
    public Country<ID> implementMap(ResultSet resultSet) throws SQLException {
        Country<ID> model = new Country();
        model.setId(getValueBySqlType(resultSet, idColumnName, idType));
        model.setName(resultSet.getString(columns[0]));
        return model;
    }
}
