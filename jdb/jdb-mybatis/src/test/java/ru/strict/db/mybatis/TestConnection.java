package ru.strict.db.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import ru.strict.db.mybatis.runners.TestRunner;

public class TestConnection {

    @Test
    public void test(){
        SqlSession connection = TestRunner.createConnectionForDbInteger.createConnection();
        Assert.assertNotNull(connection);
    }
}
