package ru.strict.db.jdbc;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Assert;
import org.junit.Test;
import ru.strict.db.core.connections.CreateConnectionByDataSource;
import ru.strict.db.jdbc.runners.TestRunner;

import java.sql.Connection;

public class TestConnection {

    @Test
    public void testConnectionInfo(){
        Connection connection = TestRunner.CREATE_DB_INTEGER_CONNECTION.createConnection();
        Assert.assertNotNull(connection);
    }

    @Test
    public void testDataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite:" + TestRunner.DB_INTEGER_FILE.getAbsolutePath());
        dataSource.setUsername("");
        dataSource.setPassword("");
        CreateConnectionByDataSource createConnectionByDataSource = new CreateConnectionByDataSource(dataSource);
        Connection connection = createConnectionByDataSource.createConnection();
        Assert.assertNotNull(connection);
    }
}
