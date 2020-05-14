package ru.strict.db.jdbc.mappers.sql;

import ru.strict.models.DetailsProfile;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;

import static ru.strict.db.jdbc.utils.JdbcUtil.getValueBySqlType;

public class ProfileDetailsSqlMapper<ID> extends BaseSqlMapper<DetailsProfile<ID>> {

    public ProfileDetailsSqlMapper(String[] columns, SQLType idType, String idColumnName){
        super(columns, idType, idColumnName);
    }

    @Override
    public DetailsProfile<ID> implementMap(ResultSet resultSet) throws SQLException {
        DetailsProfile<ID> model = new DetailsProfile();
        model.setId(getValueBySqlType(idType, resultSet, idColumnName));
        model.setName(resultSet.getString(columns[0]));
        model.setSurname(resultSet.getString(columns[1]));
        model.setMiddlename(resultSet.getString(columns[2]));
        model.setUserId(getValueBySqlType(idType, resultSet, columns[3]));
        model.setMan(resultSet.getBoolean(columns[4]));
        model.setDateBirth(resultSet.getDate(columns[5]));
        model.setPhone(resultSet.getString(columns[6]));
        model.setCityId(getValueBySqlType(idType, resultSet, columns[7]));
        return model;
    }
}
