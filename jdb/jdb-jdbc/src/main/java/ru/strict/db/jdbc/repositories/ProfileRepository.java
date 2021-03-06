package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.connections.IConnectionCreator;
import ru.strict.db.core.repositories.DefaultColumns;
import ru.strict.db.core.repositories.DefaultTable;
import ru.strict.db.core.repositories.interfaces.IProfileRepository;
import ru.strict.models.Profile;
import ru.strict.db.jdbc.mappers.sql.ProfileSqlMapper;

import java.sql.Connection;
import java.sql.SQLType;

/**
 * Репозиторий таблицы "profile".
 *
 * @param <ID> Тип идентификатора
 */
public class ProfileRepository<ID>
        extends JdbcRepository<ID, Profile<ID>>
        implements IProfileRepository<ID, Profile<ID>> {

    private static final String[] COLUMNS_NAME = DefaultColumns.PROFILE.columns();

    public ProfileRepository(IConnectionCreator<Connection> connectionSource,
            GenerateIdType generateIdType,
            SQLType sqlIdType) {
        super(DefaultTable.PROFILE.table(),
                COLUMNS_NAME,
                connectionSource,
                generateIdType,
                sqlIdType);
        setSqlMapper(new ProfileSqlMapper<>(COLUMNS_NAME, sqlIdType, getIdColumnName()));
    }

    @Override
    protected SqlParameters getParameters(Profile<ID> model) {
        SqlParameters parameters = new SqlParameters();
        parameters.set(0, COLUMNS_NAME[0], model.getName());
        parameters.set(1, COLUMNS_NAME[1], model.getSurname());
        parameters.set(2, COLUMNS_NAME[2], model.getUserId());
        return parameters;
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
