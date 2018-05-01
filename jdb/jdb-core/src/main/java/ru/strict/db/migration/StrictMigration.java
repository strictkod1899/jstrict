package ru.strict.db.migration;

import ru.strict.db.connections.StrictCreateConnectionAny;
import ru.strict.db.migration.components.StrictMigrationTable;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Базовый класс миграции
 * @param <SOURCE> Источник для получения соединения с базой данных,
 *                например, StrictCreateConnectionByDataSource, StrictCreateConnectionByConnectionInfo и др
 * @param <TABLE> Тип таблиц для миграции (например, PostgreSQLMigrationTable, SQLiteMigrationTable и др.)
 */
public class StrictMigration
        <SOURCE extends StrictCreateConnectionAny, TABLE extends StrictMigrationTable>
        implements StrictMigrationAny{

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
        for(TABLE table : getTables()){
            String sql = table.getSql();

            try (Connection connection = getConnectionSource().createConnection()){
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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
}
