package ru.strict.db.jdbc.mappers.sql;

import ru.strict.models.Profile;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;

import static ru.strict.db.jdbc.utils.JdbcUtil.getValueBySqlType;

public class ProfileSqlMapper<ID> extends BaseSqlMapper<Profile<ID>> {

    public ProfileSqlMapper(String[] columns, SQLType idType, String idColumnName){
        super(columns, idType, idColumnName);
    }

    @Override
    public Profile<ID> implementMap(ResultSet resultSet) throws SQLException {
        Profile<ID> model = new Profile();
        model.setId(getValueBySqlType(resultSet, idColumnName, idType));
        model.setName(resultSet.getString(columns[0]));
        model.setSurname(resultSet.getString(columns[1]));
        model.setUserId(getValueBySqlType(resultSet, columns[2], idType));
        return model;
    }
}
