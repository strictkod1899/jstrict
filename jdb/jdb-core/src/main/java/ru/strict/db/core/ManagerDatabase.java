package ru.strict.db.core;

import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.migration.MigrationDatabase;
import ru.strict.db.core.migration.components.MigrationTable;
import ru.strict.db.core.repositories.IRepository;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Управление соединением с базой данных
 */
public class ManagerDatabase<SOURCE extends ICreateConnection> {

    /**
     * Источник подключения к базе данных (используется для получения объекта Connection),
     * например, DataSource, StringConnectionInfo и др.
     */
    private SOURCE connectionSource;

    /**
     * Используемые репозитории
     */
    private Map<String, IRepository> repositories;

    /**
     * Поддережка миграции базы данных
     */
    private MigrationDatabase migration;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public ManagerDatabase(SOURCE connectionSource) {
        this.connectionSource = connectionSource;
        repositories = new LinkedHashMap<>();
        migration = new MigrationDatabase(connectionSource);
    }
    //</editor-fold>

    public void migration(){
        migration.migration();
    }

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    /**
     * Добавить таблицу для миграции
     * @param table
     */
    public void addMigrationTable(MigrationTable table){
        migration.addTable(table);
    }

    public MigrationDatabase getMigration() {
        return migration;
    }

    /**
     * Добавить новый репозиторий
     * @param key Ключ доступа к репозиторию
     * @param repository Добавляемый репозиторий
     */
    public IRepository addRepository(String key, IRepository repository) {
        repositories.put(key, repository);
        return repository;
    }

    public Map<String, IRepository> getRepositories() {
        return repositories;
    }

    /**
     * Получить репоизторий по ключу
     * @param key Ключ доступа к репозиторию
     * @return
     */
    public IRepository getRepository(String key) {
        return repositories.get(key);
    }

    public SOURCE getConnectionSource() {
        return connectionSource;
    }
    //</editor-fold>
}
