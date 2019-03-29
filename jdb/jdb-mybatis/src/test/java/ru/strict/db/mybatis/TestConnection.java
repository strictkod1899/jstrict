package ru.strict.db.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import ru.strict.db.jdbc.runners.TestRunner;

public class TestConnection {

    @Test
    public void test(){
        SqlSession connection = TestRunner.CREATE_DB_INTEGER_CONNECTION.createConnection();
        Assert.assertNotNull(connection);
    }
}
