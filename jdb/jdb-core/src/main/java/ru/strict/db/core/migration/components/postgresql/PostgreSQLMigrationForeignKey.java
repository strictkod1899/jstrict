package ru.strict.db.core.migration.components.postgresql;

import ru.strict.db.core.migration.components.MigrationForeignBehavior;
import ru.strict.db.core.migration.components.MigrationForeignKey;
import ru.strict.utils.UtilHashCode;

/**
 * Внешний ключ таблицы для миграции в базу данных PostgreSQL
 */
public class PostgreSQLMigrationForeignKey extends MigrationForeignKey {

    /**
     * Схема базы данных
     */
    private String schema;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public PostgreSQLMigrationForeignKey(String name, String column, String tableRef, String columnRef
            , MigrationForeignBehavior updateBehavior, MigrationForeignBehavior deleteBehavior, String schema) {
        super(name, column, tableRef, columnRef, updateBehavior, deleteBehavior);
        
        if(schema == null){
            throw new NullPointerException("schema is NULL");
        }

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

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return getSql();
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof PostgreSQLMigrationForeignKey) {
            PostgreSQLMigrationForeignKey object = (PostgreSQLMigrationForeignKey) obj;
            return super.equals(object) && schema.equals(object.getSchema());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        int superHashCode = super.hashCode();
        return UtilHashCode.createSubHashCode(superHashCode, schema);
    }
    //</editor-fold>
}
