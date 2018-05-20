package ru.strict.db.repositories;

import ru.strict.db.connections.StrictCreateConnectionAny;
import ru.strict.db.dto.StrictDtoBase;
import ru.strict.db.entities.StrictEntityBase;
import ru.strict.db.mappers.dto.StrictMapperDtoBase;
import ru.strict.db.mappers.sql.StrictMapperSqlBase;
import ru.strict.db.requests.StrictDbRequests;
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
public abstract class StrictRepositoryJdbc
        <ID, SOURCE extends StrictCreateConnectionAny, E extends StrictEntityBase, DTO extends StrictDtoBase>
        extends StrictRepositoryBase<ID, SOURCE, E, DTO>{

    /**
     * Sql-запрос на выборку данных из таблицы
     */
    private String sqlSelect;

    /**
     * Объект для преобразования полученных данных из sql-запроса в объект сущности азы данных (entity)
     */
    private StrictMapperSqlBase<E> sqlMapper;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictRepositoryJdbc(String tableName, String[] columnsName, SOURCE connectionSource, StrictMapperDtoBase<E, DTO> dtoMapper, 
    							StrictMapperSqlBase<E> sqlMapper, boolean isGenerateId, String sqlSelect) {
        super(tableName, columnsName, connectionSource, dtoMapper, isGenerateId);
        this.sqlSelect = sqlSelect;
        this.sqlMapper = sqlMapper;
    }
    //</editor-fold>

    @Override
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
                StrictUtilLogger.error(StrictRepositoryJdbc.class, "Error sql-query [read]: ID type not supported");
                throw new IllegalArgumentException("Error sql-query [read]: ID type not supported");
            }

            resultSet = statement.executeQuery();
            return getDtoMapper().map(sqlMapper.map(resultSet));
        } catch (SQLException ex) {
            StrictUtilLogger.error(StrictRepositoryJdbc.class, ex.getClass().toString(), ex.getMessage());
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
            StrictUtilLogger.error(StrictRepositoryJdbc.class, ex.getClass().toString(), ex.getMessage());
            return null;
        }
        setObjects(result);

        return result;
    }

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getSqlSelect() {
        return sqlSelect;
    }

    public StrictMapperSqlBase<E> getSqlMapper() {
        return sqlMapper;
    }
    //</editor-fold>
}
