package ru.strict.db.mybatis.tests;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import static ru.strict.db.mybatis.tests.runners.TestRunner.CREATE_DB_INTEGER_CONNECTION;

public class TestConnection {

    @Test
    public void test(){
        SqlSession connection = CREATE_DB_INTEGER_CONNECTION.createConnection();
        Assert.assertNotNull(connection);
    }
}
