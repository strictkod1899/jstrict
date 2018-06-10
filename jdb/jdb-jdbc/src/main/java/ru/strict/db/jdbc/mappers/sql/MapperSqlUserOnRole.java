package ru.strict.db.jdbc.mappers.sql;

import ru.strict.components.WrapperRuntimeException;
import ru.strict.db.core.entities.EntityUserOnRole;
import ru.strict.db.core.mappers.sql.MapperSqlBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperSqlUserOnRole extends MapperSqlBase<EntityUserOnRole> {

    private final String[] COLUMNS_NAME;

    public MapperSqlUserOnRole(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public EntityUserOnRole implementMap(ResultSet resultSet) {
        EntityUserOnRole entity = new EntityUserOnRole();
        try {
            entity.setId(resultSet.getObject("id"));
            entity.setUserId(resultSet.getObject(COLUMNS_NAME[0]));
            entity.setRoleId(resultSet.getObject(COLUMNS_NAME[1]));
        }catch(SQLException ex){
            throw new WrapperRuntimeException(ex);
        }

        return entity;
    }
}
