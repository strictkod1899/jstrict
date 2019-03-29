package ru.strict.db.spring.mappers.sql;

import org.springframework.jdbc.core.RowMapper;
import ru.strict.db.core.entities.EntityServiceOnRole;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperSqlServiceOnRole<ID, SERVICE> implements RowMapper<EntityServiceOnRole<ID, SERVICE>> {

    private final String[] COLUMNS_NAME;

    public MapperSqlServiceOnRole(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public EntityServiceOnRole<ID, SERVICE> mapRow(ResultSet resultSet, int i) throws SQLException {
        EntityServiceOnRole<ID, SERVICE> entity = new EntityServiceOnRole();
        entity.setId((ID)resultSet.getObject("id"));
        entity.setServiceId(resultSet.getInt(COLUMNS_NAME[0]));
        entity.setRoleId((ID)resultSet.getObject(COLUMNS_NAME[1]));
        return entity;
    }
}
