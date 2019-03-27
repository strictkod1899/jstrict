package ru.strict.db.core.requests;

public class DbWhereEquals extends DbWhereItem {

    public DbWhereEquals(String tableName, String columnName, Object columnValue) {
        super(tableName, columnName, columnValue, "=");
    }
}
