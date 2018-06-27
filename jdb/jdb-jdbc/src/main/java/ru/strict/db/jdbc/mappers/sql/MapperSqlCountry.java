package ru.strict.db.jdbc.mappers.sql;

import ru.strict.components.WrapperRuntimeException;
import ru.strict.db.core.entities.EntityCountry;
import ru.strict.db.core.entities.EntityRoleuser;
import ru.strict.db.core.mappers.sql.MapperSqlBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperSqlCountry extends MapperSqlBase<EntityCountry> {

    private final String[] COLUMNS_NAME;

    public MapperSqlCountry(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public EntityCountry implementMap(ResultSet resultSet) {
        EntityCountry entity = new EntityCountry();
        try {
            entity.setId(resultSet.getObject("id"));
            entity.setCaption(resultSet.getString(COLUMNS_NAME[0]));
        }catch(SQLException ex){
            throw new WrapperRuntimeException(ex);
        }
        return entity;
    }
}
