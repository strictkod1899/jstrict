package ru.strict.db.spring.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.connections.CreateConnectionByDataSource;
import ru.strict.db.core.repositories.DefaultColumns;
import ru.strict.db.core.repositories.DefaultTable;
import ru.strict.db.core.repositories.interfaces.IRepositoryUser;
import ru.strict.db.spring.mappers.sql.MapperSpringUser;
import ru.strict.db.spring.mappers.sql.MapperSpringUserSecurity;
import ru.strict.db.spring.security.UserSecurity;

import java.sql.SQLType;

public class RepositoryUserSecurity<ID>
        extends RepositorySpringNamed<ID, UserSecurity<ID>>
        implements IRepositoryUser<ID, UserSecurity<ID>> {

    private static final String[] COLUMNS_NAME = DefaultColumns.USER.columns();

    public RepositoryUserSecurity(CreateConnectionByDataSource connectionSource,
                          GenerateIdType generateIdType,
                          SQLType sqlIdType) {
        super(DefaultTable.USER.table(),
                COLUMNS_NAME,
                connectionSource,
                generateIdType,
                sqlIdType);
        setSpringMapper(
                new MapperSpringUserSecurity<>(
                        new MapperSpringUser<>(COLUMNS_NAME, sqlIdType, getColumnIdName())
                )
        );
    }

    @Override
    protected SqlParameters getParameters(UserSecurity<ID> model){
        SqlParameters parameters = new SqlParameters();
        parameters.add(0, COLUMNS_NAME[0], model.getUsername());
        parameters.add(1, COLUMNS_NAME[1], model.getPasswordEncode());
        parameters.add(2, COLUMNS_NAME[2], model.getEmail());
        parameters.add(3, COLUMNS_NAME[3], model.isBlocked());
        parameters.add(4, COLUMNS_NAME[4], model.isDeleted());
        parameters.add(5, COLUMNS_NAME[5], model.isConfirmEmail());
        parameters.add(6, COLUMNS_NAME[6], model.getSalt());
        parameters.add(7, COLUMNS_NAME[7], model.getSecret());
        return parameters;
    }

    @Override
    protected UserSecurity<ID> fill(UserSecurity<ID> model){
        return model;
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
