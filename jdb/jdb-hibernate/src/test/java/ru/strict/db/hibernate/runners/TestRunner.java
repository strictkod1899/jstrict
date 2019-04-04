package ru.strict.db.hibernate.runners;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ru.strict.db.core.common.ConnectionDbInfo;
import ru.strict.db.core.repositories.IRepositoryExtension;
import ru.strict.db.hibernate.*;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.connection.HibernateConnectionInfo;
import ru.strict.db.hibernate.mock.entities.integer.*;
import ru.strict.utils.UtilResources;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestConnection.class,
        /*TestRepositoryCountry.class,
        TestRepositoryCity.class,
        TestRepositoryRoleuser.class,
        TestRepositoryUser.class,
        TestRepositoryUserOnRole.class,
        TestRepositoryProfile.class,
        TestRepositoryProfileInfo.class,
        TestRepositoryFileStorage.class,
        TestRepositoryJWTToken.class,
        TestRepositoryServiceOnRole.class*/
})
public class TestRunner {

    private static final String DB_INTEGER_FILE_PATH = "testdb_integer.sqlite";
    private static final String DB_UUID_FILE_PATH = "testdb_uuid.sqlite";

    public static File DB_INTEGER_FILE;
    public static File DB_UUID_FILE;
    public static CreateConnectionHibernate CREATE_DB_INTEGER_CONNECTION;
    public static CreateConnectionHibernate CREATE_DB_UUID_CONNECTION;
    public static List<IRepositoryExtension> repositories;

    @BeforeClass
    public static void prepare(){
        DB_INTEGER_FILE = UtilResources.getResource(DB_INTEGER_FILE_PATH);
        DB_UUID_FILE = UtilResources.getResource(DB_UUID_FILE_PATH);
        if(DB_INTEGER_FILE == null){
            throw new NullPointerException("not found integer-db file");
        }
        if(DB_UUID_FILE == null){
            throw new NullPointerException("not found uuid-db file");
        }

        HibernateConnectionInfo connectionInfoForDbInteger = new HibernateConnectionInfo(
                "org.hibernate.dialect.SQLiteDialect",
                ConnectionDbInfo.SQLITE.getDriver(),
                ConnectionDbInfo.SQLITE.getUrl() + TestRunner.DB_INTEGER_FILE.getAbsolutePath(),
                "",
                "");

        HibernateConnectionInfo connectionInfoForDbUuid = new HibernateConnectionInfo(
                "org.hibernate.dialect.SQLiteDialect",
                ConnectionDbInfo.SQLITE.getDriver(),
                ConnectionDbInfo.SQLITE.getUrl() + TestRunner.DB_UUID_FILE.getAbsolutePath(),
                "",
                "");

        /*connectionInfoForDbInteger.addEntityClass(EntityCountry.class);
        connectionInfoForDbInteger.addEntityClass(EntityCity.class);
        connectionInfoForDbInteger.addEntityClass(EntityFileStorage.class);
        connectionInfoForDbInteger.addEntityClass(EntityJWTToken.class);
        connectionInfoForDbInteger.addEntityClass(EntityProfile.class);
        connectionInfoForDbInteger.addEntityClass(EntityProfileInfo.class);
        connectionInfoForDbInteger.addEntityClass(EntityRoleuser.class);
        connectionInfoForDbInteger.addEntityClass(EntityServiceOnRole.class);
        connectionInfoForDbInteger.addEntityClass(EntityUser.class);
        connectionInfoForDbInteger.addEntityClass(EntityUserOnRole.class);
        connectionInfoForDbUuid.addEntityClass(EntityCity.class);
        connectionInfoForDbUuid.addEntityClass(EntityCountry.class);
        connectionInfoForDbUuid.addEntityClass(EntityFileStorage.class);
        connectionInfoForDbUuid.addEntityClass(EntityJWTToken.class);
        connectionInfoForDbUuid.addEntityClass(EntityProfile.class);
        connectionInfoForDbUuid.addEntityClass(EntityProfileInfo.class);
        connectionInfoForDbUuid.addEntityClass(EntityRoleuser.class);
        connectionInfoForDbUuid.addEntityClass(EntityServiceOnRole.class);
        connectionInfoForDbUuid.addEntityClass(EntityUser.class);
        connectionInfoForDbUuid.addEntityClass(EntityUserOnRole.class);*/

        CREATE_DB_INTEGER_CONNECTION = new CreateConnectionHibernate(connectionInfoForDbInteger);
        CREATE_DB_UUID_CONNECTION = new CreateConnectionHibernate(connectionInfoForDbUuid);

        repositories = new ArrayList<>();
    }

    @AfterClass
    public static void post(){
        postProcess();
    }

    public static void postProcess(){
        for(IRepositoryExtension repository : repositories){
            repository.executeSql("DELETE FROM " + repository.getTableName());
        }
        repositories.clear();
    }
}
