package ru.strict.db.core.requests;

public class DbWhereIsNotNull extends DbWhereItem {

    public DbWhereIsNotNull(DbTable table, String column) {
        super(new DbSelectItem(table, column), null, "IS NOT NULL");
    }

    public DbWhereIsNotNull(DbSelectItem whereItem) {
        super(whereItem, null, "IS NOT NULL");
    }
}
