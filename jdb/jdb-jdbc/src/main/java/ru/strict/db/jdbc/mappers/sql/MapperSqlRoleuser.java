package ru.strict.db.jdbc.mappers.sql;

import ru.strict.components.WrapperRuntimeException;
import ru.strict.db.core.entities.EntityRoleuser;
import ru.strict.db.core.mappers.sql.MapperSqlBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperSqlRoleuser extends MapperSqlBase<EntityRoleuser> {

    private final String[] COLUMNS_NAME;

    public MapperSqlRoleuser(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public EntityRoleuser implementMap(ResultSet resultSet) {
        EntityRoleuser entity = new EntityRoleuser();
        try {
            entity.setId(resultSet.getObject("id"));
            entity.setSymbols(resultSet.getString(COLUMNS_NAME[0]));
            entity.setDescription(resultSet.getString(COLUMNS_NAME[1]));
        }catch(SQLException ex){
            throw new WrapperRuntimeException(ex);
        }
        return entity;
    }
}
