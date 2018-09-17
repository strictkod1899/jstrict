package ru.strict.db.spring.mappers.sql;

import org.springframework.jdbc.core.RowMapper;
import ru.strict.db.core.entities.EntityUserOnRole;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperSqlUserOnRole<ID> implements RowMapper<EntityUserOnRole<ID>> {

    private final String[] COLUMNS_NAME;

    public MapperSqlUserOnRole(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public EntityUserOnRole<ID> mapRow(ResultSet resultSet, int i) throws SQLException {
        EntityUserOnRole<ID> entity = new EntityUserOnRole();
        entity.setId((ID)resultSet.getObject("id"));
        entity.setUserId((ID)resultSet.getObject(COLUMNS_NAME[0]));
        entity.setRoleId((ID)resultSet.getObject(COLUMNS_NAME[1]));
        return entity;
    }
}
