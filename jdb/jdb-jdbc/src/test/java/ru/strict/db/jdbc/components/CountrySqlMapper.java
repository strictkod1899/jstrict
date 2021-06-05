package ru.strict.db.jdbc.components;

import ru.strict.db.jdbc.mapper.sql.BaseSqlMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;

import static ru.strict.db.jdbc.util.JdbcUtil.getValueBySqlType;

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
