package ru.strict.db.core;

import ru.strict.db.core.connections.StrictCreateConnectionAny;
import ru.strict.db.core.migration.StrictMigration;
import ru.strict.db.core.migration.components.StrictMigrationTable;
import ru.strict.db.core.repositories.IStrictRepository;

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
    private Map<String, IStrictRepository> repositories;

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
    public IStrictRepository addRepository(String key, IStrictRepository repository) {
        repositories.put(key, repository);
        return repository;
    }

    public Map<String, IStrictRepository> getRepositories() {
        return repositories;
    }

    /**
     * Получить репоизторий по ключу
     * @param key Ключ доступа к репозиторию
     * @return
     */
    public IStrictRepository getRepository(String key) {
        return repositories.get(key);
    }

    public SOURCE getConnectionSource() {
        return connectionSource;
    }
    //</editor-fold>
}
