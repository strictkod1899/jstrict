package ru.strict.db.core.requests;

import ru.strict.validate.ValidateBaseValue;

import java.util.Objects;

public class DbSelectItem implements IDbRequest {

    private DbTable table;
    private String columnName;

    private void initialize(String columnName){
        if(ValidateBaseValue.isEmptyOrNull(columnName)){
            throw new IllegalArgumentException("columnName is NULL");
        }
        this.columnName = columnName;
    }

    public DbSelectItem(String columnName) {
        initialize(columnName);
    }

    public DbSelectItem(DbTable table, String columnName) {
        this(columnName);
        this.table = table;
    }

    public String getColumnName() {
        return columnName;
    }

    public DbTable getTable() {
        return table;
    }

    @Override
    public String getSql() {
        if(table == null){
            return columnName;
        } else {
            return String.format("%s.%s", table.getRequiredName(), columnName);
        }
    }

    @Override
    public String toString(){
        return getSql();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DbSelectItem that = (DbSelectItem) o;
        return Objects.equals(table, that.table) &&
                Objects.equals(columnName, that.columnName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(table, columnName);
    }
}
