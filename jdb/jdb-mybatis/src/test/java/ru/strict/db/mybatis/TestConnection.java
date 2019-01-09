package ru.strict.db.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;

@RunWith(JUnit4.class)
public class TestConnection {

    @Test
    public void test(){
        CreateConnectionByMybatis createConnection = new CreateConnectionByMybatis(TestRunner.connectionInfoForDbInteger);
        SqlSession connection = createConnection.createConnection();
        Assert.assertNotNull(connection);
    }
}
