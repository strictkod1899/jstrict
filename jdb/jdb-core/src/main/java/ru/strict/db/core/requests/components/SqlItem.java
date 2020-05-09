package ru.strict.db.core.requests.components;

import ru.strict.db.core.requests.IRequest;
import ru.strict.validate.Validator;

import java.util.Objects;

public class SqlItem implements IRequest {
    private Table table;
    private String columnName;

    public SqlItem(String columnName) {
        this(null, columnName);
    }

    public SqlItem(Table table, String columnName) {
        Validator.isNull(columnName, "columnName").onThrow();

        this.columnName = columnName;
        this.table = table;
    }

    public String getColumnName() {
        return columnName;
    }

    public Table getTable() {
        return table;
    }

    @Override
    public String getSql() {
        if (table == null) {
            return columnName;
        } else {
            return String.format("%s.%s", table.getRequiredName(), columnName);
        }
    }

    @Override
    public String toString() {
        return getSql();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SqlItem that = (SqlItem) o;
        return Objects.equals(table, that.table) &&
                Objects.equals(columnName, that.columnName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(table, columnName);
    }
}
