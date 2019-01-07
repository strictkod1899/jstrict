package ru.strict.db.mybatis.connection;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import ru.strict.validates.ValidateBaseValue;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;

/**
 * Используется при создании SessionFactory в классе CreateConnectionByMybatis
 */
public class SessionFactorySingleton {

    private static SqlSessionFactory instance;
    private static DataSource dataSource;
    private static String configFilePath;

    private static MybatisConnectionInfo connectionInfo;

    public static void initialize(MybatisConnectionInfo connectionInfo){
        instance = null;
        if(!ValidateBaseValue.isEmptyOrNull(connectionInfo.getConfigFilePath())){
            configFilePath = connectionInfo.getConfigFilePath();
        }else {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName(connectionInfo.getDriver());
            dataSource.setUrl(connectionInfo.getUrl());
            dataSource.setUsername(connectionInfo.getUsername());
            dataSource.setPassword(connectionInfo.getPassword());

            SessionFactorySingleton.dataSource = dataSource;
            SessionFactorySingleton.connectionInfo = connectionInfo;
        }
    }

    /**
     * Перед первым использованием необходимо вызвать метод initialize()
     * @return
     */
    public static SqlSessionFactory instance(){
        if(instance == null){
            if(dataSource != null){
                TransactionFactory transactionFactory = new JdbcTransactionFactory();
                Environment environment = new Environment("development", transactionFactory, dataSource);
                Configuration configuration = new Configuration(environment);
                for(Class mapperClass : connectionInfo.getMappers()) {
                    configuration.addMapper(mapperClass);
                }

                instance = new SqlSessionFactoryBuilder().build(configuration);
            }else if(!ValidateBaseValue.isEmptyOrNull(configFilePath)){
                InputStream configStream = null;
                try {
                    configStream = Resources.getResourceAsStream(configFilePath);
                    instance = new SqlSessionFactoryBuilder().build(configStream);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        return instance;
    }
}
