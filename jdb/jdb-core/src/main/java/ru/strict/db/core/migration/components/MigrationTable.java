package ru.strict.db.core.migration.components;

import ru.strict.utils.UtilLogger;
import ru.strict.components.WrapperLogger;
import ru.strict.utils.UtilHashCode;

import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Таблица для миграции в базу данных
 * @param <COLUMN> Тип столбцов таблицы для миграции (например, MigrationColumn, PostgreSQLMigrationColumn и др.)
 * @param <FK> Тип внешних ключей таблицы для миграции (например, MigrationForeignKey, PostgreSQLMigrationForeignKey и др.)
 */
public class MigrationTable
        <COLUMN extends MigrationColumn, FK extends MigrationForeignKey>
        implements MigrationComponent {

    protected final WrapperLogger LOGGER = UtilLogger.createLogger(MigrationTable.class);

    /**
     * Наименование таблицы
     */
    private String name;
    /**
     * Столбц таблицы
     */
    private Collection<COLUMN> columns;
    /**
     * Первичный ключ таблицы
     */
    private MigrationPrimaryKey primaryKey;
    /**
     * Внешние ключи таблицы
     */
    private Collection<FK> foreignKeys;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public MigrationTable(String name) {
        if(name == null){
            throw new NullPointerException("name is NULL");
        }

        this.name = name;
        columns = new LinkedList<>();
        primaryKey = null;
        foreignKeys = new LinkedList<>();
    }
    //</editor-fold>

    @Override
    public String getSql(){
        LOGGER.info("Trying a table sql query created");
        StringBuilder sql = new StringBuilder();
        sql.append(String.format("CREATE TABLE %s ( ", getName()));

        // Добавление столбцов таблицы
        for(MigrationColumn column : getColumns()) {
            sql.append(column.getSql());
            sql.append(", ");
        }

        // Добавление первичного ключа
        sql.append(getPrimaryKey().getSql());

        // Добавление внешних ключей
        for(MigrationForeignKey foreignKey : getForeignKeys()){
            sql.append(", ");
            sql.append(foreignKey.getSql());
        }

        sql.append(" );");
        LOGGER.info("Table sql query is created");
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
        if(column == null){
            throw new NullPointerException("column is NULL");
        }

        if(columns != null) {
            columns.add(column);
        }
    }

    public MigrationPrimaryKey getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(MigrationPrimaryKey primaryKey) {
        if(primaryKey == null){
            throw new NullPointerException("primaryKey is NULL");
        }

        this.primaryKey = primaryKey;
    }

    public Collection<FK> getForeignKeys() {
        return foreignKeys;
    }

    public void addForeignKey(FK foreignKey){
        if(foreignKey == null){
            throw new NullPointerException("foreignKey is NULL");
        }

        if(foreignKeys != null) {
            foreignKeys.add(foreignKey);
        }
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
        if(obj!=null && obj instanceof MigrationTable) {
            MigrationTable object = (MigrationTable) obj;
            return name.equals(object.getName()) && primaryKey.equals(object.getPrimaryKey())
                    && (columns.size() == object.getColumns().size() && columns.containsAll(object.getColumns()))
                    && (foreignKeys.size() == object.getForeignKeys().size() && foreignKeys.containsAll(object.getForeignKeys()));
        }else
            return false;
    }

    @Override
    public int hashCode(){
        return UtilHashCode.createHashCode(name, primaryKey, columns, foreignKeys);
    }
    //</editor-fold>
}
