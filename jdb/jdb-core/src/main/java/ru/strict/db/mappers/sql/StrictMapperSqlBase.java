package ru.strict.db.mappers.sql;

import ru.strict.db.entities.StrictEntityBase;
import ru.strict.db.mappers.StrictMapperBase;

/**
 * Базовая реализация маппера из выборки sql-запроса в entity
 * @param <S> Выборка sql-запроса
 * @param <T> Entity-класс
 */
public abstract class StrictMapperSqlBase<S extends StrictResultSetMap, T extends StrictEntityBase>
        extends StrictMapperBase<S, T> {
}
