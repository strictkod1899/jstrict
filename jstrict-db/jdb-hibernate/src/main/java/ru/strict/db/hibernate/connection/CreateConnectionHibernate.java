package ru.strict.db.hibernate.connection;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.strict.db.core.connections.CreateConnectionBase;

/**
 * Конструктор соединения с базой данных, на основе информации переданной в объекте класса HibernateConnectionInfo
 * <p><b>Пример использования:</b></p>
 * <code><pre style="background-color: white; font-family: consolas">
 *     ...
 *     ICreateConnection connectionCreator = new CreateConnectionHibernate(hibernateConnectionInfo);
 *     Session connection = connectionCreator.createConnection();
 * </pre></code>
 */
public class CreateConnectionHibernate extends CreateConnectionBase<HibernateConnectionInfo, Session> {

    private SessionFactory sessionFactory;

    public CreateConnectionHibernate(HibernateConnectionInfo connectionSource) {
        super(connectionSource);
        initializeSessionFactory();
    }

    @Override
    public Session createConnection() {
        Session result = null;

        try {
            result = sessionFactory.openSession();
        } catch (HibernateException ex) {
            throw new RuntimeException(ex);
        }

        return result;
    }

    private void initializeSessionFactory(){
        HibernateConnectionInfo connectionInfo = getConnectionSource();

        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.dialect", connectionInfo.getDialect());
        configuration.setProperty("hibernate.connection.driver_class", connectionInfo.getDriver());
        configuration.setProperty("hibernate.connection.url", connectionInfo.getUrl());
        configuration.setProperty("hibernate.connection.username", connectionInfo.getUsername());
        configuration.setProperty("hibernate.connection.password", connectionInfo.getPassword());

        configuration.setProperty("hibernate.connection.pool_size", String.valueOf(connectionInfo.getPoolSize()));
        configuration.setProperty("hibernate.connection.autocommit", String.valueOf(connectionInfo.isAutoCommit()));
        configuration.setProperty("hibernate.cache.provider_class", connectionInfo.getProviderClass());
        configuration.setProperty("hibernate.cache.use_second_level_cache", String.valueOf(connectionInfo.isUseSecondLevelCache()));
        configuration.setProperty("hibernate.cache.use_query_cache", String.valueOf(connectionInfo.isUseQueryCache()));
        configuration.setProperty("hibernate.show_sql",String.valueOf(connectionInfo.isShowSql()));
        configuration.setProperty("hibernate.current_session_context_class", connectionInfo.getCurrentSessionContextClass());

        for(String packagePath : connectionInfo.getPackages()){
            configuration.addPackage(packagePath);
        }

        for(Class entityClass : connectionInfo.getEntityClasses()){
            configuration.addAnnotatedClass(entityClass);
        }

        sessionFactory = configuration.buildSessionFactory();
    }
}
