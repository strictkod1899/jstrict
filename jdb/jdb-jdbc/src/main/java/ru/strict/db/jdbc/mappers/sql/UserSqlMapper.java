package ru.strict.db.jdbc.mappers.sql;

import ru.strict.models.DetailsUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;

public class UserSqlMapper<ID> extends BaseSqlMapper<ID, DetailsUser<ID>> {

    public UserSqlMapper(String[] columns, SQLType idType, String idColumnName){
        super(columns, idType, idColumnName);
    }

    @Override
    public DetailsUser<ID> implementMap(ResultSet resultSet) throws SQLException {
        DetailsUser<ID> model = new DetailsUser();
        model.setId(mapValueBySqlType(idType, resultSet, idColumnName));
        model.setUsername(resultSet.getString(columns[0]));
        model.setPasswordEncode(resultSet.getString(columns[1]));
        model.setEmail(resultSet.getString(columns[2]));
        model.setBlocked(resultSet.getBoolean(columns[3]));
        model.setDeleted(resultSet.getBoolean(columns[4]));
        model.setConfirmEmail(resultSet.getBoolean(columns[5]));
        model.setSalt(resultSet.getString(columns[6]));
        model.setSecret(resultSet.getString(columns[7]));
        return model;
    }
}
