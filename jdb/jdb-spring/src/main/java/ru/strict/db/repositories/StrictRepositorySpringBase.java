package ru.strict.db.repositories;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.strict.db.connections.StrictCreateConnectionByDataSource;
import ru.strict.db.dto.StrictDtoBase;
import ru.strict.db.entities.StrictEntityBase;
import ru.strict.db.mappers.StrictMapperBase;

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

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictRepositorySpringBase(SOURCE connectionSource, StrictMapperBase mapper, String sqlSelect) {
        super(connectionSource, mapper);
        this.sqlSelect = sqlSelect;
        this.springJdbc = new NamedParameterJdbcTemplate(getConnectionSource().getConnectionSource());
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getSqlSelect() {
        return sqlSelect;
    }
    //</editor-fold>
}
