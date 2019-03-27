package ru.strict.db.core.requests;

public class DbWhereLike extends DbWhereItem {

    public DbWhereLike(String tableName, String columnName, String columnValue) {
        super(tableName, columnName, columnValue, "LIKE");
    }
}
