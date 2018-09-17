package ru.strict.db.jdbc.mappers.sql;

import ru.strict.components.WrapperRuntimeException;
import ru.strict.db.core.entities.EntityCity;
import ru.strict.db.core.entities.EntityCountry;
import ru.strict.db.core.mappers.sql.MapperSqlBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperSqlCity<ID> extends MapperSqlBase<ID, EntityCity<ID>> {

    private final String[] COLUMNS_NAME;

    public MapperSqlCity(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public EntityCity<ID> implementMap(ResultSet resultSet) {
        EntityCity<ID> entity = new EntityCity();
        try {
            entity.setId((ID)resultSet.getObject("id"));
            entity.setCaption(resultSet.getString(COLUMNS_NAME[0]));
            entity.setCountryId((ID)resultSet.getObject(COLUMNS_NAME[1]));
        }catch(SQLException ex){
            throw new WrapperRuntimeException(ex);
        }
        return entity;
    }
}
