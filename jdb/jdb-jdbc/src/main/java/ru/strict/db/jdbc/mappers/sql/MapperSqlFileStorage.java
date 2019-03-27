package ru.strict.db.jdbc.mappers.sql;

import ru.strict.db.core.entities.EntityFileStorage;
import ru.strict.db.core.mappers.sql.MapperSqlBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperSqlFileStorage<ID> extends MapperSqlBase<ID, EntityFileStorage<ID>> {

    private final String[] COLUMNS_NAME;

    public MapperSqlFileStorage(String[] columnsName){
        this.COLUMNS_NAME = columnsName;
    }

    @Override
    public EntityFileStorage<ID> implementMap(ResultSet resultSet) {
        EntityFileStorage<ID> entity = new EntityFileStorage();
        try {
            entity.setId((ID)resultSet.getObject("id"));
            entity.setFilename(resultSet.getString(COLUMNS_NAME[0]));
            entity.setExtension(resultSet.getString(COLUMNS_NAME[1]));
            entity.setDisplayName(resultSet.getString(COLUMNS_NAME[2]));
            entity.setContent(resultSet.getBytes(COLUMNS_NAME[3]));
            entity.setFilePath(resultSet.getString(COLUMNS_NAME[4]));
            entity.setCreateDate(resultSet.getDate(COLUMNS_NAME[5]));
            entity.setType(resultSet.getInt(COLUMNS_NAME[6]));
            entity.setStatus(resultSet.getInt(COLUMNS_NAME[7]));
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
        return entity;
    }
}
