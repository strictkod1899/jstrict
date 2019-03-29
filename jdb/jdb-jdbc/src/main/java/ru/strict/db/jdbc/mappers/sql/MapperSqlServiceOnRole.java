package ru.strict.db.jdbc.mappers.sql;

import ru.strict.db.core.entities.EntityServiceOnRole;
import ru.strict.db.core.mappers.sql.MapperSqlBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperSqlServiceOnRole<ID, SERVICE> extends MapperSqlBase<ID, EntityServiceOnRole<ID, SERVICE>> {

    private final String[] COLUMNS_NAME;

    public MapperSqlServiceOnRole(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public EntityServiceOnRole<ID, SERVICE> implementMap(ResultSet resultSet) {
        EntityServiceOnRole<ID, SERVICE> entity = new EntityServiceOnRole();
        try {
            entity.setId((ID)resultSet.getObject("id"));
            entity.setServiceId(resultSet.getInt(COLUMNS_NAME[0]));
            entity.setRoleId((ID)resultSet.getObject(COLUMNS_NAME[1]));
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }

        return entity;
    }
}
