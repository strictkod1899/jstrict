package ru.strict.db.migration.components;

import ru.strict.utils.StrictUtilLogger;
import ru.strict.utils.components.StrictLogger;

import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Таблица для миграции в базу данных
 * @param <COLUMN> Тип столбцов таблицы для миграции (например, StrictMigrationColumn, PostgreSQLMigrationColumn и др.)
 * @param <FK> Тип внешних ключей таблицы для миграции (например, StrictMigrationForeignKey, PostgreSQLMigrationForeignKey и др.)
 */
public class StrictMigrationTable
        <COLUMN extends StrictMigrationColumn, FK extends StrictMigrationForeignKey>
        implements StrictMigrationComponent {

    protected final StrictLogger LOGGER = StrictUtilLogger.createLogger(StrictMigrationTable.class);

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
    private StrictMigrationPrimaryKey primaryKey;
    /**
     * Внешние ключи таблицы
     */
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
        LOGGER.info("Trying a table sql query created");
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
            StrictMigrationTable object = (StrictMigrationTable) obj;
            return name.equals(object.getName()) && primaryKey.equals(object.getPrimaryKey())
                    && (columns.size() == object.getColumns().size() && columns.containsAll(object.getColumns()))
                    && (foreignKeys.size() == object.getForeignKeys().size() && foreignKeys.containsAll(object.getForeignKeys()));
        }else
            return false;
    }
    //</editor-fold>
}
