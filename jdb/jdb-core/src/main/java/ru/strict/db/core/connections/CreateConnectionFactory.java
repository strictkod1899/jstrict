package ru.strict.db.core.connections;

import ru.strict.patterns.IFactory;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Фабрика для создания конструктора соединения с базой данных на основе переданного источника подключения (DataSource, ConnectionInfo)
 */
public class CreateConnectionFactory implements IFactory<ICreateConnection<Connection>, Object> {

    @Override
    public ICreateConnection<Connection> instance(Object parameter) {
        ICreateConnection<Connection> createConnection = null;
        if(parameter instanceof DataSource){
            createConnection = new CreateConnectionByDataSource((DataSource) parameter);
        }else if(parameter instanceof ConnectionInfo){
            createConnection = new CreateConnectionByConnectionInfo((ConnectionInfo) parameter);
        }
        return createConnection;
    }
}
