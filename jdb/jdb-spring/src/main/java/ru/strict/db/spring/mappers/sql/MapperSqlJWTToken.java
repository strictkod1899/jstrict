package ru.strict.db.spring.mappers.sql;

import org.springframework.jdbc.core.RowMapper;
import ru.strict.components.WrapperRuntimeException;
import ru.strict.db.core.entities.EntityJWTToken;
import ru.strict.db.core.mappers.sql.MapperSqlBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperSqlJWTToken implements RowMapper<EntityJWTToken> {

    private final String[] COLUMNS_NAME;

    public MapperSqlJWTToken(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public EntityJWTToken mapRow(ResultSet resultSet, int i) throws SQLException {
        EntityJWTToken entity = new EntityJWTToken();
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
        }catch(SQLException ex){
            throw new WrapperRuntimeException(ex);
        }
        return entity;
    }
}
