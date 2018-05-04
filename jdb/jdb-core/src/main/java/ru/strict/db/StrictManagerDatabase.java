package ru.strict.db;

import ru.strict.db.connections.StrictCreateConnectionAny;
import ru.strict.db.migration.StrictMigration;
import ru.strict.db.migration.components.StrictMigrationTable;
import ru.strict.db.repositories.StrictRepositoryAny;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Управление соединением с базой данных
 */
public class StrictManagerDatabase<SOURCE extends StrictCreateConnectionAny> {

    /**
     * Источник подключения к базе данных (используется для получения объекта Connection),
     * например, DataSource, StringConnectionInfo и др.
     */
    private SOURCE connectionSource;

    /**
     * Используемые репозитории
     */
    private Map<String, StrictRepositoryAny> repositories;

    /**
     * Поддережка миграции базы данных
     */
    private StrictMigration migration;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictManagerDatabase(SOURCE connectionSource) {
        this.connectionSource = connectionSource;
        repositories = new LinkedHashMap<>();
        migration = new StrictMigration(connectionSource);
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
    public void addMigrationTable(StrictMigrationTable table){
        migration.addTable(table);
    }

    public StrictMigration getMigration() {
        return migration;
    }

    /**
     * Добавить новый репозиторий
     * @param key Ключ доступа к репозиторию
     * @param repository Добавляемый репозиторий
     */
    public StrictRepositoryAny addRepository(String key, StrictRepositoryAny repository) {
        repositories.put(key, repository);
        return repository;
    }

    public Map<String, StrictRepositoryAny> getRepositories() {
        return repositories;
    }

    /**
     * Получить репоизторий по ключу
     * @param key Ключ доступа к репозиторию
     * @return
     */
    public StrictRepositoryAny getRepository(String key) {
        return repositories.get(key);
    }

    public SOURCE getConnectionSource() {
        return connectionSource;
    }
    //</editor-fold>
}
