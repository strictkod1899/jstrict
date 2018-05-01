package ru.strict.db.mappers.spring;

import org.springframework.jdbc.core.RowMapper;
import ru.strict.db.entities.StrictEntityUser;
import ru.strict.db.entities.StrictEntityUserOnRole;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StrictMapperSqlUserOnRole implements RowMapper<StrictEntityUserOnRole> {

    private final String[] COLUMNS_NAME;

    public StrictMapperSqlUserOnRole(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public StrictEntityUserOnRole mapRow(ResultSet resultSet, int i) throws SQLException {
        StrictEntityUserOnRole entity = new StrictEntityUserOnRole();
        entity.setId(resultSet.getObject("id"));
        entity.setUserId(resultSet.getObject(COLUMNS_NAME[0]));
        entity.setRoleId(resultSet.getObject(COLUMNS_NAME[1]));
        return entity;
    }
}
