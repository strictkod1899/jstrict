package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.repositories.DefaultColumns;
import ru.strict.db.core.repositories.DefaultTable;
import ru.strict.models.JWTToken;
import ru.strict.models.User;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.repositories.interfaces.IRepositoryJWTToken;
import ru.strict.db.jdbc.mappers.sql.MapperSqlJWTToken;
import java.sql.Connection;
import java.sql.SQLType;

public class RepositoryJWTToken<ID>
        extends RepositoryJdbcBase<ID, JWTToken<ID>>
        implements IRepositoryJWTToken<ID> {

    private static final String[] COLUMNS_NAME = DefaultColumns.JWT_TOKEN.columns();

    public RepositoryJWTToken(ICreateConnection<Connection> connectionSource,
                              GenerateIdType generateIdType,
                              SQLType sqlIdType) {
        super(DefaultTable.JWT_TOKEN.table(),
                COLUMNS_NAME,
                connectionSource,
                generateIdType,
                sqlIdType);
        setSqlMapper(new MapperSqlJWTToken<>(COLUMNS_NAME, sqlIdType, getIdColumnName()));
    }

    @Override
    protected SqlParameters getParameters(JWTToken<ID> model){
        SqlParameters parameters = new SqlParameters();
        parameters.add(0, COLUMNS_NAME[0], model.getAccessToken());
        parameters.add(1, COLUMNS_NAME[1], model.getRefreshToken());
        parameters.add(2, COLUMNS_NAME[2], model.getExpireTimeAccess());
        parameters.add(3, COLUMNS_NAME[3], model.getExpireTimeRefresh());
        parameters.add(4, COLUMNS_NAME[4], model.getIssuedAt());
        parameters.add(5, COLUMNS_NAME[5], model.getIssuer());
        parameters.add(6, COLUMNS_NAME[6], model.getSubject());
        parameters.add(7, COLUMNS_NAME[7], model.getNotBefore());
        parameters.add(8, COLUMNS_NAME[8], model.getAudience());
        parameters.add(9, COLUMNS_NAME[9], model.getSecret());
        parameters.add(10, COLUMNS_NAME[10], model.getAlgorithm());
        parameters.add(11, COLUMNS_NAME[11], model.getType());
        parameters.add(12, COLUMNS_NAME[12], model.getUserId());
        return parameters;
    }

    @Override
    protected JWTToken<ID> fill(JWTToken<ID> model){
        // Добавление пользователя
        IRepository<ID, User<ID>> repositoryUser = new RepositoryUser(getConnectionSource(), GenerateIdType.NONE, getSqlIdType());
        model.setUser(repositoryUser.read(model.getUserId()));
        return model;
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
