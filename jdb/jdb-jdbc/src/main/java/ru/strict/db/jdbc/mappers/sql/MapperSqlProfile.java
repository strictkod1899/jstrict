package ru.strict.db.jdbc.mappers.sql;


import ru.strict.db.core.entities.EntityProfile;
import ru.strict.db.core.mappers.sql.MapperSqlBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperSqlProfile<ID> extends MapperSqlBase<ID, EntityProfile<ID>> {

    private final String[] COLUMNS_NAME;

    public MapperSqlProfile(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public EntityProfile<ID> implementMap(ResultSet resultSet) {
        EntityProfile<ID> entity = new EntityProfile();
        try {
            entity.setId((ID)resultSet.getObject("id"));
            entity.setName(resultSet.getString(COLUMNS_NAME[0]));
            entity.setSurname(resultSet.getString(COLUMNS_NAME[1]));
            entity.setMiddlename(resultSet.getString(COLUMNS_NAME[2]));
            entity.setUserId((ID)resultSet.getObject(COLUMNS_NAME[3]));
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
        return entity;
    }
}
