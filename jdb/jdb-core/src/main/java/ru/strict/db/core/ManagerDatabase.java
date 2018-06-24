package ru.strict.db.core;

import ru.strict.db.core.connections.CreateConnectionFactory;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.migration.MigrationDatabase;
import ru.strict.db.core.migration.components.MigrationTable;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.utils.UtilReflection;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.sql.Connection;

/**
 * Управление соединением с базой данных
 */
public class ManagerDatabase<SOURCE> {

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
        migration = new MigrationDatabase(new CreateConnectionFactory().instance(getConnectionSource()));
    }
    //</editor-fold>

    public <REPOSITORY extends IRepository> IRepository createRepository(Class<REPOSITORY> repositoryClass,
                                                                         Object...parameters){
        Object[] userParameters = new Object[parameters.length + 1];
        userParameters[0] = getCreateConnection();
        for(int i=0; i<parameters.length; i++){
            userParameters[i+1] = parameters[i];
        }

        return UtilReflection.createInstance(repositoryClass, userParameters);
    }

    /**
     * Добавить новый репозиторий
     * @param key Ключ доступа к репозиторию
     */
    public <REPOSITORY extends IRepository> IRepository createAndAddRepository(
            String key,
            Class<REPOSITORY> repositoryClass,
            Object...parameters) {
        IRepository repository = createRepository(repositoryClass, parameters);
        repositories.put(key, repository);
        return repository;
    }

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
     * Получить репоизтории по классу
     * @return
     */
    public <REPSITORY extends IRepository> IRepository[] getRepositories(Class<REPSITORY> repositoryClass) {
        IRepository[] result = repositories.entrySet()
                .stream()
                .filter(entry -> UtilReflection.IsInstanceOf(repositoryClass, entry.getValue().getClass()))
                .map(entry -> entry.getValue())
                .toArray(IRepository[]::new);
        return result;
    }

    /**
     * Получить репоизтории по классу
     * @return
     */
    public <REPSITORY extends IRepository> IRepository getRepository(Class<REPSITORY> repositoryClass) {
        IRepository result = repositories.entrySet()
                .stream()
                .filter(entry -> UtilReflection.IsInstanceOf(repositoryClass, entry.getValue().getClass()))
                .map(entry -> entry.getValue())
                .findFirst()
                .orElse(null);
        return result;
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

    public ICreateConnection getCreateConnection(){
        return new CreateConnectionFactory().instance(getConnectionSource());
    }

    public Connection getConnection(){
        return new CreateConnectionFactory()
                .instance(getConnectionSource())
                .createConnection();
    }
    //</editor-fold>
}
