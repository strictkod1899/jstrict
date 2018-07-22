package ru.strict.db.hibernate.common;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.strict.db.hibernate.connection.HibernateConnectionInfo;

public class SessionFactorySingleton {

    private static SessionFactory instance;
    private static Configuration configuration;

    private SessionFactorySingleton(){
        configuration = new Configuration();
    }

    public static void initialize(HibernateConnectionInfo connectionInfo){
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

        for(String packagePath : connectionInfo.getPackages()){
            configuration.addPackage(packagePath);
        }

        for(Class entityClass : connectionInfo.getEntityClasses()){
            configuration.addAnnotatedClass(entityClass);
        }
    }

    /**
     * Перед первым использованием необходимо вызвать метод initialize()
     * @return
     */
    public static SessionFactory instance(){
        if(instance == null){
            if(configuration != null){
                configuration.configure();
                instance = configuration.buildSessionFactory();
            }
        }

        return instance;
    }
}
