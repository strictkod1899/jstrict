package ru.strict.db.spring.mappers.sql;

import org.springframework.jdbc.core.RowMapper;
import ru.strict.db.core.entities.EntityProfile;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperSqlProfile implements RowMapper<EntityProfile> {

    private final String[] COLUMNS_NAME;

    public MapperSqlProfile(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public EntityProfile mapRow(ResultSet resultSet, int i) throws SQLException {
        EntityProfile entity = new EntityProfile();
        entity.setId(resultSet.getObject("id"));
        entity.setName(resultSet.getString(COLUMNS_NAME[0]));
        entity.setSurname(resultSet.getString(COLUMNS_NAME[1]));
        entity.setMiddlename(resultSet.getString(COLUMNS_NAME[2]));
        entity.setUserId(resultSet.getObject(COLUMNS_NAME[3]));
        return entity;
    }
}
