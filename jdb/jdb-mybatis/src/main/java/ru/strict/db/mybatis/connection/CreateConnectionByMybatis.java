package ru.strict.db.mybatis.connection;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import ru.strict.components.Log4jWrapper;
import ru.strict.db.core.connections.CreateConnectionBase;
import ru.strict.utils.UtilLogger;

/**
 * Конструктор соединения с базой данных, на основе информации переданной в объекте класса HibernateConnectionInfo
 * <p><b>Пример использования:</b></p>
 * <code><pre style="background-color: white; font-family: consolas">
 *     ...
 *     ICreateConnection connectionCreater = new CreateConnectionByMybatis(mybatisConnectionInfo);
 *     SqlSession connection = connectionCreater.createConnection();
 * </pre></code>
 */
public class CreateConnectionByMybatis extends CreateConnectionBase<MybatisConnectionInfo, SqlSession> {

    protected final Log4jWrapper LOGGER = UtilLogger.createLogger(CreateConnectionByMybatis.class);

    public CreateConnectionByMybatis(MybatisConnectionInfo connectionSource) {
        super(connectionSource);
    }

    @Override
    public SqlSession createConnection() {
        SqlSession result = null;
        SqlSessionFactory sessionFactory = SessionFactorySingleton.instance();
        if(sessionFactory == null){
            SessionFactorySingleton.initialize(getConnectionSource());
            sessionFactory = SessionFactorySingleton.instance();
        }

        result = sessionFactory.openSession();

        return result;
    }
}

