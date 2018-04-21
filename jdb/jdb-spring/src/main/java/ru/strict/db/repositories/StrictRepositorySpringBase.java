package ru.strict.db.repositories;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import ru.strict.db.connections.StrictCreateConnectionByDataSource;
import ru.strict.db.dto.StrictDtoBase;
import ru.strict.db.entities.StrictEntityBase;
import ru.strict.db.mappers.StrictMapperBase;
import ru.strict.db.requests.StrictDbRequests;

import java.util.LinkedList;
import java.util.List;

/**
 * Базовый класс репозитория с использованием Spring Jdbc
 */
public abstract class StrictRepositorySpringBase
        <ID, SOURCE extends StrictCreateConnectionByDataSource, E extends StrictEntityBase, DTO extends StrictDtoBase>
        extends StrictRepositoryBase<ID, SOURCE, E, DTO> {

    /**
     * Sql-запрос на выборку данных из таблицы
     */
    private String sqlSelect;

    /**
     * Экземпляр Spring Jdbc, с помощью которого, производится взаимодействие с базой данных
     */
    private NamedParameterJdbcTemplate springJdbc;

    /**
     * Объект для преобразования полученных данных из sql-запроса в объект сущности азы данных (entity)
     */
    private RowMapper<E> springMapper;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictRepositorySpringBase(SOURCE connectionSource, StrictMapperBase mapper, String sqlSelect,
                                      RowMapper<E> springMapper) {
        super(connectionSource, mapper);
        this.sqlSelect = sqlSelect;
        this.springJdbc = new NamedParameterJdbcTemplate(getConnectionSource().getConnectionSource());
        this.springMapper = springMapper;
    }
    //</editor-fold>

    @Override
    public DTO read(ID id) {
        String sql = String.format("%s WHERE id = :entityId", getSqlSelect());
        SqlParameterSource parameters = new MapSqlParameterSource("entityId", id);
        E entity = springJdbc.queryForObject(sql, parameters, springMapper);
        return getDtoMapper().map(entity);
    }

    @Override
    public List<DTO> readAll(StrictDbRequests requests) {
        String sql = getSqlSelect() + (requests==null?"":requests.getSql());
        List<DTO> result = new LinkedList<>();
        List<E> entities = springJdbc.query(sql, springMapper);
        for(E entity : entities)
            result.add(getDtoMapper().map(entity));
        return result;
    }

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getSqlSelect() {
        return sqlSelect;
    }
    //</editor-fold>
}
