package ru.strict.db.mybatis.connection;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import ru.strict.db.core.connections.CreateConnectionBase;
import ru.strict.validate.ValidateBaseValue;

import java.io.IOException;
import java.io.InputStream;

/**
 * Конструктор соединения с базой данных, на основе информации переданной в объекте класса MybatisConnectionInfo
 * <p><b>Пример использования:</b></p>
 * <code><pre style="background-color: white; font-family: consolas">
 *     ...
 *     ICreateConnection connectionCreator = new CreateConnectionByMybatis(mybatisConnectionInfo);
 *     SqlSession connection = connectionCreator.createConnection();
 * </pre></code>
 */
public class CreateConnectionByMybatis extends CreateConnectionBase<MybatisConnectionInfo, SqlSession> {

    private SqlSessionFactory sessionFactory;

    public CreateConnectionByMybatis(MybatisConnectionInfo connectionSource) {
        super(connectionSource);
        initializeSessionFactory();
    }

    @Override
    public SqlSession createConnection() {
        return sessionFactory.openSession();
    }

    private void initializeSessionFactory(){
        sessionFactory = null;

        if(!ValidateBaseValue.isEmptyOrNull(getConnectionSource().getConfigFilePath())){
            String configFilePath = getConnectionSource().getConfigFilePath();

            InputStream configStream = null;
            try {
                configStream = Resources.getResourceAsStream(configFilePath);
                sessionFactory = new SqlSessionFactoryBuilder().build(configStream);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }else {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName(getConnectionSource().getDriver());
            dataSource.setUrl(getConnectionSource().getUrl());
            dataSource.setUsername(getConnectionSource().getUsername());
            dataSource.setPassword(getConnectionSource().getPassword());

            TransactionFactory transactionFactory = new JdbcTransactionFactory();
            Environment environment = new Environment(getConnectionSource().getEnvironment(), transactionFactory, dataSource);
            Configuration configuration = new Configuration(environment);
            getConnectionSource().getMappers().forEach(mapperClass -> configuration.addMapper(mapperClass));

            sessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        }
    }
}

