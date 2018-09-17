package ru.strict.db.core.mappers.sql;

import ru.strict.db.core.entities.EntityBase;
import ru.strict.db.core.mappers.MapperBase;

import java.sql.ResultSet;

/**
 * Базовая реализация маппера из выборки sql-запроса (ResultSet) в entity
 * @param <T> Entity-класс
 */
public abstract class MapperSqlBase<ID, T extends EntityBase<ID>>
        extends MapperBase<ResultSet, T> {

    @Override
    public ResultSet map(T t) {
        return null;
    }

    @Override
    protected ResultSet implementMap(T target) {
        return null;
    }
}
