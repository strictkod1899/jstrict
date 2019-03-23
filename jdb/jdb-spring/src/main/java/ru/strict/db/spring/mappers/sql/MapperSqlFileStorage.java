package ru.strict.db.spring.mappers.sql;

import org.springframework.jdbc.core.RowMapper;
import ru.strict.db.core.entities.EntityFileStorage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperSqlFileStorage<ID> implements RowMapper<EntityFileStorage<ID>> {

    private final String[] COLUMNS_NAME;

    public MapperSqlFileStorage(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public EntityFileStorage<ID> mapRow(ResultSet resultSet, int i) throws SQLException {
        EntityFileStorage<ID> entity = new EntityFileStorage();
        entity.setId((ID)resultSet.getObject("id"));
        entity.setFilename(resultSet.getString(COLUMNS_NAME[0]));
        entity.setContent(resultSet.getBytes(COLUMNS_NAME[1]));
        entity.setFilePath(resultSet.getString(COLUMNS_NAME[2]));
        entity.setCreateDate(resultSet.getDate(COLUMNS_NAME[3]));
        entity.setType(resultSet.getString(COLUMNS_NAME[4]));
        return entity;
    }
}
