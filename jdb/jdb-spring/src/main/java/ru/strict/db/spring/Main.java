package ru.strict.db.spring;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.strict.db.core.ManagerDatabase;
import ru.strict.db.core.common.ConnectionByDbType;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.*;
import ru.strict.db.core.mappers.dto.MapperDtoUser;
import ru.strict.db.core.repositories.IRepositoryExtension;
import ru.strict.db.spring.repositories.*;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(ConnectionByDbType.SQLITE.getDriver());
        dataSource.setUrl(ConnectionByDbType.SQLITE.getUrl() + "A:\\Users\\strictkod1899\\testdb.sqlite");
        dataSource.setUsername("");
        dataSource.setPassword("");
        ManagerDatabase<DataSource> managerDatabase = new ManagerDatabase<>(dataSource);
        addRepositories(managerDatabase);
        int i = 0;
        //create(managerDatabase);
        //createAutoId(managerDatabase);
        //update(managerDatabase);
        read(managerDatabase);
        readFill(managerDatabase);
        readAll(managerDatabase);
        //delete(managerDatabase);
        i = 0;
    }

    private static void addRepositories(ManagerDatabase managerDatabase){
        managerDatabase.createAndAddRepository("roleuser", RepositoryRoleuser.class, GenerateIdType.NONE);
        managerDatabase.createAndAddRepository("user", RepositoryUserFillToken.class, GenerateIdType.NONE);
        managerDatabase.createAndAddRepository("user_on_role", RepositoryUserOnRole.class, GenerateIdType.NONE);
        managerDatabase.createAndAddRepository("profile", RepositoryProfileInfo.class, GenerateIdType.NONE);
        managerDatabase.createAndAddRepository("country", RepositoryCountry.class, GenerateIdType.NONE);
        managerDatabase.createAndAddRepository("city", RepositoryCity.class, GenerateIdType.NONE);
        managerDatabase.createAndAddRepository("token", RepositoryJWTUserToken.class, GenerateIdType.NONE);
    }

    private static void addRepositoriesAutoId(ManagerDatabase managerDatabase){
        managerDatabase.createAndAddRepository("roleuser", RepositoryRoleuser.class, GenerateIdType.NUMBER);
        managerDatabase.createAndAddRepository("user", RepositoryUser.class, new MapperDtoUser<>(), GenerateIdType.NUMBER);
        managerDatabase.createAndAddRepository("user_on_role", RepositoryUserOnRole.class, GenerateIdType.NUMBER);
        managerDatabase.createAndAddRepository("profile", RepositoryProfileInfo.class, GenerateIdType.NUMBER);
        managerDatabase.createAndAddRepository("city", RepositoryCity.class, GenerateIdType.NUMBER);
        managerDatabase.createAndAddRepository("country", RepositoryCountry.class, GenerateIdType.NUMBER);
    }

    private static void create(ManagerDatabase managerDatabase){
        managerDatabase.getRepository(RepositoryCountry.class)
                .create(new DtoCountry(1, "Russia"));
        managerDatabase.getRepository(RepositoryCity.class)
                .create(new DtoCity(1, "Novokuznetsk", 1));
        managerDatabase.getRepository(RepositoryUserFillToken.class)
                .create(new DtoUserToken(1, "user", "password"));
        managerDatabase.getRepository(RepositoryProfileInfo.class)
                .create(new DtoProfileInfo(1, "Konstantin", "Kastirin", "Igorevich", 1, new Date(), "123", 1));
        managerDatabase.getRepository(RepositoryRoleuser.class)
                .create(new DtoRoleuser(1, "SUPERUSER", "This is superuser"));
        managerDatabase.getRepository(RepositoryUserOnRole.class)
                .create(new DtoUserOnRole(1, 1, 1));
        managerDatabase.getRepository(RepositoryJWTUserToken.class)
                .create(new DtoJWTUserToken(1, "access", "refresh", new Date(), new Date(), new Date(), 1, 1));
    }

    private static void createAutoId(ManagerDatabase managerDatabase){
        managerDatabase.getRepository(RepositoryCountry.class)
                .create(new DtoCountry("Russia"));
        managerDatabase.getRepository(RepositoryCity.class)
                .create(new DtoCity("Novokuznetsk", 1));
        managerDatabase.getRepository(RepositoryUser.class)
                .create(new DtoUser("user", "password", "token"));
        managerDatabase.getRepository(RepositoryProfileInfo.class)
                .create(new DtoProfileInfo("Konstantin", "Kastirin", "Igorevich", 1, new Date(), "123", 1));
        managerDatabase.getRepository(RepositoryRoleuser.class)
                .create(new DtoRoleuser("SUPERUSER", "This is superuser"));
        managerDatabase.getRepository(RepositoryUserOnRole.class)
                .create(new DtoUserOnRole(1, 1));
    }

    private static void update(ManagerDatabase managerDatabase){
        managerDatabase.getRepository(RepositoryCountry.class)
                .update(new DtoCountry(1, "RussiaUpdate"));
        managerDatabase.getRepository(RepositoryCity.class)
                .update(new DtoCity(1, "NovokuznetskUpdate", 1));
        managerDatabase.getRepository(RepositoryUser.class)
                .update(new DtoUser(1, "userUpdate", "password"));
        managerDatabase.getRepository(RepositoryProfileInfo.class)
                .update(new DtoProfileInfo(1, "KonstantinUpdate", "KastirinUpdate", "Igorevich", 1, new Date(), "123", 1));
        managerDatabase.getRepository(RepositoryRoleuser.class)
                .update(new DtoRoleuser(1, "SUPERUSERUpdate", "This is superuser"));
        managerDatabase.getRepository(RepositoryUserOnRole.class)
                .update(new DtoUserOnRole(1, 1, 1));
    }

    private static void read(ManagerDatabase managerDatabase){
        DtoBase dtoCountry = managerDatabase.getRepository(RepositoryCountry.class).read(1);
        DtoBase dtoCity = managerDatabase.getRepository(RepositoryCity.class).read(1);
        DtoBase dtoUser = managerDatabase.getRepository(RepositoryUserFillToken.class).read(1);
        DtoBase dtoProfileInfo = managerDatabase.getRepository(RepositoryProfileInfo.class).read(1);
        DtoBase dtoRoleuser = managerDatabase.getRepository(RepositoryRoleuser.class).read(1);
        DtoBase dtoUserOnRole = managerDatabase.getRepository(RepositoryUserOnRole.class).read(1);
        DtoBase dtoToken = managerDatabase.getRepository(RepositoryJWTUserToken.class).read(1);
        int i = 0;
    }

    private static void readFill(ManagerDatabase managerDatabase){
        DtoBase dtoCountry = ((IRepositoryExtension)managerDatabase.getRepository(RepositoryCountry.class))
                .readFill(1);
        DtoBase dtoCity = ((IRepositoryExtension)managerDatabase.getRepository(RepositoryCity.class))
                .readFill(1);
        DtoBase dtoUser = ((IRepositoryExtension)managerDatabase.getRepository(RepositoryUserFillToken.class))
                .readFill(1);
        DtoBase dtoProfileInfo = ((IRepositoryExtension)managerDatabase.getRepository(RepositoryProfileInfo.class))
                .readFill(1);
        DtoBase dtoRoleuser = ((IRepositoryExtension)managerDatabase.getRepository(RepositoryRoleuser.class))
                .readFill(1);
        DtoBase dtoUserOnRole = ((IRepositoryExtension)managerDatabase.getRepository(RepositoryUserOnRole.class))
                .readFill(1);
        DtoBase dtoToken = ((IRepositoryExtension)managerDatabase.getRepository(RepositoryJWTUserToken.class))
                .readFill(1);
        int i = 0;
    }

    private static void readAll(ManagerDatabase managerDatabase){
        List dtoCountry = managerDatabase.getRepository(RepositoryCountry.class).readAll(null);
        List dtoCity = managerDatabase.getRepository(RepositoryCity.class).readAll(null);
        List dtoUser = managerDatabase.getRepository(RepositoryUserFillToken.class).readAll(null);
        List dtoProfileInfo = managerDatabase.getRepository(RepositoryProfileInfo.class).readAll(null);
        List dtoRoleuser = managerDatabase.getRepository(RepositoryRoleuser.class).readAll(null);
        List dtoUserOnRole = managerDatabase.getRepository(RepositoryUserOnRole.class).readAll(null);
        List dtoToken = managerDatabase.getRepository(RepositoryJWTUserToken.class).readAll(null);
        int i = 0;
    }

    private static void delete(ManagerDatabase managerDatabase){
        managerDatabase.getRepository(RepositoryCountry.class).delete(1);
        managerDatabase.getRepository(RepositoryCity.class).delete(1);
        managerDatabase.getRepository(RepositoryUser.class).delete(1);
        managerDatabase.getRepository(RepositoryProfileInfo.class).delete(1);
        managerDatabase.getRepository(RepositoryRoleuser.class).delete(1);
        managerDatabase.getRepository(RepositoryUserOnRole.class).delete(1);
    }
}
