package ru.strict.db.jdbc.mappers.sql;

import ru.strict.components.WrapperRuntimeException;
import ru.strict.db.core.entities.EntityJWTUserToken;
import ru.strict.db.core.mappers.sql.MapperSqlBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperSqlJWTUserToken extends MapperSqlBase<EntityJWTUserToken> {

    private final String[] COLUMNS_NAME;

    public MapperSqlJWTUserToken(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public EntityJWTUserToken implementMap(ResultSet resultSet) {
        EntityJWTUserToken entity = new EntityJWTUserToken();
        try {
            entity.setId(resultSet.getObject("id"));
            entity.setAccessToken(resultSet.getString(COLUMNS_NAME[0]));
            entity.setRefreshToken(resultSet.getString(COLUMNS_NAME[1]));
            entity.setExpireTimeAccess(resultSet.getDate(COLUMNS_NAME[2]));
            entity.setExpireTimeRefresh(resultSet.getDate(COLUMNS_NAME[3]));
            entity.setIssuedAt(resultSet.getDate(COLUMNS_NAME[4]));
            entity.setIssuer(resultSet.getString(COLUMNS_NAME[5]));
            entity.setSubject(resultSet.getString(COLUMNS_NAME[6]));
            entity.setNotBefore(resultSet.getDate(COLUMNS_NAME[7]));
            entity.setAudience(resultSet.getString(COLUMNS_NAME[8]));
            entity.setSecret(resultSet.getString(COLUMNS_NAME[9]));
            entity.setAlgorithm(resultSet.getString(COLUMNS_NAME[10]));
            entity.setType(resultSet.getString(COLUMNS_NAME[11]));
            entity.setUserId(resultSet.getObject(COLUMNS_NAME[12]));
            entity.setRoleUserId(resultSet.getObject(COLUMNS_NAME[13]));
        }catch(SQLException ex){
            throw new WrapperRuntimeException(ex);
        }
        return entity;
    }
}