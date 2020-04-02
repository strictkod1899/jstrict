package ru.strict.db.jdbc.mappers.sql;

import ru.strict.models.ProfileDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;

public class MapperSqlProfileDetails<ID> extends SqlMapperBase<ID, ProfileDetails<ID>> {

    public MapperSqlProfileDetails(String[] columns, SQLType idType, String idColumnName){
        super(columns, idType, idColumnName);
    }

    @Override
    public ProfileDetails<ID> implementMap(ResultSet resultSet) throws SQLException {
        ProfileDetails<ID> model = new ProfileDetails();
        model.setId(mapValueBySqlType(idType, resultSet, idColumnName));
        model.setName(resultSet.getString(columns[0]));
        model.setSurname(resultSet.getString(columns[1]));
        model.setMiddlename(resultSet.getString(columns[2]));
        model.setUserId(mapValueBySqlType(idType, resultSet, columns[3]));
        model.setMan(resultSet.getBoolean(columns[4]));
        model.setDateBirth(resultSet.getDate(columns[5]));
        model.setPhone(resultSet.getString(columns[6]));
        model.setCityId(mapValueBySqlType(idType, resultSet, columns[7]));
        return model;
    }
}
