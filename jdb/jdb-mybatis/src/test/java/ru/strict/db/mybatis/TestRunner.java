package ru.strict.db.mybatis;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ru.strict.db.core.common.ConnectionDbInfo;
import ru.strict.db.core.repositories.IRepositoryExtension;
import ru.strict.db.mybatis.connection.MybatisConnectionInfo;
import ru.strict.db.mybatis.mappers.sql.MapperSqlCountry;
import ru.strict.utils.UtilResources;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestConnection.class,
        TestRepositoryCountry.class
})
public class TestRunner {

    private static final String DB_INTEGER_FILE_PATH = "testdb_integer.sqlite";
    private static final String DB_UUID_FILE_PATH = "testdb_uuid.sqlite";

    private static File dbIntegerFile;
    private static File dbUuidFile;
    public static MybatisConnectionInfo connectionInfoForDbInteger;
    public static MybatisConnectionInfo connectionInfoForDbUuid;
    public static List<IRepositoryExtension> repositories;

    @BeforeClass
    public static void prepare(){
        dbIntegerFile = UtilResources.getResource(DB_INTEGER_FILE_PATH);
        dbUuidFile = UtilResources.getResource(DB_UUID_FILE_PATH);
        if(dbIntegerFile == null){
            throw new NullPointerException("not found integer-db file");
        }
        if(dbUuidFile == null){
            throw new NullPointerException("not found uuid-db file");
        }

        connectionInfoForDbInteger = new MybatisConnectionInfo(
                ConnectionDbInfo.SQLITE.getDriver(),
                ConnectionDbInfo.SQLITE.getUrl() + TestRunner.dbIntegerFile.getAbsolutePath(),
                "",
                "");
        connectionInfoForDbUuid = new MybatisConnectionInfo(
                ConnectionDbInfo.SQLITE.getDriver(),
                ConnectionDbInfo.SQLITE.getUrl() + TestRunner.dbUuidFile.getAbsolutePath(),
                "",
                "");

        connectionInfoForDbInteger.addMapper(MapperSqlCountry.class);
        connectionInfoForDbUuid.addMapper(MapperSqlCountry.class);
        repositories = new ArrayList<>();
    }

    @AfterClass
    public static void post(){
        for(IRepositoryExtension repository : repositories){
            repository.executeSql("DELETE FROM " + repository.getTableName());
        }
    }
}
