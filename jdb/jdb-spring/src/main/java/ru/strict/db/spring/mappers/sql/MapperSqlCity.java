package ru.strict.db.spring.mappers.sql;

import org.springframework.jdbc.core.RowMapper;
import ru.strict.components.WrapperRuntimeException;
import ru.strict.db.core.entities.EntityCity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperSqlCity<ID> implements RowMapper<EntityCity<ID>> {

    private final String[] COLUMNS_NAME;

    public MapperSqlCity(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public EntityCity<ID> mapRow(ResultSet resultSet, int i) throws SQLException {
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
