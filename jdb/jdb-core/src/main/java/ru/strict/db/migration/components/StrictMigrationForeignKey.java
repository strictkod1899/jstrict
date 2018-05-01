package ru.strict.db.migration.components;

public class StrictMigrationForeignKey {

    private String name;
    private String column;
    private String tableRef;
    private String columnRef;
    private StrictMigrationForeignBehavior updateBehavior;
    private StrictMigrationForeignBehavior deleteBehavior;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictMigrationForeignKey(String name, String column, String tableRef, String columnRef
            , StrictMigrationForeignBehavior updateBehavior, StrictMigrationForeignBehavior deleteBehavior) {
        this.name = name;
        this.column = column;
        this.tableRef = tableRef;
        this.columnRef = columnRef;
        this.updateBehavior = updateBehavior;
        this.deleteBehavior = deleteBehavior;
    }
    //</editor-fold>

    public String getSql(){
        return String.format("CONSTRAINT %s FOREIGN KEY (%s) REFERENCES %s (%s) ON UPDATE %s ON DELETE %s"
                , name, column, tableRef, columnRef, updateBehavior.getSql(), deleteBehavior.getSql());
    }

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getName() {
        return name;
    }

    public String getColumn() {
        return column;
    }

    public String getTableRef() {
        return tableRef;
    }

    public String getColumnRef() {
        return columnRef;
    }

    public StrictMigrationForeignBehavior getUpdateBehavior() {
        return updateBehavior;
    }

    public StrictMigrationForeignBehavior getDeleteBehavior() {
        return deleteBehavior;
    }
    //</editor-fold>
}
