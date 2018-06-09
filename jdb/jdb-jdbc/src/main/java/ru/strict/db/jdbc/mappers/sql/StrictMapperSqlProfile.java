package ru.strict.db.jdbc.mappers.sql;

import ru.strict.components.StrictWrapperRuntimeException;
import ru.strict.db.core.entities.StrictEntityProfile;
import ru.strict.db.core.mappers.sql.StrictMapperSqlBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StrictMapperSqlProfile extends StrictMapperSqlBase<StrictEntityProfile> {

    private final String[] COLUMNS_NAME;

    public StrictMapperSqlProfile(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public StrictEntityProfile implementMap(ResultSet resultSet) {
        StrictEntityProfile entity = new StrictEntityProfile();
        try {
            entity.setId(resultSet.getObject("id"));
            entity.setName(resultSet.getString(COLUMNS_NAME[0]));
            entity.setSurname(resultSet.getString(COLUMNS_NAME[1]));
            entity.setMiddlename(resultSet.getString(COLUMNS_NAME[2]));
            entity.setUserId(resultSet.getObject(COLUMNS_NAME[3]));
        }catch(SQLException ex){
            throw new StrictWrapperRuntimeException(ex);
        }
        return entity;
    }
}
