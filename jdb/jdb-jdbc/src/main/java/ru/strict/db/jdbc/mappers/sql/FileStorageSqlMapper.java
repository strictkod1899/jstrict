package ru.strict.db.jdbc.mappers.sql;

import ru.strict.models.FileStorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;

public class FileStorageSqlMapper<ID> extends BaseSqlMapper<ID, FileStorage<ID>> {

    public FileStorageSqlMapper(String[] columns, SQLType idType, String idColumnName){
        super(columns, idType, idColumnName);
    }

    @Override
    public FileStorage<ID> implementMap(ResultSet resultSet) throws SQLException {
        FileStorage<ID> model = new FileStorage();
        model.setId(mapValueBySqlType(idType, resultSet, idColumnName));
        model.setFilename(resultSet.getString(columns[0]));
        model.setExtension(resultSet.getString(columns[1]));
        model.setDisplayName(resultSet.getString(columns[2]));
        model.setContent(resultSet.getBytes(columns[3]));
        model.setFilePath(resultSet.getString(columns[4]));
        model.setCreateDate(resultSet.getDate(columns[5]));
        model.setType(resultSet.getInt(columns[6]));
        model.setStatus(resultSet.getInt(columns[7]));
        return model;
    }
}
