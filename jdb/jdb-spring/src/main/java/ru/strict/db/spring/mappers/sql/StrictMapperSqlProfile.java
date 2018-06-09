package ru.strict.db.spring.mappers.sql;

import org.springframework.jdbc.core.RowMapper;
import ru.strict.db.core.entities.StrictEntityProfile;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StrictMapperSqlProfile implements RowMapper<StrictEntityProfile> {

    private final String[] COLUMNS_NAME;

    public StrictMapperSqlProfile(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public StrictEntityProfile mapRow(ResultSet resultSet, int i) throws SQLException {
        StrictEntityProfile entity = new StrictEntityProfile();
        entity.setId(resultSet.getObject("id"));
        entity.setName(resultSet.getString(COLUMNS_NAME[0]));
        entity.setSurname(resultSet.getString(COLUMNS_NAME[1]));
        entity.setMiddlename(resultSet.getString(COLUMNS_NAME[2]));
        entity.setUserId(resultSet.getObject(COLUMNS_NAME[3]));
        return entity;
    }
}
