package ru.strict.db.jdbc.mappers.sql;

import ru.strict.components.WrapperRuntimeException;
import ru.strict.db.core.entities.EntityRoleuser;
import ru.strict.db.core.mappers.sql.MapperSqlBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperSqlRoleuser<ID> extends MapperSqlBase<ID, EntityRoleuser<ID>> {

    private final String[] COLUMNS_NAME;

    public MapperSqlRoleuser(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public EntityRoleuser<ID> implementMap(ResultSet resultSet) {
        EntityRoleuser<ID> entity = new EntityRoleuser();
        try {
            entity.setId((ID)resultSet.getObject("id"));
            entity.setCode(resultSet.getString(COLUMNS_NAME[0]));
            entity.setDescription(resultSet.getString(COLUMNS_NAME[1]));
        }catch(SQLException ex){
            throw new WrapperRuntimeException(ex);
        }
        return entity;
    }
}
