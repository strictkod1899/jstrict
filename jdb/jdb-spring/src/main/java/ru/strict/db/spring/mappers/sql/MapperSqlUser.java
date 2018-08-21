package ru.strict.db.spring.mappers.sql;

import org.springframework.jdbc.core.RowMapper;
import ru.strict.db.core.entities.EntityUser;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperSqlUser implements RowMapper<EntityUser> {

    private final String[] COLUMNS_NAME;

    public MapperSqlUser(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public EntityUser mapRow(ResultSet resultSet, int i) throws SQLException {
        EntityUser entity = new EntityUser();
        entity.setId(resultSet.getObject("id"));
        entity.setUsername(resultSet.getString(COLUMNS_NAME[0]));
        entity.setPasswordEncode(resultSet.getString(COLUMNS_NAME[1]));
        entity.setEmail(resultSet.getString(COLUMNS_NAME[2]));
        entity.setBlocked(resultSet.getBoolean(COLUMNS_NAME[3]));
        entity.setDeleted(resultSet.getBoolean(COLUMNS_NAME[4]));
        entity.setConfirmEmail(resultSet.getBoolean(COLUMNS_NAME[5]));
        return entity;
    }
}
