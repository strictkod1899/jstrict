package ru.strict.db.jdbc.mappers.sql;

import ru.strict.models.JWTToken;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;

public class MapperSqlJWTToken<ID> extends MapperSqlBase<ID, JWTToken<ID>> {

    public MapperSqlJWTToken(String[] columns, SQLType idType, String idColumnName){
        super(columns, idType, idColumnName);
    }

    @Override
    public JWTToken<ID> implementMap(ResultSet resultSet) throws SQLException {
        JWTToken<ID> model = new JWTToken();
        model.setId(mapValueBySqlType(idType, resultSet, idColumnName));
        model.setAccessToken(resultSet.getString(columns[0]));
        model.setRefreshToken(resultSet.getString(columns[1]));
        model.setExpireTimeAccess(resultSet.getDate(columns[2]));
        model.setExpireTimeRefresh(resultSet.getDate(columns[3]));
        model.setIssuedAt(resultSet.getDate(columns[4]));
        model.setIssuer(resultSet.getString(columns[5]));
        model.setSubject(resultSet.getString(columns[6]));
        model.setNotBefore(resultSet.getDate(columns[7]));
        model.setAudience(resultSet.getString(columns[8]));
        model.setSecret(resultSet.getString(columns[9]));
        model.setAlgorithm(resultSet.getString(columns[10]));
        model.setType(resultSet.getString(columns[11]));
        model.setUserId(mapValueBySqlType(idType, resultSet, columns[12]));
        return model;
    }
}
