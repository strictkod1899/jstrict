package ru.strict.db.core.mappers.sql;

import ru.strict.db.core.entities.StrictEntityBase;
import ru.strict.db.core.mappers.StrictMapperBase;

import java.sql.ResultSet;

/**
 * Базовая реализация маппера из выборки sql-запроса в entity
 * @param <T> Entity-класс
 */
public abstract class StrictMapperSqlBase<T extends StrictEntityBase>
        extends StrictMapperBase<ResultSet, T> {

    @Override
    public ResultSet map(T t) {
        return null;
    }

    @Override
    protected ResultSet implementMap(T target) {
        return null;
    }
}
