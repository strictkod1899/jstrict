package ru.strict.db.migration.components;

public enum StrictMigrationForeignBehavior {
    NO_ACTION("NO ACTION"),
    RESTRICT("RESTRICT"),
    SET_NULL("SET NULL"),
    SET_DEFAULT("SET DEFAULT"),
    CASCADE("CASCADE");

    private String sql;

    StrictMigrationForeignBehavior(String sql) {
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }
}
