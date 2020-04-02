package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.repositories.DefaultColumns;
import ru.strict.db.core.repositories.DefaultTable;
import ru.strict.models.Profile;
import ru.strict.models.User;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.repositories.interfaces.IRepositoryProfile;
import ru.strict.db.jdbc.mappers.sql.MapperSqlProfile;
import java.sql.Connection;
import java.sql.SQLType;

/**
 * Репозиторий таблицы "profile".
 * @param <ID> Тип идентификатора
 */
public class RepositoryProfile<ID>
        extends RepositoryJdbcBase<ID, Profile<ID>>
        implements IRepositoryProfile<ID, Profile<ID>> {

    private static final String[] COLUMNS_NAME = DefaultColumns.PROFILE.columns();

    public RepositoryProfile(ICreateConnection<Connection> connectionSource,
                             GenerateIdType generateIdType,
                             SQLType sqlIdType) {
        super(DefaultTable.PROFILE.table(),
                COLUMNS_NAME,
                connectionSource,
                generateIdType,
                sqlIdType);
        setSqlMapper(new MapperSqlProfile<>(COLUMNS_NAME, sqlIdType, getIdColumnName()));
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
        IRepository<ID, User<ID>> repositoryUser = new RepositoryUser(getConnectionSource(), GenerateIdType.NONE, getSqlIdType());
        model.setUser(repositoryUser.read(model.getUserId()));
        return model;
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
