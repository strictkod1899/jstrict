package ru.strict.db.spring.repositories;

import ru.strict.db.core.common.GenerateIdType;

import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.connections.CreateConnectionByDataSource;
import ru.strict.db.core.repositories.DefaultColumns;
import ru.strict.db.core.repositories.DefaultTable;
import ru.strict.db.core.requests.DbTable;
import ru.strict.models.Profile;
import ru.strict.models.UserDetails;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.repositories.interfaces.IRepositoryProfile;
import ru.strict.db.spring.mappers.sql.MapperSpringProfile;

import java.sql.SQLType;

/**
 * Репозиторий таблицы "profile"
 * @param <ID> Тип идентификатора
 */
public class RepositoryProfile<ID>
        extends RepositorySpringBase<ID, Profile<ID>>
        implements IRepositoryProfile<ID, Profile<ID>> {

    private static final String[] COLUMNS_NAME = DefaultColumns.PROFILE.columns();

    public RepositoryProfile(CreateConnectionByDataSource connectionSource,
                             GenerateIdType generateIdType,
                             SQLType sqlIdType) {
        super(DefaultTable.PROFILE.table(),
                COLUMNS_NAME,
                connectionSource,
                generateIdType,
                sqlIdType);
        setSpringMapper(new MapperSpringProfile<>(COLUMNS_NAME, sqlIdType, getColumnIdName()));
    }

    @Override
    protected SqlParameters getParameters(Profile<ID> model){
        SqlParameters parameters = new SqlParameters();
        parameters.add(0, COLUMNS_NAME[0], model.getName());
        parameters.add(1, COLUMNS_NAME[1], model.getSurname());
        parameters.add(2, COLUMNS_NAME[2], model.getUserId());
        return parameters;
    }

    @Override
    protected Profile<ID> fill(Profile<ID> model){
        IRepository<ID, UserDetails<ID>> repositoryUser = new RepositoryUser(getConnectionSource(), GenerateIdType.NONE, getSqlIdType());
        model.setUser(repositoryUser.read(model.getUserId()));
        return model;
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
