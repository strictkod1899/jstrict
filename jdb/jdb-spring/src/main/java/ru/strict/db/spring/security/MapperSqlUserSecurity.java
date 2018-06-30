package ru.strict.db.spring.security;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperSqlUserSecurity implements RowMapper<EntityUserSecurity> {

    private final String[] COLUMNS_NAME;

    public MapperSqlUserSecurity(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public EntityUserSecurity mapRow(ResultSet resultSet, int i) throws SQLException {
        EntityUserSecurity entity = new EntityUserSecurity();
        entity.setId(resultSet.getObject("id"));
        entity.setUsername(resultSet.getString(COLUMNS_NAME[0]));
        entity.setPasswordEncode(resultSet.getString(COLUMNS_NAME[1]));
        return entity;
    }
}
