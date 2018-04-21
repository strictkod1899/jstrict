package ru.strict.db.repositories;

import ru.strict.db.connections.StrictCreateConnectionAny;
import ru.strict.db.dto.StrictDtoBase;
import ru.strict.db.entities.StrictEntityBase;
import ru.strict.db.mappers.StrictMapperBase;
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

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictRepositoryJdbc(SOURCE connectionSource, StrictMapperBase<E, DTO> mapper, String sqlSelect) {
        super(connectionSource, mapper);
        this.sqlSelect = sqlSelect;
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
            return createObject(resultSet);
        } catch (SQLException ex) {
            StrictUtilLogger.error(StrictRepositoryJdbc.class, ex.getClass().toString(), ex.getMessage());
            return null;
        }
    }

    @Override
    public List<DTO> readAll(StrictDbRequests wheres) {
        List<DTO> objects = createObjects(wheres);
        setObjects(objects);

        return objects;
    }

    /**
     * Получение списка объектов через запрос к базе данных
     * @param wheres Условия для выборки из базы данных
     * @return
     */
    private List<DTO> createObjects(StrictDbRequests wheres){
        PreparedStatement statement;
        ResultSet resultSet;
        List<DTO> result = new LinkedList<>();
        try {
            statement = createConnection().prepareStatement(getSqlSelect() + "?");
            statement.setString(1, wheres==null?"":wheres.getSql());
            resultSet = statement.executeQuery();
            while(resultSet.next())
                result.add(createObject(resultSet));
        } catch (SQLException ex) {
            StrictUtilLogger.error(StrictRepositoryJdbc.class, ex.getClass().toString(), ex.getMessage());
            return null;
        }
        return result;
    }

    /**
     * Создать единичный объект базы данных используя результат выборки из базы данных
     * @param data Результат выборки из базы данных
     * @return
     */
    protected abstract DTO createObject(ResultSet data);

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getSqlSelect() {
        return sqlSelect;
    }
    //</editor-fold>
}
