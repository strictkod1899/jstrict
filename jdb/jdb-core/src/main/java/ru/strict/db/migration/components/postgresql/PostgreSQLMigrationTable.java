package ru.strict.db.migration.components.postgresql;

import ru.strict.db.migration.components.StrictMigrationTable;

import java.util.stream.Collectors;

/**
 * Таблица для миграции в базу данных PostgreSQL
 */
public class PostgreSQLMigrationTable
        <COLUMN extends PostgreSQLMigrationColumn, FK extends PostgreSQLMigrationForeignKey>
        extends StrictMigrationTable<COLUMN, FK> {

    /**
     * Схема базы данных
     */
    private String schema;
    // TODO: добавить создание sequence для автоинкремента
    /**
     * Использование автоинкремента на стороне базы данных
     */
    private boolean isAutoincrement;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public PostgreSQLMigrationTable(String name, String schema) {
        super(name);
        isAutoincrement = false;
        this.schema = schema;
    }

    public PostgreSQLMigrationTable(String name, boolean isAutoincrement, String schema) {
        super(name);
        this.isAutoincrement = isAutoincrement;
        this.schema = schema;
    }
    //</editor-fold>

    @Override
    public String getSql(){
        StringBuilder sql = new StringBuilder();
        sql.append(String.format("CREATE TABLE %s.%s ( ", schema, getName()));

        // Добавление столбцов таблицы
        for(PostgreSQLMigrationColumn column : getColumns()) {
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
        String columnsName = String.join("; ", getColumns().stream().map((column) -> column.getName()).collect(Collectors.toList()));
        return String.format("Table: %s.%s. Columns: %s", schema, getName(), columnsName);
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof PostgreSQLMigrationTable) {
            PostgreSQLMigrationTable object = (PostgreSQLMigrationTable) obj;
            return super.equals(object) && schema.equals(object.getSchema()) && isAutoincrement == object.isAutoincrement();
        }else
            return false;
    }
    //</editor-fold>
}
