package ru.strict.db.jdbc.mappers.sql;

import ru.strict.db.core.entities.EntityCountry;
import ru.strict.db.core.mappers.sql.MapperSqlBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperSqlCountry<ID> extends MapperSqlBase<ID, EntityCountry<ID>> {

    private final String[] COLUMNS_NAME;

    public MapperSqlCountry(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public EntityCountry<ID> implementMap(ResultSet resultSet) {
        EntityCountry<ID> entity = new EntityCountry();
        try {
            entity.setId((ID)resultSet.getObject("id"));
            entity.setCaption(resultSet.getString(COLUMNS_NAME[0]));
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
        return entity;
    }
}
