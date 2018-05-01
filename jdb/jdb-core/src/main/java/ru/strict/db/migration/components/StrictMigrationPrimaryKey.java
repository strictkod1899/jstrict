package ru.strict.db.migration.components;

public class StrictMigrationPrimaryKey {

    private String name;
    private String column;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictMigrationPrimaryKey(String name, String column) {
        this.name = name;
        this.column = column;
    }
    //</editor-fold>

    public String getSql(){
        return String.format("CONSTRAINT %s PRIMARY KEY (%s)", name, column);
    }

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getName() {
        return name;
    }

    public String getColumn() {
        return column;
    }
    //</editor-fold>
}
