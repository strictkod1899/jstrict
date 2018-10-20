package ru.strict.db.migration.components.postgresql;

import ru.strict.db.migration.components.MigrationColumn;
import ru.strict.db.migration.components.MigrationTable;


import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Таблица для миграции в базу данных PostgreSQL
 */
public class PostgreSQLMigrationTable
        <COLUMN extends MigrationColumn, FK extends PostgreSQLMigrationForeignKey>
        extends MigrationTable<COLUMN, FK> {

    /**
     * Схема базы данных
     */
    private String schema;
    // TODO: добавить создание sequence для автоинкремента
    /**
     * Использование автоинкремента на стороне базы данных
     */
    private boolean isAutoincrement;

    public void initialize(boolean isAutoincrement, String schema){
        if(schema == null){
            throw new NullPointerException("schema is NULL");
        }

        this.isAutoincrement = isAutoincrement;
        this.schema = schema;
    }

    //<editor-fold defaultState="collapsed" desc="constructors">
    public PostgreSQLMigrationTable(String name, String schema) {
        super(name);
        initialize(false, schema);
    }

    public PostgreSQLMigrationTable(String name, boolean isAutoincrement, String schema) {
        super(name);
        initialize(isAutoincrement, schema);
    }
    //</editor-fold>

    @Override
    public String getSql(){
        StringBuilder sql = new StringBuilder();
        sql.append(String.format("CREATE TABLE %s.%s ( ", schema, getName()));

        // Добавление столбцов таблицы
        for(MigrationColumn column : getColumns()) {
            sql.append(column.getSql());
            sql.append(", ");
        }

        // Добавление первичного ключа
        sql.append(getPrimaryKey().getSql());

        // Добавление внешних ключей
        for(PostgreSQLMigrationForeignKey foreignKey : getForeignKeys()){
            sql.append(", ");
            sql.append(foreignKey.getSql());
        }

        sql.append(" );");
        return sql.toString();
    }

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getSchema() {
        return schema;
    }

    public boolean isAutoincrement() {
        return isAutoincrement;
    }

    public void setAutoincrement(boolean autoincrement) {
        isAutoincrement = autoincrement;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return getSql();
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof PostgreSQLMigrationTable) {
            PostgreSQLMigrationTable object = (PostgreSQLMigrationTable) obj;
            return super.equals(object) && Objects.equals(schema, object.getSchema())
                    && Objects.equals(isAutoincrement, object.isAutoincrement());
        }else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(getName(), getPrimaryKey(), getColumns(), getForeignKeys(), schema, isAutoincrement);
    }
    //</editor-fold>
}