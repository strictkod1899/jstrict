package ru.strict.db.core.requests;

public class DbWhereIsNull extends DbWhereItem {

    public DbWhereIsNull(String tableName, String columnName) {
        super(tableName, columnName, null, "IS NULL");
    }
}
