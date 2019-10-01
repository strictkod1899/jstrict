package ru.strict.db.spring;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

import static ru.strict.db.spring.runners.TestRunner.CREATE_DB_INTEGER_CONNECTION;

public class TestConnection {

    @Test
    public void test(){
        Connection connection = CREATE_DB_INTEGER_CONNECTION.createConnection();
        Assert.assertNotNull(connection);
    }
}
