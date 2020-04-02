package ru.strict.db.core.requests;

public class DbWhereEquals extends DbWhereItem {

    public DbWhereEquals(DbTable table, String column, Object columnValue) {
        super(new DbSelectItem(table, column), columnValue, "=");
    }

    public DbWhereEquals(DbSelectItem whereItem, Object columnValue) {
        super(whereItem, columnValue, "=");
    }
}
