package ru.strict.db.migration.components;

import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class StrictMigrationTable
        <COLUMN extends StrictMigrationColumn, FK extends StrictMigrationForeignKey>
        implements StrictMigrationComponent {

    private String name;
    private Collection<COLUMN> columns;
    private StrictMigrationPrimaryKey primaryKey;
    private Collection<FK> foreignKeys;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictMigrationTable(String name) {
        this.name = name;
        columns = new LinkedList<>();
        primaryKey = null;
        foreignKeys = new LinkedList<>();
    }
    //</editor-fold>

    @Override
    public String getSql(){
        StringBuilder sql = new StringBuilder();
        sql.append(String.format("CREATE TABLE %s ( ", getName()));

        // Добавление столбцов таблицы
        for(StrictMigrationColumn column : getColumns()) {
            sql.append(column.getSql());
            sql.append(", ");
        }

        // Добавление первичного ключа
        sql.append(getPrimaryKey().getSql());

        // Добавление внешних ключей
        for(StrictMigrationForeignKey foreignKey : getForeignKeys()){
            sql.append(", ");
            sql.append(foreignKey.getSql());
        }

        sql.append(" );");
        return sql.toString();
    }

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getName() {
        return name;
    }

    public Collection<COLUMN> getColumns() {
        return columns;
    }

    public void addColumn(COLUMN column){
        columns.add(column);
    }

    public StrictMigrationPrimaryKey getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(StrictMigrationPrimaryKey primaryKey) {
        this.primaryKey = primaryKey;
    }

    public Collection<FK> getForeignKeys() {
        return foreignKeys;
    }

    public void addForeignKey(FK foreignKey){
        foreignKeys.add(foreignKey);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        String columnsName = String.join("; ", columns.stream().map((column) -> column.getName()).collect(Collectors.toList()));
        return String.format("Table: %s. Columns: %s", name, columnsName);
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof StrictMigrationTable) {
            StrictMigrationTable table = (StrictMigrationTable) obj;
            return name.equals(table.getName()) && primaryKey.equals(table.getPrimaryKey())
                    && (columns.size() == table.getColumns().size() && columns.containsAll(table.getColumns()))
                    && (foreignKeys.size() == table.getForeignKeys().size() && foreignKeys.containsAll(table.getForeignKeys()));
        }else
            return false;
    }
    //</editor-fold>
}
