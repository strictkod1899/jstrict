package ru.strict.db.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;

public class TestConnection {

    @Test
    public void test(){
        SqlSession connection = TestRunner.createConnectionForDbInteger.createConnection();
        Assert.assertNotNull(connection);
    }
}
