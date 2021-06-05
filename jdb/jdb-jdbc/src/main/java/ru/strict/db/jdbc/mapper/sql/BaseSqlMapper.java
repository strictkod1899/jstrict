package ru.strict.db.jdbc.mapper.sql;

import ru.strict.db.jdbc.util.JdbcUtil;
import ru.strict.patterns.mapper.BaseMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
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

    protected <V> V mapByIdType(Object sourceValue) {
        return JdbcUtil.mapValue(sourceValue, idType);
    }

    protected <V> V getByIdType(ResultSet resultSet, String columnName) throws SQLException {
        return JdbcUtil.getValueBySqlType(resultSet, columnName, idType);
    }
}