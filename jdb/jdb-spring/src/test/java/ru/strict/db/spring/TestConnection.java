package ru.strict.db.spring;

import org.junit.Assert;
import org.junit.Test;
import ru.strict.db.spring.runners.TestRunner;

import java.sql.Connection;

public class TestConnection {

    @Test
    public void test(){
        Connection connection = TestRunner.CREATE_DB_INTEGER_CONNECTION.createConnection();
        Assert.assertNotNull(connection);
    }
}
