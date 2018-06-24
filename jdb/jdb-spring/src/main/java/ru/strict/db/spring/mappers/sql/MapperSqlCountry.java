package ru.strict.db.spring.mappers.sql;

import org.springframework.jdbc.core.RowMapper;
import ru.strict.components.WrapperRuntimeException;
import ru.strict.db.core.entities.EntityCountry;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperSqlCountry implements RowMapper<EntityCountry> {

    private final String[] COLUMNS_NAME;

    public MapperSqlCountry(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public EntityCountry mapRow(ResultSet resultSet, int i) throws SQLException {
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
