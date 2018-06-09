package ru.strict.db.spring.mappers.sql;

import org.springframework.jdbc.core.RowMapper;
import ru.strict.db.core.entities.StrictEntityRoleuser;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StrictMapperSqlRoleuser implements RowMapper<StrictEntityRoleuser> {

    private final String[] COLUMNS_NAME;

    public StrictMapperSqlRoleuser(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public StrictEntityRoleuser mapRow(ResultSet resultSet, int i) throws SQLException {
        StrictEntityRoleuser entity = new StrictEntityRoleuser();
        entity.setId(resultSet.getObject("id"));
        entity.setSymbols(resultSet.getString(COLUMNS_NAME[0]));
        entity.setDescription(resultSet.getString(COLUMNS_NAME[1]));
        return entity;
    }
}
