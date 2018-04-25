package ru.strict.db.mappers.sql;

import ru.strict.db.entities.StrictEntityBase;
import ru.strict.db.mappers.StrictMapperBase;

import java.sql.ResultSet;

/**
 * Базовая реализация маппера из выборки sql-запроса в entity
 * @param <T> Entity-класс
 */
public abstract class StrictMapperSqlBase<T extends StrictEntityBase>
        extends StrictMapperBase<ResultSet, T> {
}
