package ru.strict.db.migration.components;

public enum StrictMigrationForeignBehavior implements StrictMigrationComponent{
    NO_ACTION("NO ACTION"),
    RESTRICT("RESTRICT"),
    SET_NULL("SET NULL"),
    SET_DEFAULT("SET DEFAULT"),
    CASCADE("CASCADE");

    private String sql;

    StrictMigrationForeignBehavior(String sql) {
        this.sql = sql;
    }

    @Override
    public String getSql() {
        return sql;
    }

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("Foreign behaviour: %s", sql);
    }
    //</editor-fold>
}
