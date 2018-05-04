package ru.strict.db.migration.components;

public class StrictMigrationPrimaryKey implements StrictMigrationComponent {

    private String name;
    private String column;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictMigrationPrimaryKey(String name, String column) {
        this.name = name;
        this.column = column;
    }
    //</editor-fold>

    @Override
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

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("Primary key: %s to column %s", name, column);
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof StrictMigrationPrimaryKey) {
            StrictMigrationPrimaryKey object = (StrictMigrationPrimaryKey) obj;
            return name.equals(object.getName()) && column.equals(object.getColumn());
        }else
            return false;
    }
    //</editor-fold>
}
