package ru.strict.db.migration.components.postgresql;

import ru.strict.db.migration.components.StrictMigrationTable;

public class PostgreSQLMigrationTable
        <COLUMN extends PostgreSQLMigrationColumn, FK extends PostgreSQLMigrationForeignKey>
        extends StrictMigrationTable<COLUMN, FK> {

    private String schema;
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
}
