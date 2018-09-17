package ru.strict.db.jdbc.mappers.sql;

import ru.strict.components.WrapperRuntimeException;
import ru.strict.db.core.entities.EntityUser;
import ru.strict.db.core.mappers.sql.MapperSqlBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperSqlUser<ID> extends MapperSqlBase<ID, EntityUser<ID>> {

    private final String[] COLUMNS_NAME;

    public MapperSqlUser(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public EntityUser<ID> implementMap(ResultSet resultSet) {
        EntityUser<ID> entity = new EntityUser();
        try {
            entity.setId((ID)resultSet.getObject("id"));
            entity.setUsername(resultSet.getString(COLUMNS_NAME[0]));
            entity.setPasswordEncode(resultSet.getString(COLUMNS_NAME[1]));
            entity.setEmail(resultSet.getString(COLUMNS_NAME[2]));
            entity.setBlocked(resultSet.getBoolean(COLUMNS_NAME[3]));
            entity.setDeleted(resultSet.getBoolean(COLUMNS_NAME[4]));
            entity.setConfirmEmail(resultSet.getBoolean(COLUMNS_NAME[5]));
        }catch(SQLException ex){
            throw new WrapperRuntimeException(ex);
        }
        return entity;
    }
}
