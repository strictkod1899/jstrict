package ru.strict.db.jdbc.mappers.sql;

import ru.strict.components.StrictWrapperRuntimeException;
import ru.strict.db.core.entities.StrictEntityProfileInfo;
import ru.strict.db.core.mappers.sql.StrictMapperSqlBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StrictMapperSqlProfileInfo extends StrictMapperSqlBase<StrictEntityProfileInfo> {

    private final String[] COLUMNS_NAME;

    public StrictMapperSqlProfileInfo(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public StrictEntityProfileInfo implementMap(ResultSet resultSet) {
        StrictEntityProfileInfo entity = new StrictEntityProfileInfo();
        try {
            entity.setId(resultSet.getObject("id"));
            entity.setName(resultSet.getString(COLUMNS_NAME[0]));
            entity.setSurname(resultSet.getString(COLUMNS_NAME[1]));
            entity.setMiddlename(resultSet.getString(COLUMNS_NAME[2]));
            entity.setUserId(resultSet.getObject(COLUMNS_NAME[3]));
            entity.setDateBirth(resultSet.getDate(COLUMNS_NAME[4]));
            entity.setPhone(resultSet.getString(COLUMNS_NAME[5]));
            entity.setCountry(resultSet.getString(COLUMNS_NAME[6]));
            entity.setCity(resultSet.getString(COLUMNS_NAME[7]));
            entity.setAddress(resultSet.getString(COLUMNS_NAME[8]));
        }catch(SQLException ex){
            throw new StrictWrapperRuntimeException(ex);
        }
        return entity;
    }
}
