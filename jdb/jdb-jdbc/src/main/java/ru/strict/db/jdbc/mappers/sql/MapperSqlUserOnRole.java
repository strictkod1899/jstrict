package ru.strict.db.jdbc.mappers.sql;


import ru.strict.db.core.entities.EntityUserOnRole;
import ru.strict.db.core.mappers.sql.MapperSqlBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperSqlUserOnRole<ID> extends MapperSqlBase<ID, EntityUserOnRole<ID>> {

    private final String[] COLUMNS_NAME;

    public MapperSqlUserOnRole(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public EntityUserOnRole<ID> implementMap(ResultSet resultSet) {
        EntityUserOnRole<ID> entity = new EntityUserOnRole();
        try {
            entity.setId((ID)resultSet.getObject("id"));
            entity.setUserId((ID)resultSet.getObject(COLUMNS_NAME[0]));
            entity.setRoleId((ID)resultSet.getObject(COLUMNS_NAME[1]));
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }

        return entity;
    }
}
