package ru.strict.db.hibernate;

import ru.strict.db.core.common.ConnectionByDbType;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.dto.*;
import ru.strict.db.hibernate.entities.*;
import ru.strict.db.hibernate.mappers.dto.MapperDtoFactory;
import ru.strict.db.hibernate.mappers.dto.MapperDtoUser;
import ru.strict.db.hibernate.common.HibernateDialects;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.connection.HibernateConnectionInfo;
import ru.strict.db.hibernate.mappers.dto.MapperDtoUserToken;
import ru.strict.db.hibernate.repositories.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Main {

    public static void main(String[] args){
        HibernateConnectionInfo hibernateConnectionInfo =
                new HibernateConnectionInfo(HibernateDialects.SQLITE.getDialect(),
                        ConnectionByDbType.SQLITE.getDriver(),
                        ConnectionByDbType.SQLITE.getUrl() + "C:\\Users\\strictkod1899\\testdb.sqlite",
                        "",
                        "");
        hibernateConnectionInfo.addPackage("ru.strict.db.hibernate.entities");
        hibernateConnectionInfo.addEntityClass(EntityCountry.class);
        hibernateConnectionInfo.addEntityClass(EntityCity.class);
        hibernateConnectionInfo.addEntityClass(EntityUser.class);
        hibernateConnectionInfo.addEntityClass(EntityProfile.class);
        hibernateConnectionInfo.addEntityClass(EntityProfileInfo.class);
        hibernateConnectionInfo.addEntityClass(EntityRoleuser.class);
        hibernateConnectionInfo.addEntityClass(EntityUserOnRole.class);
        hibernateConnectionInfo.addEntityClass(EntityJWTUserToken.class);

        RepositoryCountry repositoryCountry =
                new RepositoryCountry(new CreateConnectionHibernate(hibernateConnectionInfo), GenerateIdType.NONE);
        RepositoryCity repositoryCity =
                new RepositoryCity(new CreateConnectionHibernate(hibernateConnectionInfo), GenerateIdType.NONE);
        RepositoryUser<DtoUserToken> repositoryUser =
                new RepositoryUser(new CreateConnectionHibernate(hibernateConnectionInfo),
                        new MapperDtoFactory().instance(MapperDtoType.USER_TOKEN), GenerateIdType.NONE);
        RepositoryProfileInfo repositoryProfile =
                new RepositoryProfileInfo(new CreateConnectionHibernate(hibernateConnectionInfo), GenerateIdType.NONE);
        RepositoryRoleuser repositoryRoleUser =
                new RepositoryRoleuser(new CreateConnectionHibernate(hibernateConnectionInfo), GenerateIdType.NONE);
        RepositoryUserOnRole repositoryUserOnRole =
                new RepositoryUserOnRole(new CreateConnectionHibernate(hibernateConnectionInfo), GenerateIdType.NONE);
        RepositoryJWTUserToken repositoryToken =
                new RepositoryJWTUserToken(new CreateConnectionHibernate(hibernateConnectionInfo), GenerateIdType.NONE);

        /*repositoryCountry.create(new DtoCountry("Russia"));
        repositoryCity.create(new DtoCity("Novokuznetsk", repositoryCountry.readAll(null).get(0).getId()));
        repositoryUser.create(new DtoUserToken("user", "password"));
        UUID userId = (UUID) repositoryUser.readAll(null).get(0).getId();
        UUID cityId = (UUID) repositoryCity.readAll(null).get(0).getId();
        repositoryProfile.create(new DtoProfileInfo("Konstantin", "Kastirin", "Igorevich",
                userId, new Date(), "123",
                cityId));
        repositoryRoleUser.create(new DtoRoleuser("SUPERUSER", "This is superuser"));
        repositoryUserOnRole.create(new DtoUserOnRole(repositoryUser.readAll(null).get(0).getId(),
                repositoryRoleUser.readAll(null).get(0).getId()));
        repositoryToken.create(new DtoJWTUserToken("access", "refresh", new Date(), new Date(), new Date(),
                repositoryUser.readAll(null).get(0).getId(),
                repositoryRoleUser.readAll(null).get(0).getId()));*/


        DtoBase dtoCountry = repositoryCountry.read((UUID)repositoryCountry.readAll(null).get(0).getId());
        DtoBase dtoCity = repositoryCity.read((UUID)repositoryCity.readAll(null).get(0).getId());
        DtoBase dtoUserOnRole = repositoryUserOnRole.read((UUID)repositoryUserOnRole.readAll(null).get(0).getId());
        DtoBase dtoRoleuser = repositoryRoleUser.read((UUID)repositoryRoleUser.readAll(null).get(0).getId());
        DtoBase dtoUser = repositoryUser.read((UUID)repositoryUser.readAll(null).get(0).getId());
        DtoBase dtoProfile = repositoryProfile.read((UUID)repositoryProfile.readAll(null).get(0).getId());
        DtoBase dtoToken = repositoryToken.read((UUID)repositoryToken.readAll(null).get(0).getId());

        int i = 0;
        /*List dtoCountries = repositoryCountry.readAll(null);
        List dtoCities = repositoryCity.readAll(null);
        List dtoUsers = repositoryUser.readAll(null);
        List dtoProfiles = repositoryProfile.readAll(null);
        List dtoRoleusers = repositoryRoleUser.readAll(null);
        List dtoUSerOnRoles = repositoryUserOnRole.readAll(null);
        List dtoTokens = repositoryToken.readAll(null);*/

        /*
        repositoryCountry.update(new DtoCountry(repositoryCountry.readAll(null).get(0).getId(), "RussiaUpdate"));
        repositoryCity.update(new DtoCity(repositoryCity.readAll(null).get(0).getId(), "NovokuznetskUpdate", repositoryCountry.readAll(null).get(0).getId()));
        repositoryUser.update(new DtoUser(repositoryUser.readAll(null).get(0).getId(), "userUPDATE", "password"));
        repositoryProfile.update(new DtoProfileInfo(repositoryProfile.readAll(null).get(0).getId(), "KonstantinUPDATE", "Kastirin", "Igorevich",
                repositoryUser.readAll(null).get(0).getId(), new Date(), "123",
                repositoryCity.readAll(null).get(0).getId()));
        repositoryRoleUser.update(new DtoRoleuser(repositoryRoleUser.readAll(null).get(0).getId(), "SUPERUSER_UPDATE", "This is superuser"));
        repositoryUserOnRole.update(new DtoUserOnRole(repositoryUserOnRole.readAll(null).get(0).getId(), repositoryUser.readAll(null).get(0).getId(),
                repositoryRoleUser.readAll(null).get(0).getId()));
        repositoryToken.update(new DtoJWTUserToken(repositoryToken.readAll(null).get(0).getId(), "accessUpdate", "refresh", new Date(), new Date(), new Date(),
                repositoryUser.readAll(null).get(0).getId(),
                repositoryRoleUser.readAll(null).get(0).getId()));


        repositoryCity.delete((UUID)repositoryCity.readAll(null).get(0).getId());
        repositoryCountry.delete((UUID)repositoryCountry.readAll(null).get(0).getId());
        repositoryUser.delete((UUID)repositoryUser.readAll(null).get(0).getId());
        repositoryProfile.delete((UUID)repositoryProfile.readAll(null).get(0).getId());
        repositoryRoleUser.delete((UUID)repositoryRoleUser.readAll(null).get(0).getId());
        repositoryUserOnRole.delete((UUID)repositoryUserOnRole.readAll(null).get(0).getId());
        repositoryToken.delete((UUID)repositoryToken.readAll(null).get(0).getId());
        */

        i = 0;
    }
}
