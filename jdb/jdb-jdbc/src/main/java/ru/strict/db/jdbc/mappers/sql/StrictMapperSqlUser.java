package ru.strict.db.jdbc.mappers.sql;

import ru.strict.components.StrictWrapperRuntimeException;
import ru.strict.db.core.entities.StrictEntityUser;
import ru.strict.db.core.mappers.sql.StrictMapperSqlBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StrictMapperSqlUser extends StrictMapperSqlBase<StrictEntityUser> {

    private final String[] COLUMNS_NAME;

    public StrictMapperSqlUser(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public StrictEntityUser implementMap(ResultSet resultSet) {
        StrictEntityUser entity = new StrictEntityUser();
        try {
            entity.setId(resultSet.getObject("id"));
            entity.setUsername(resultSet.getString(COLUMNS_NAME[0]));
            entity.setPasswordEncode(resultSet.getString(COLUMNS_NAME[1]));
            entity.setToken(resultSet.getString(COLUMNS_NAME[2]));
        }catch(SQLException ex){
            throw new StrictWrapperRuntimeException(ex);
        }
        return entity;
    }
}
