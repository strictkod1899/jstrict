package ru.strict.db.spring.mappers.sql;

import org.springframework.jdbc.core.RowMapper;
import ru.strict.components.WrapperRuntimeException;
import ru.strict.db.core.entities.EntityCity;
import ru.strict.db.core.mappers.sql.MapperSqlBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperSqlCity implements RowMapper<EntityCity> {

    private final String[] COLUMNS_NAME;

    public MapperSqlCity(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public EntityCity mapRow(ResultSet resultSet, int i) throws SQLException {
        EntityCity entity = new EntityCity();
        try {
            entity.setId(resultSet.getObject("id"));
            entity.setCaption(resultSet.getString(COLUMNS_NAME[0]));
            entity.setCountryId(resultSet.getObject(COLUMNS_NAME[1]));
        }catch(SQLException ex){
            throw new WrapperRuntimeException(ex);
        }
        return entity;
    }
}
