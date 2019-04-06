package ru.strict.db.core.common;

import java.sql.SQLType;

/**
 * Параметр для подставновки в sql-запрос типа PreparedStatement
 * @param <VALUE> Тип значения параметра
 */
public class SqlParameter<VALUE> {

    private int index;
    private String columnName;
    private VALUE value;
    /**
     * java.sql.JDBCType
     */
    private SQLType sqlType;

    public SqlParameter(int index, String columnName, VALUE value) {
        this.index = index;
        this.columnName = columnName;
        this.value = value;
    }

    public SqlParameter(int index, String columnName, VALUE value, SQLType sqlType) {
        this.index = index;
        this.columnName = columnName;
        this.value = value;
        this.sqlType = sqlType;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getColumnName() {
        return columnName;
    }

    public VALUE getValue() {
        return value;
    }

    public SQLType getSqlType() {
        return sqlType;
    }

    public void setSqlType(SQLType sqlType) {
        this.sqlType = sqlType;
    }
}
