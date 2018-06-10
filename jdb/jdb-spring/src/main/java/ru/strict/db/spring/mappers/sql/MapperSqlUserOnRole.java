package ru.strict.db.spring.mappers.sql;

import org.springframework.jdbc.core.RowMapper;
import ru.strict.db.core.entities.EntityUserOnRole;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperSqlUserOnRole implements RowMapper<EntityUserOnRole> {

    private final String[] COLUMNS_NAME;

    public MapperSqlUserOnRole(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public EntityUserOnRole mapRow(ResultSet resultSet, int i) throws SQLException {
        EntityUserOnRole entity = new EntityUserOnRole();
        entity.setId(resultSet.getObject("id"));
        entity.setUserId(resultSet.getObject(COLUMNS_NAME[0]));
        entity.setRoleId(resultSet.getObject(COLUMNS_NAME[1]));
        return entity;
    }
}
