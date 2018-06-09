package ru.strict.db.jdbc.mappers.sql;

import ru.strict.components.StrictWrapperRuntimeException;
import ru.strict.db.core.entities.StrictEntityUserOnRole;
import ru.strict.db.core.mappers.sql.StrictMapperSqlBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StrictMapperSqlUserOnRole extends StrictMapperSqlBase<StrictEntityUserOnRole> {

    private final String[] COLUMNS_NAME;

    public StrictMapperSqlUserOnRole(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public StrictEntityUserOnRole implementMap(ResultSet resultSet) {
        StrictEntityUserOnRole entity = new StrictEntityUserOnRole();
        try {
            entity.setId(resultSet.getObject("id"));
            entity.setUserId(resultSet.getObject(COLUMNS_NAME[0]));
            entity.setRoleId(resultSet.getObject(COLUMNS_NAME[1]));
        }catch(SQLException ex){
            throw new StrictWrapperRuntimeException(ex);
        }

        return entity;
    }
}
