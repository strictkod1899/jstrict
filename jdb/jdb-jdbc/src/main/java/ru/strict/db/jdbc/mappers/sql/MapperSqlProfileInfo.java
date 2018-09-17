package ru.strict.db.jdbc.mappers.sql;

import ru.strict.components.WrapperRuntimeException;
import ru.strict.db.core.entities.EntityProfileInfo;
import ru.strict.db.core.mappers.sql.MapperSqlBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperSqlProfileInfo<ID> extends MapperSqlBase<ID, EntityProfileInfo<ID>> {

    private final String[] COLUMNS_NAME;

    public MapperSqlProfileInfo(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public EntityProfileInfo<ID> implementMap(ResultSet resultSet) {
        EntityProfileInfo<ID> entity = new EntityProfileInfo();
        try {
            entity.setId((ID)resultSet.getObject("id"));
            entity.setName(resultSet.getString(COLUMNS_NAME[0]));
            entity.setSurname(resultSet.getString(COLUMNS_NAME[1]));
            entity.setMiddlename(resultSet.getString(COLUMNS_NAME[2]));
            entity.setUserId((ID)resultSet.getObject(COLUMNS_NAME[3]));
            entity.setDateBirth(resultSet.getDate(COLUMNS_NAME[4]));
            entity.setPhone(resultSet.getString(COLUMNS_NAME[5]));
            entity.setCityId((ID)resultSet.getObject(COLUMNS_NAME[6]));
        }catch(SQLException ex){
            throw new WrapperRuntimeException(ex);
        }
        return entity;
    }
}
