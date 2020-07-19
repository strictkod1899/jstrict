package ru.strict.db.jdbc.mappers.sql;

import ru.strict.patterns.mapper.BaseMapper;

import java.sql.ResultSet;
import java.sql.SQLType;

/**
 * Базовая реализация маппера из выборки sql-запроса (ResultSet) в модель
 *
 * @param <T> Модель таблицы базы данных
 */
public abstract class BaseSqlMapper<T> extends BaseMapper<ResultSet, T> {

    protected final String[] columns;
    protected final SQLType idType;
    protected final String idColumnName;

    public BaseSqlMapper() {
        this(null, null, null);
    }

    public BaseSqlMapper(String[] columns) {
        this(columns, null, null);
    }

    public BaseSqlMapper(String[] columns, SQLType idType, String idColumnName) {
        this.columns = columns;
        this.idType = idType;
        this.idColumnName = idColumnName;
    }
}