package ru.strict.db.migration.components.postgresql;

import ru.strict.db.migration.components.StrictMigrationForeignBehavior;
import ru.strict.db.migration.components.StrictMigrationForeignKey;

public class PostgreSQLMigrationForeignKey extends StrictMigrationForeignKey {

    private String schema;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public PostgreSQLMigrationForeignKey(String name, String column, String tableRef, String columnRef
            , StrictMigrationForeignBehavior updateBehavior, StrictMigrationForeignBehavior deleteBehavior, String schema) {
        super(name, column, tableRef, columnRef, updateBehavior, deleteBehavior);
        this.schema = schema;
    }
    //</editor-fold>

    @Override
    public String getSql(){
        return String.format("CONSTRAINT %s FOREIGN KEY (%s) REFERENCES %s.%s (%s) ON UPDATE %s ON DELETE %s"
                , getName(), getColumn(), schema, getTableRef(), getColumnRef()
                ,getUpdateBehavior().getSql(), getDeleteBehavior().getSql());
    }

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getSchema() {
        return schema;
    }
    //</editor-fold>
}
