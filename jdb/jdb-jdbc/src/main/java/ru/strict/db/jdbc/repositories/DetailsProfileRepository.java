package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.connections.IConnectionCreator;
import ru.strict.db.core.repositories.DefaultColumns;
import ru.strict.db.core.repositories.DefaultTable;
import ru.strict.db.core.repositories.interfaces.IDetailsProfileRepository;
import ru.strict.models.City;
import ru.strict.models.DetailsProfile;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.jdbc.mappers.sql.ProfileDetailsSqlMapper;
import ru.strict.models.DetailsUser;

import java.sql.Connection;
import java.sql.SQLType;

/**
 * Репозиторий таблицы "profile".
 *
 * @param <ID> Тип идентификатора
 */
public class DetailsProfileRepository<ID>
        extends JdbcRepository<ID, DetailsProfile<ID>>
        implements IDetailsProfileRepository<ID, DetailsProfile<ID>> {

    private static final String[] COLUMNS_NAME = DefaultColumns.PROFILE_DETAILS.columns();

    public DetailsProfileRepository(IConnectionCreator<Connection> connectionSource,
            GenerateIdType generateIdType,
            SQLType sqlIdType) {
        super(DefaultTable.PROFILE.table(),
                COLUMNS_NAME,
                connectionSource,
                generateIdType,
                sqlIdType);
        setSqlMapper(new ProfileDetailsSqlMapper<>(COLUMNS_NAME, sqlIdType, getIdColumnName()));
    }

    @Override
    protected SqlParameters getParameters(DetailsProfile<ID> model) {
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
    protected DetailsProfile<ID> fill(DetailsProfile<ID> model) {
        IRepository<ID, DetailsUser<ID>> repositoryUser =
                new UserRepository<>(getConnectionSource(), GenerateIdType.NONE, getSqlIdType());
        model.setUser(repositoryUser.read(model.getUserId()));

        IRepository<ID, City<ID>> repositoryCity =
                new CityRepository(getConnectionSource(), GenerateIdType.NONE, getSqlIdType());
        model.setCity(repositoryCity.read(model.getCityId()));

        return model;
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
