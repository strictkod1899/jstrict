package ru.strict.db.core.requests;

public class DbWhereFunction extends DbWhereItem {

    public DbWhereFunction(String function, String operation, Object columnValue) {
        super(new DbSelectItem(null, function), columnValue, operation);
    }
}
