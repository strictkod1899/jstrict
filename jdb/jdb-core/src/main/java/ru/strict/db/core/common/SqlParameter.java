package ru.strict.db.core.common;

import java.sql.SQLType;
import java.util.Objects;

/**
 * Параметр для подставновки в sql-запрос типа PreparedStatement
 *
 * @param <VALUE> Тип значения параметра
 */
public class SqlParameter<VALUE> {
    private int index;
    private String name;
    private VALUE value;
    /**
     * java.sql.JDBCType
     */
    private SQLType sqlType;

    public SqlParameter(String name, VALUE value) {
        this.name = name;
        this.value = value;
    }

    public SqlParameter(String name, VALUE value, SQLType sqlType) {
        this.name = name;
        this.value = value;
        this.sqlType = sqlType;
    }

    public SqlParameter(int index, String name, VALUE value) {
        this.index = index;
        this.name = name;
        this.value = value;
    }

    public SqlParameter(int index, String name, VALUE value, SQLType sqlType) {
        this.index = index;
        this.name = name;
        this.value = value;
        this.sqlType = sqlType;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString() {
        return String.format("sql-parameter [%s - %s] - %s", index, name, String.valueOf(value));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SqlParameter<?> that = (SqlParameter<?>) o;
        return index == that.index &&
                Objects.equals(name, that.name) &&
                Objects.equals(value, that.value) &&
                Objects.equals(sqlType, that.sqlType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, name, value, sqlType);
    }

    //</editor-fold>
}
