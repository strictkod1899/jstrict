package ru.strict.db.repositories;

import ru.strict.db.dto.StrictDtoBase;
import ru.strict.db.enums.StrictDataState;
import ru.strict.db.mappers.StrictBaseMapper;
import ru.strict.db.requests.StrictDbRequests;
import ru.strict.db.entities.StrictEntityBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public abstract class StrictRepositoryBase
        <ID, SOURCE, E extends StrictEntityBase, DTO extends StrictDtoBase>
        implements StrictRepositoryAny<ID, E, DTO>{

    /**
     * Источник подключения к базе данных (используется для получения объекта Connection),
     * например, DataSource, StringConnectionInfo и др.
     */
    private SOURCE connectionSource;

    /**
     * Маппер связанной сущности/dto
     */
    private StrictBaseMapper<E, DTO> mapper;

    /**
     * Кэшированный список объектов
     */
    private List<DTO> objects;

    /**
     * Текущее состояние кэшированный значений
     */
    private StrictDataState state;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictRepositoryBase(SOURCE connectionSource, StrictBaseMapper<E, DTO> mapper) {
        this.connectionSource = connectionSource;
        this.mapper = mapper;
        objects = new LinkedList<>();
        state = StrictDataState.NONE;
    }
    //</editor-fold>

    /**
     * TODO: Удалить метод: перенести в модуль jdbc
     * Получение списка объектов через запрос к базе данных
     * @return
     */
    private List<E> createObjects(StrictDbRequests wheres){
        Statement statement;
        ResultSet resultSet;
        List<E> result = new LinkedList<>();
        /*try {
            statement = getConnection().createStatement();

            resultSet = statement.executeQuery(getSqlSelect() + (wheres==null?"":wheres.toString()));
            while(resultSet.next())
                result.add(initObject(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        return result;
    }

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public SOURCE getConnectionSource() {
        return connectionSource;
    }

    public StrictBaseMapper<E, DTO> getMapper() {
        return mapper;
    }

    public List<DTO> getObjects() {
        return objects;
    }

    public StrictDataState getState() {
        return state;
    }
    //</editor-fold>
}
