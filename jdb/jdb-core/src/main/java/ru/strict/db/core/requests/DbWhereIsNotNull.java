package ru.strict.db.core.requests;

public class DbWhereIsNotNull extends DbWhereItem {

    public DbWhereIsNotNull(String tableName, String columnName) {
        super(tableName, columnName, null, "IS NOT NULL");
    }
}
