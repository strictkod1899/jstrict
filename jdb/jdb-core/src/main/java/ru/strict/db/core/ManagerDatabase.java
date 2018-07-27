package ru.strict.db.core;

import ru.strict.db.core.connections.CreateConnectionFactory;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.migration.MigrationDatabase;
import ru.strict.db.core.migration.components.MigrationTable;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.utils.UtilReflection;

import java.util.LinkedHashMap;
import java.util.Map;
import java.sql.Connection;

/**
 * Управление соединением с базой данных
 * <p><b>Пример использования:</b></p>
 * <code><pre style="background-color: white; font-family: consolas">
 *     ConnectionInfo connectionInfo = ... ;
 *     ManagerDatabase<ConnectionInfo> managerDatabase = new ManagerDatabase<>(connectionInfo);
 *
 *     // Добавление используемых репозиториев
 *     managerDatabase.createAndAddRepository("roleuser", RepositoryRoleuser.class, GenerateIdType.NONE);
 *     managerDatabase.createAndAddRepository("user", RepositoryUser.class, new MapperDtoUser<>(), GenerateIdType.NONE);
 *     ...
 *
 *     // Получение репозитория и выполнение операции
 *     managerDatabase.getRepository(RepositoryRoleuser.class).create(new DtoRoleuser(1, "SUPERUSER", "This is superuser"));
 *     managerDatabase.getRepository(RepositoryUser.class).create(new DtoUser(1, "user", "password", "token"));
 *     ...
 * </pre></code>
 */
public class ManagerDatabase<SOURCE> {

    /**
     * Источник подключения к базе данных (DataSource, StringConnectionInfo).
     * <i><b>Примечание: </b>используется для получения объекта Connection</i>
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
        if(connectionSource == null){
            throw new NullPointerException("connectionSource is NULL");
        }

        this.connectionSource = connectionSource;
        repositories = new LinkedHashMap<>();
        migration = new MigrationDatabase(new CreateConnectionFactory().instance(getConnectionSource()));
    }
    //</editor-fold>

    /**
     * Создать новый репозиторий (он не будет добавлен к списку репозиториев ManagerDatabase)
     * @param repositoryClass   Класс создаваемого репозитория
     * @param parameters        Параметры (кроме ConnectionSource), которые необходимы для создания репозитория. Параметры указываются в порядке следования
     * @param <REPOSITORY>      Тип создаваемого репозитория (необязательно)
     * @return
     */
    public <REPOSITORY extends IRepository> IRepository createRepository(Class<REPOSITORY> repositoryClass,
                                                                         Object...parameters){
        if(repositoryClass == null){
            throw new NullPointerException("repositoryClass is NULL");
        }

        Object[] userParameters = new Object[parameters.length + 1];
        userParameters[0] = getCreateConnection();
        for(int i=0; i<parameters.length; i++){
            userParameters[i+1] = parameters[i];
        }

        return UtilReflection.createInstance(repositoryClass, userParameters);
    }
    /**
     * Создать новый репозиторий и добавить к списку репозиториев ManagerDatabase
     * @param key               Ключ, по которому можно будет получить доступ к репозиторию
     * @param repositoryClass   Класс создаваемого репозитория
     * @param parameters        Параметры (кроме ConnectionSource), которые необходимы для создания репозитория. Параметры указываются в порядке следования
     * @param <REPOSITORY>      Тип создаваемого репозитория (необязательно)
     * @return
     */
    public <REPOSITORY extends IRepository> IRepository createAndAddRepository(
            String key,
            Class<REPOSITORY> repositoryClass,
            Object...parameters) {
        IRepository repository = createRepository(repositoryClass, parameters);
        addRepository(key, repository);
        return repository;
    }

    /**
     * Выполнить миграцию базы данных
     */
    public void migration(){
        migration.migration();
    }

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    /**
     * Получить объект для миграции базы данных
     * @return
     */
    public MigrationDatabase getMigration() {
        return migration;
    }

    /**
     * Добавить таблицу для миграции
     * @param table
     */
    public void addMigrationTable(MigrationTable table){
        migration.addTable(table);
    }

    /**
     * Получить список репозиториев, хранимых в ManagerDatabase
     * @return
     */
    public Map<String, IRepository> getRepositories() {
        return repositories;
    }

    /**
     * Добавить новый репозиторий
     * @param key Ключ доступа к репозиторию
     * @param repository Добавляемый репозиторий
     */
    public IRepository addRepository(String key, IRepository repository) {
        if(key == null){
            throw new NullPointerException("key is NULL");
        } else if(repository == null){
            throw new NullPointerException("repository is NULL");
        }

        if(repositories != null) {
            repositories.put(key, repository);
        }
        return repository;
    }

    /**
     * Получить репозитории по классу (вернет все хранящиеся репозитории с данным классом)
     * @param repositoryClass   Класс, требуемого репозитория
     * @param <REPSITORY>       Тип требуемого репозитория (необязательно)
     * @return
     */
    public <REPSITORY extends IRepository> IRepository[] getRepositories(Class<REPSITORY> repositoryClass) {
        IRepository[] result = repositories.entrySet()
                .stream()
                .filter(entry -> UtilReflection.isInstanceOf(repositoryClass, entry.getValue().getClass()))
                .map(entry -> entry.getValue())
                .toArray(IRepository[]::new);
        return result;
    }

    /**
     * Получить репозитории по классу (вернет один репозиторий с данным классом)
     * @param repositoryClass   Класс, требуемого репозитория
     * @param <REPSITORY>       Тип требуемого репозитория (необязательно)
     * @return
     */
    public <REPSITORY extends IRepository> IRepository getRepository(Class<REPSITORY> repositoryClass) {
        IRepository result = repositories.entrySet()
                .stream()
                .map(entry -> entry.getValue())
                .filter(repository -> UtilReflection.isInstanceOf(repositoryClass, repository.getClass()))
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

    /**
     * Получить источник соединения
     * @return
     */
    public SOURCE getConnectionSource() {
        return connectionSource;
    }

    /**
     * Получить конструктор соединения по хранящемуся источнику
     * @return
     */
    public ICreateConnection<Connection> getCreateConnection(){
        return new CreateConnectionFactory().instance(getConnectionSource());
    }

    /**
     * Получить соединение с базой данных
     * @return
     */
    public Connection getConnection(){
        return getCreateConnection().createConnection();
    }
    //</editor-fold>
}
