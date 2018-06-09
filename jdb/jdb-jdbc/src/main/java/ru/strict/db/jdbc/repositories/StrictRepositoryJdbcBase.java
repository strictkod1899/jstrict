package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.connections.StrictCreateConnectionAny;
import ru.strict.db.core.dto.StrictDtoBase;
import ru.strict.db.core.entities.StrictEntityBase;
import ru.strict.db.core.mappers.dto.StrictMapperDtoBase;
import ru.strict.db.core.mappers.sql.StrictMapperSqlBase;
import ru.strict.db.core.repositories.StrictRepositoryBase;
import ru.strict.db.core.requests.StrictDbRequests;
import ru.strict.utils.StrictUtilLogger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Базовый класс репозитория с использованием Jdbc
 */
public abstract class StrictRepositoryJdbcBase
        <ID, SOURCE extends StrictCreateConnectionAny, E extends StrictEntityBase, DTO extends StrictDtoBase>
        extends StrictRepositoryBase<ID, SOURCE, E, DTO> {

    /**
     * Объект для преобразования полученных данных из sql-запроса в объект сущности азы данных (entity)
     */
    private StrictMapperSqlBase<E> sqlMapper;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictRepositoryJdbcBase(String tableName, String[] columnsName,
                                    SOURCE connectionSource,
                                    StrictMapperDtoBase<E, DTO> dtoMapper,
                                    StrictMapperSqlBase<E> sqlMapper, boolean isGenerateId) {
        super(tableName, columnsName, connectionSource, dtoMapper, isGenerateId);
        this.sqlMapper = sqlMapper;
    }
    //</editor-fold>

    /*@Override
    public DTO read(ID id) {
        PreparedStatement statement;
        ResultSet resultSet;
        try {
            statement = createConnection().prepareStatement(getSqlSelect() + " WHERE id = ?");

            if(id instanceof Integer)
                statement.setInt(1, (Integer) id);
            else if(id instanceof Long)
                statement.setLong(1, (Long) id);
            else if(id instanceof UUID)
                statement.setString(1, ((UUID)id).toString());
            else if(id instanceof String)
                statement.setString(1, (String)id);
            else{
                StrictUtilLogger.error(StrictRepositoryJdbcBase.class, "Error sql-query [read]: ID type not supported");
                throw new IllegalArgumentException("Error sql-query [read]: ID type not supported");
            }

            resultSet = statement.executeQuery();
            return getDtoMapper().map(sqlMapper.map(resultSet));
        } catch (SQLException ex) {
            StrictUtilLogger.error(StrictRepositoryJdbcBase.class, ex.getClass().toString(), ex.getMessage());
            return null;
        }
    }

    @Override
    public List<DTO> readAll(StrictDbRequests requests) {
        PreparedStatement statement;
        ResultSet resultSet;
        List<DTO> result = new LinkedList<>();
        try {
            statement = createConnection().prepareStatement(getSqlSelect() + "?");
            statement.setString(1, requests ==null?"": requests.getSql());
            resultSet = statement.executeQuery();
            while(resultSet.next())
                result.add(getDtoMapper().map(sqlMapper.map(resultSet)));
        } catch (SQLException ex) {
            StrictUtilLogger.error(StrictRepositoryJdbcBase.class, ex.getClass().toString(), ex.getMessage());
            return null;
        }
        setObjects(result);

        return result;
    }*/

    //<editor-fold defaultState="collapsed" desc="Get/Set"
    public StrictMapperSqlBase<E> getSqlMapper() {
        return sqlMapper;
    }
    //</editor-fold>
}
