package ru.strict.db.jdbc.mappers.sql;

import ru.strict.components.StrictWrapperRuntimeException;
import ru.strict.db.core.entities.StrictEntityRoleuser;
import ru.strict.db.core.mappers.sql.StrictMapperSqlBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StrictMapperSqlRoleuser extends StrictMapperSqlBase<StrictEntityRoleuser> {

    private final String[] COLUMNS_NAME;

    public StrictMapperSqlRoleuser(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public StrictEntityRoleuser implementMap(ResultSet resultSet) {
        StrictEntityRoleuser entity = new StrictEntityRoleuser();
        try {
            entity.setId(resultSet.getObject("id"));
            entity.setSymbols(resultSet.getString(COLUMNS_NAME[0]));
            entity.setDescription(resultSet.getString(COLUMNS_NAME[1]));
        }catch(SQLException ex){
            throw new StrictWrapperRuntimeException(ex);
        }
        return entity;
    }
}
