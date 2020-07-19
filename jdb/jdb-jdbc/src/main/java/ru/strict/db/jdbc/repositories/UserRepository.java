package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.connections.IConnectionCreator;
import ru.strict.db.core.repositories.DefaultColumns;
import ru.strict.db.core.repositories.DefaultTable;
import ru.strict.db.core.repositories.interfaces.IUserRepository;
import ru.strict.models.*;
import ru.strict.db.jdbc.mappers.sql.UserSqlMapper;

import java.sql.Connection;
import java.sql.SQLType;

public class UserRepository<ID>
        extends NamedJdbcRepository<ID, DetailsUser<ID>>
        implements IUserRepository<ID, DetailsUser<ID>> {

    private static final String[] COLUMNS_NAME = DefaultColumns.USER.columns();

    public UserRepository(IConnectionCreator<Connection> connectionSource,
            GenerateIdType generateIdType,
            SQLType sqlIdType) {
        super(DefaultTable.USER.table(),
                COLUMNS_NAME,
                connectionSource,
                generateIdType,
                sqlIdType);
        setSqlMapper(new UserSqlMapper<>(COLUMNS_NAME, sqlIdType, getIdColumnName()));
    }

    @Override
    protected SqlParameters getParameters(DetailsUser<ID> model) {
        SqlParameters parameters = new SqlParameters();
        parameters.set(0, COLUMNS_NAME[0], model.getUsername());
        parameters.set(1, COLUMNS_NAME[1], model.getPasswordEncode());
        parameters.set(2, COLUMNS_NAME[2], model.getEmail());
        parameters.set(3, COLUMNS_NAME[3], model.isBlocked());
        parameters.set(4, COLUMNS_NAME[4], model.isDeleted());
        parameters.set(5, COLUMNS_NAME[5], model.isConfirmEmail());
        parameters.set(6, COLUMNS_NAME[6], model.getSalt());
        parameters.set(7, COLUMNS_NAME[7], model.getSecret());
        return parameters;
    }

    @Override
    public String getColumnWithName() {
        return COLUMNS_NAME[0];
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
