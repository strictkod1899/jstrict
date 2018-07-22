package ru.strict.db.hibernate.connection;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.strict.components.WrapperLogger;
import ru.strict.db.core.connections.ConnectionInfo;
import ru.strict.db.core.connections.CreateConnectionBase;
import ru.strict.db.hibernate.common.SessionFactorySingleton;
import ru.strict.utils.UtilLogger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Конструктор соединения с базой данных, на основе информации переданной в объекте класса HibernateConnectionInfo
 * <p><b>Пример использования:</b></p>
 * <code><pre style="background-color: white; font-family: consolas">
 *     ...
 *     ICreateConnection connectionCreater = new CreateConnectionHibernate(hibernateConnectionInfo);
 *     Session connection = connectionCreater.createConnection();
 * </pre></code>
 */
public class CreateConnectionHibernate extends CreateConnectionBase<HibernateConnectionInfo, Session> {

    protected final WrapperLogger LOGGER = UtilLogger.createLogger(CreateConnectionHibernate.class);

    public CreateConnectionHibernate(HibernateConnectionInfo connectionSource) {
        super(connectionSource);
    }

    @Override
    public Session createConnection() {
        Session result = null;
        SessionFactory sessionFactory = SessionFactorySingleton.instance();
        if(sessionFactory == null){
            SessionFactorySingleton.initialize(getConnectionSource());
            sessionFactory = SessionFactorySingleton.instance();
        }

        try {
            result = sessionFactory.openSession();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return result;
    }
}