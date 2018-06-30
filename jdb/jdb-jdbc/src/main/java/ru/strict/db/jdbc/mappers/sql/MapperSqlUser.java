package ru.strict.db.jdbc.mappers.sql;

import ru.strict.components.WrapperRuntimeException;
import ru.strict.db.core.entities.EntityUser;
import ru.strict.db.core.mappers.sql.MapperSqlBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperSqlUser extends MapperSqlBase<EntityUser> {

    private final String[] COLUMNS_NAME;

    public MapperSqlUser(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public EntityUser implementMap(ResultSet resultSet) {
        EntityUser entity = new EntityUser();
        try {
            entity.setId(resultSet.getObject("id"));
            entity.setUsername(resultSet.getString(COLUMNS_NAME[0]));
            entity.setPasswordEncode(resultSet.getString(COLUMNS_NAME[1]));
        }catch(SQLException ex){
            throw new WrapperRuntimeException(ex);
        }
        return entity;
    }
}
