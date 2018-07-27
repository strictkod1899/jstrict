package ru.strict.db.core.migration;

import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.migration.components.MigrationTable;
import ru.strict.utils.UtilLogger;
import ru.strict.components.WrapperLogger;
import ru.strict.utils.UtilHashCode;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Миграция базы данных
 * @param <SOURCE> Источник для получения соединения с базой данных (CreateConnectionByDataSource, CreateConnectionByConnectionInfo)
 * @param <TABLE> Тип таблиц для миграции (например, PostgreSQLMigrationTable, SQLiteMigrationTable и др.)
 */
public class MigrationDatabase
        <SOURCE extends ICreateConnection<Connection>, TABLE extends MigrationTable>
        implements IMigration {

    protected final WrapperLogger LOGGER = UtilLogger.createLogger(MigrationDatabase.class);

    /**
     * Источник подключения к базе данных (используется для получения объекта Connection),
     * является реализацией интерфейса ICreateConnection (CreateConnectionByDataSource, CreateConnectionByConnectionInfo)
     */
    private SOURCE connectionSource;

    /**
     * Таблицы, которые необходимо создать
     */
    private Collection<TABLE> tables;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public MigrationDatabase(SOURCE connectionSource) {
        if(connectionSource == null){
            throw new NullPointerException("connectionSource is NULL");
        }
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
        if(table == null){
            throw new NullPointerException("table is NULL");
        }

        if(tables != null) {
            tables.add(table);
        }
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        String tablesName = String.join("; ", tables.stream().map((table) -> table.getName()).collect(Collectors.toList()));
        return String.format("MigrationDatabase tables: %s", tablesName);
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof MigrationDatabase) {
            MigrationDatabase object = (MigrationDatabase) obj;
            return connectionSource.equals(object.getConnectionSource())
                    && (tables.size() == object.getTables().size() && tables.containsAll(object.getTables()));
        }else
            return false;
    }

    @Override
    public int hashCode(){
        return UtilHashCode.createHashCode(connectionSource, tables);
    }
    //</editor-fold>
}
