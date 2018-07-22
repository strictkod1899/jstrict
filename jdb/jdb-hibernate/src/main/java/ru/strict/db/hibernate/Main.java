package ru.strict.db.hibernate;

import ru.strict.db.core.common.ConnectionByDbType;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoBase;
import ru.strict.db.core.dto.DtoCity;
import ru.strict.db.core.dto.DtoCountry;
import ru.strict.db.core.repositories.IRepositoryExtension;
import ru.strict.db.hibernate.common.HibernateDialects;
import ru.strict.db.hibernate.connection.CreateConnectionHibernate;
import ru.strict.db.hibernate.connection.HibernateConnectionInfo;
import ru.strict.db.hibernate.entities.EntityCity;
import ru.strict.db.hibernate.entities.EntityCountry;
import ru.strict.db.hibernate.repositories.*;

import java.util.List;

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

        IRepositoryExtension<Integer, DtoCountry> repositoryCountry =
                new RepositoryCountry(new CreateConnectionHibernate(hibernateConnectionInfo), GenerateIdType.NONE);
        IRepositoryExtension<Integer, DtoCity> repositoryCity =
                new RepositoryCity(new CreateConnectionHibernate(hibernateConnectionInfo), GenerateIdType.NONE);

        repositoryCountry.create(new DtoCountry(1, "Russia"));
        repositoryCity.create(new DtoCity(1, "Novokuznetsk", 1));

        DtoBase dtoCountry = repositoryCountry.read(1);
        DtoBase dtoCity = repositoryCity.read(1);

        dtoCountry = repositoryCountry.readFill(1);
        dtoCity = repositoryCity.readFill(1);

        List dtoCountries = repositoryCountry.readAll(null);
        List dtoCities = repositoryCity.readAll(null);

        repositoryCountry.update(new DtoCountry(1, "RussiaUpdate"));
        repositoryCity.update(new DtoCity(1, "NovokuznetskUpdate", 1));

        repositoryCity.delete(1);
        repositoryCountry.delete(1);
    }
}
