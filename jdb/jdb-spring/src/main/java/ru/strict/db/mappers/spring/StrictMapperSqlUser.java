package ru.strict.db.mappers.spring;

import org.springframework.jdbc.core.RowMapper;
import ru.strict.db.entities.StrictEntityUser;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StrictMapperSqlUser implements RowMapper<StrictEntityUser> {

    private final String[] COLUMNS_NAME;

    public StrictMapperSqlUser(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public StrictEntityUser mapRow(ResultSet resultSet, int i) throws SQLException {
        StrictEntityUser entity = new StrictEntityUser();
        entity.setId(resultSet.getObject("id"));
        entity.setUsername(resultSet.getString(COLUMNS_NAME[0]));
        entity.setPasswordEncode(resultSet.getString(COLUMNS_NAME[1]));
        entity.setToken(resultSet.getString(COLUMNS_NAME[2]));
        return entity;
    }
}
