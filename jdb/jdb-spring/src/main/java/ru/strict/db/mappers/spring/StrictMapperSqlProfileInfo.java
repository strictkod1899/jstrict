package ru.strict.db.mappers.spring;

import org.springframework.jdbc.core.RowMapper;
import ru.strict.db.entities.StrictEntityProfileInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StrictMapperSqlProfileInfo implements RowMapper<StrictEntityProfileInfo> {

    private final String[] COLUMNS_NAME;

    public StrictMapperSqlProfileInfo(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public StrictEntityProfileInfo mapRow(ResultSet resultSet, int i) throws SQLException {
        StrictEntityProfileInfo entity = new StrictEntityProfileInfo();
        entity.setId(resultSet.getObject("id"));
        entity.setName(resultSet.getString(COLUMNS_NAME[0]));
        entity.setSurname(resultSet.getString(COLUMNS_NAME[1]));
        entity.setMiddlename(COLUMNS_NAME[2]);
        entity.setDateBirth(resultSet.getDate(COLUMNS_NAME[3]));
        entity.setPhone(resultSet.getString(COLUMNS_NAME[4]));
        entity.setCounrty(resultSet.getString(COLUMNS_NAME[5]));
        entity.setCity(resultSet.getString(COLUMNS_NAME[6]));
        entity.setAddress(resultSet.getString(COLUMNS_NAME[7]));
        return entity;
    }
}
