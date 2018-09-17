package ru.strict.db.spring.mappers.sql;

import org.springframework.jdbc.core.RowMapper;
import ru.strict.db.core.entities.EntityRoleuser;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperSqlRoleuser<ID> implements RowMapper<EntityRoleuser<ID>> {

    private final String[] COLUMNS_NAME;

    public MapperSqlRoleuser(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public EntityRoleuser<ID> mapRow(ResultSet resultSet, int i) throws SQLException {
        EntityRoleuser<ID> entity = new EntityRoleuser();
        entity.setId((ID)resultSet.getObject("id"));
        entity.setCode(resultSet.getString(COLUMNS_NAME[0]));
        entity.setDescription(resultSet.getString(COLUMNS_NAME[1]));
        return entity;
    }
}
