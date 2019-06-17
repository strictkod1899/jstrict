package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;

import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.requests.DbTable;
import ru.strict.models.City;
import ru.strict.models.ProfileDetails;
import ru.strict.models.User;
import ru.strict.db.core.entities.EntityProfileInfo;
import ru.strict.db.core.entities.EntityUser;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.repositories.interfaces.IRepositoryProfile;
import ru.strict.db.jdbc.mappers.sql.MapperSqlProfileInfo;
import ru.strict.utils.UtilClass;

import java.sql.Connection;

/**
 * Репозиторий таблицы "profileinfo".
 * Определяет столбцы: "name", "surname", "middlename", "userx_id", "datebirth", "phone", "city_id"
 * @param <ID> Тип идентификатора
 */
public class RepositoryProfileInfo<ID>
        extends RepositoryJdbcBase<ID, EntityProfileInfo<ID>, ProfileDetails<ID>>
        implements IRepositoryProfile<ID, ProfileDetails<ID>> {

    private static final String[] COLUMNS_NAME = new String[] {"name", "surname", "middlename", "userx_id", "datebirth",
            "phone", "city_id"};

    public RepositoryProfileInfo(ICreateConnection<Connection> connectionSource, GenerateIdType generateIdType) {
        super(new DbTable("profile", "pr"),
                COLUMNS_NAME,
                connectionSource,
                new MapperDtoFactory<ID>().instance(UtilClass.castClass(EntityProfileInfo.class), UtilClass.castClass(ProfileDetails.class)),
                new MapperSqlProfileInfo<ID>(COLUMNS_NAME),
                generateIdType);
    }

    @Override
    protected SqlParameters getParameters(EntityProfileInfo<ID> entity){
        SqlParameters parameters = new SqlParameters();
        parameters.add(0, COLUMNS_NAME[0], entity.getName());
        parameters.add(1, COLUMNS_NAME[1], entity.getSurname());
        parameters.add(2, COLUMNS_NAME[2], entity.getMiddlename());
        parameters.add(3, COLUMNS_NAME[3], entity.getUserId());
        parameters.add(4, COLUMNS_NAME[4], entity.getDateBirth());
        parameters.add(5, COLUMNS_NAME[5], entity.getPhone());
        parameters.add(6, COLUMNS_NAME[6], entity.getCityId());
        return parameters;
    }

    @Override
    protected ProfileDetails<ID> fill(ProfileDetails<ID> dto){
        IRepository<ID, User<ID>> repositoryUser = new RepositoryUser<>(getConnectionSource(),
                new MapperDtoFactory().instance(UtilClass.castClass(EntityUser.class), UtilClass.castClass(User.class)),
                GenerateIdType.NONE);
        dto.setUser(repositoryUser.read(dto.getUserId()));

        IRepository<ID, City<ID>> repositoryCity = new RepositoryCity(getConnectionSource(), GenerateIdType.NONE);
        dto.setCity(repositoryCity.read(dto.getCityId()));

        return dto;
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
