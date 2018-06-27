package ru.strict.db.spring.mappers.sql;

import org.springframework.jdbc.core.RowMapper;
import ru.strict.db.core.entities.EntityProfileInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperSqlProfileInfo implements RowMapper<EntityProfileInfo> {

    private final String[] COLUMNS_NAME;

    public MapperSqlProfileInfo(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public EntityProfileInfo mapRow(ResultSet resultSet, int i) throws SQLException {
        EntityProfileInfo entity = new EntityProfileInfo();
        entity.setId(resultSet.getObject("id"));
        entity.setName(resultSet.getString(COLUMNS_NAME[0]));
        entity.setSurname(resultSet.getString(COLUMNS_NAME[1]));
        entity.setMiddlename(resultSet.getString(COLUMNS_NAME[2]));
        entity.setUserId(resultSet.getObject(COLUMNS_NAME[3]));
        entity.setDateBirth(resultSet.getDate(COLUMNS_NAME[4]));
        entity.setPhone(resultSet.getString(COLUMNS_NAME[5]));
        entity.setCityId(resultSet.getObject(COLUMNS_NAME[6]));
        return entity;
    }
}
