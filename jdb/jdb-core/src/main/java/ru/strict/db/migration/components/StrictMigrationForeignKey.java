package ru.strict.db.migration.components;

public class StrictMigrationForeignKey implements StrictMigrationComponent{

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

    @Override
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

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return getSql();
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof StrictMigrationForeignKey) {
            StrictMigrationForeignKey object = (StrictMigrationForeignKey) obj;
            return name.equals(object.getName()) && column.equals(object.getColumn()) && tableRef.equals(object.getTableRef())
                    && columnRef.equals(object.getColumnRef()) && updateBehavior.equals(object.getUpdateBehavior())
                    && deleteBehavior.equals(object.getDeleteBehavior());
        }else
            return false;
    }
    //</editor-fold>
}
