package ru.strict.db.spring.mappers.sql;

import org.springframework.jdbc.core.RowMapper;
import ru.strict.db.core.entities.EntityRoleuser;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperSqlRoleuser implements RowMapper<EntityRoleuser> {

    private final String[] COLUMNS_NAME;

    public MapperSqlRoleuser(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public EntityRoleuser mapRow(ResultSet resultSet, int i) throws SQLException {
        EntityRoleuser entity = new EntityRoleuser();
        entity.setId(resultSet.getObject("id"));
        entity.setCode(resultSet.getString(COLUMNS_NAME[0]));
        entity.setDescription(resultSet.getString(COLUMNS_NAME[1]));
        return entity;
    }
}
