package ru.strict.db.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.strict.db.core.common.ConnectionDbInfo;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.connection.MybatisConnectionInfo;
import ru.strict.db.mybatis.mappers.sql.MapperSqlCity;
import ru.strict.utils.UtilResources;

import java.io.File;

@RunWith(JUnit4.class)
public class TestConnection {

    private static final String DB_FILE_PATH = "testdb_integer.sqlite";

    private static File dbFile;

    @BeforeClass
    public static void prepare(){
        dbFile = UtilResources.getResourceAsFile(DB_FILE_PATH);
        if(dbFile == null){
            throw new NullPointerException("ot found db-file");
        }
    }

    @AfterClass
    public static void post(){
        if(dbFile != null && dbFile.exists()){
            dbFile.delete();
        }
    }

    @Test
    public void test(){
        MybatisConnectionInfo connectionInfo = new MybatisConnectionInfo(
                ConnectionDbInfo.SQLITE.getDriver(),
                ConnectionDbInfo.SQLITE.getUrl() + dbFile.getAbsolutePath(),
                "",
                "");
        CreateConnectionByMybatis createConnection = new CreateConnectionByMybatis(connectionInfo);
        SqlSession connection = createConnection.createConnection();
        Assert.assertNotNull(connection);
    }
}
