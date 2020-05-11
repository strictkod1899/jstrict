package ru.strict.db.jdbc.mappers.sql;

import ru.strict.db.core.common.SqlType;
import ru.strict.models.IModel;
import ru.strict.patterns.BaseMapper;
import ru.strict.patterns.MapperBase;
import ru.strict.validate.Validator;

import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.util.UUID;

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
        this(null, null, null)
    }

    public BaseSqlMapper(String[] columns) {
        this(columns, null, null);
    }

    public BaseSqlMapper(String[] columns, String idColumnName) {
        this(columns, null, idColumnName);
    }

    public BaseSqlMapper(String[] columns, SQLType idType, String idColumnName) {
        this.columns = columns;
        this.idType = idType;
        this.idColumnName = idColumnName;
    }

    @Override
    public T map(ResultSet resultSet) {
        try {
            return resultSet == null ? null : implementMap(resultSet);
        } catch (Exception ex) {
            throw new SqlMappingException(ex);
        }
    }

    protected <T> T mapValueBySqlType(SQLType sqlType, ResultSet resultSet, String columnName) throws SQLException {
        if (sqlType == null) {
            throw new IllegalArgumentException("sqlType is NULL");
        }

        ID value;
        if (sqlType.equals(SqlType.UUID)) {
            value = (ID) UUID.fromString(resultSet.getString(columnName));
        } else if (sqlType.equals(SqlType.TEXT)) {
            value = (ID) resultSet.getString(columnName);
        } else if (sqlType.equals(JDBCType.BIGINT)) {
            value = (ID) (Object) resultSet.getLong(columnName);
        } else if (sqlType.equals(JDBCType.INTEGER)) {
            value = (ID) (Object) resultSet.getInt(columnName);
        } else {
            value = (ID) resultSet.getObject(columnName);
        }
        return value;
    }
}
