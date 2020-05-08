package ru.strict.db.spring.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.connections.CreateConnectionByDataSource;
import ru.strict.db.core.repositories.DefaultColumns;
import ru.strict.db.core.repositories.DefaultTable;
import ru.strict.db.core.repositories.interfaces.IRepositoryProfileDetails;
import ru.strict.db.core.requests.DbTable;
import ru.strict.models.City;
import ru.strict.models.DetailsProfile;
import ru.strict.models.DetailsUser;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.repositories.interfaces.IRepositoryProfile;
import ru.strict.db.spring.mappers.sql.MapperSpringProfileDetails;

import java.sql.SQLType;

/**
 * Репозиторий таблицы "profile".
 * @param <ID> Тип идентификатора
 */
public class RepositoryProfileDetails<ID>
        extends RepositorySpringBase<ID, DetailsProfile<ID>>
        implements IRepositoryProfileDetails<ID, DetailsProfile<ID>> {

    private static final String[] COLUMNS_NAME = DefaultColumns.PROFILE_DETAILS.columns();

    public RepositoryProfileDetails(CreateConnectionByDataSource connectionSource,
                                    GenerateIdType generateIdType,
                                    SQLType sqlIdType) {
        super(DefaultTable.PROFILE.table(),
                COLUMNS_NAME,
                connectionSource,
                generateIdType,
                sqlIdType);
        setSpringMapper(new MapperSpringProfileDetails<>(COLUMNS_NAME, sqlIdType, getColumnIdName()));
    }

    @Override
    protected SqlParameters getParameters(DetailsProfile<ID> model){
        SqlParameters parameters = new SqlParameters();
        parameters.add(0, COLUMNS_NAME[0], model.getName());
        parameters.add(1, COLUMNS_NAME[1], model.getSurname());
        parameters.add(2, COLUMNS_NAME[2], model.getMiddlename());
        parameters.add(3, COLUMNS_NAME[3], model.getUserId());
        parameters.add(4, COLUMNS_NAME[4], model.isMan());
        parameters.add(5, COLUMNS_NAME[5], model.getDateBirth());
        parameters.add(6, COLUMNS_NAME[6], model.getPhone());
        parameters.add(7, COLUMNS_NAME[7], model.getCityId());
        return parameters;
    }

    @Override
    protected DetailsProfile<ID> fill(DetailsProfile<ID> model){
        IRepository<ID, DetailsUser<ID>> repositoryUser = new RepositoryUser<>(getConnectionSource(), GenerateIdType.NONE, getSqlIdType());
        model.setUser(repositoryUser.read(model.getUserId()));
        IRepository<ID, City<ID>> repositoryCity = new RepositoryCity(getConnectionSource(), GenerateIdType.NONE, getSqlIdType());
        model.setCity(repositoryCity.read(model.getCityId()));
        return model;
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
