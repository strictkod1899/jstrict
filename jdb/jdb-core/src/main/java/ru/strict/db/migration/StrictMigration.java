package ru.strict.db.migration;

import ru.strict.db.connections.StrictCreateConnectionAny;
import ru.strict.db.migration.components.StrictMigrationTable;
import ru.strict.utils.StrictUtilLogger;
import ru.strict.utils.components.StrictLogger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Базовый класс миграции
 * @param <SOURCE> Источник для получения соединения с базой данных,
 *                например, StrictCreateConnectionByDataSource, StrictCreateConnectionByConnectionInfo и др
 * @param <TABLE> Тип таблиц для миграции (например, PostgreSQLMigrationTable, SQLiteMigrationTable и др.)
 */
public class StrictMigration
        <SOURCE extends StrictCreateConnectionAny, TABLE extends StrictMigrationTable>
        implements StrictMigrationAny{

    protected final StrictLogger LOGGER = StrictUtilLogger.createLogger(StrictMigration.class);

    /**
     * Источник подключения к базе данных (используется для получения объекта Connection),
     * является реализацией интерфейса StrictCreateConnectionAny,
     * например, StrictCreateConnectionByDataSource, StrictCreateConnectionByConnectionInfo и др.
     */
    private SOURCE connectionSource;

    /**
     * Таблицы, которые необходимо создать
     */
    private Collection<TABLE> tables;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictMigration(SOURCE connectionSource) {
        this.connectionSource = connectionSource;
        tables = new LinkedList<>();
    }
    //</editor-fold>

    @Override
    public void migration() {
        LOGGER.info("Database migration is started");
        for(TABLE table : getTables()){
            String sql = table.getSql();

            try (Connection connection = getConnectionSource().createConnection()){
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
            } catch (SQLException ex) {
                LOGGER.error(ex.getClass().toString(), ex.getMessage());
            }
        }
        LOGGER.info("Database migration is finished");
    }

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public SOURCE getConnectionSource() {
        return connectionSource;
    }

    public Collection<TABLE> getTables() {
        return tables;
    }

    public void addTable(TABLE table){
        tables.add(table);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        String tablesName = String.join("; ", tables.stream().map((table) -> table.getName()).collect(Collectors.toList()));
        return String.format("Migration tables: %s", tablesName);
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof StrictMigration) {
            StrictMigration migration = (StrictMigration) obj;
            return connectionSource.equals(migration.connectionSource)
                    && (tables.size() == migration.getTables().size() && tables.containsAll(migration.getTables()));
        }else
            return false;
    }
    //</editor-fold>
}
