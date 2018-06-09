package ru.strict.db.spring.security;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StrictMapperSqlUserSecurity implements RowMapper<StrictEntityUserSecurity> {

    private final String[] COLUMNS_NAME;

    public StrictMapperSqlUserSecurity(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public StrictEntityUserSecurity mapRow(ResultSet resultSet, int i) throws SQLException {
        StrictEntityUserSecurity entity = new StrictEntityUserSecurity();
        entity.setId(resultSet.getObject("id"));
        entity.setUsername(resultSet.getString(COLUMNS_NAME[0]));
        entity.setPasswordEncode(resultSet.getString(COLUMNS_NAME[1]));
        entity.setToken(resultSet.getString(COLUMNS_NAME[2]));
        return entity;
    }
}
