package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.connections.IConnectionCreator;
import ru.strict.db.core.repositories.DefaultColumns;
import ru.strict.db.core.repositories.DefaultTable;
import ru.strict.db.core.repositories.interfaces.IJWTTokenRepository;
import ru.strict.models.JWTToken;
import ru.strict.models.User;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.jdbc.mappers.sql.JWTTokenSqlMapper;

import java.sql.Connection;
import java.sql.SQLType;

public class JWTTokenRepository<ID>
        extends JdbcRepository<ID, JWTToken<ID>>
        implements IJWTTokenRepository<ID> {

    private static final String[] COLUMNS_NAME = DefaultColumns.JWT_TOKEN.columns();

    public JWTTokenRepository(IConnectionCreator<Connection> connectionSource,
            GenerateIdType generateIdType,
            SQLType sqlIdType) {
        super(DefaultTable.JWT_TOKEN.table(),
                COLUMNS_NAME,
                connectionSource,
                generateIdType,
                sqlIdType);
        setSqlMapper(new JWTTokenSqlMapper<>(COLUMNS_NAME, sqlIdType, getIdColumnName()));
    }

    @Override
    protected SqlParameters getParameters(JWTToken<ID> model) {
        SqlParameters parameters = new SqlParameters();
        parameters.set(0, COLUMNS_NAME[0], model.getAccessToken());
        parameters.set(1, COLUMNS_NAME[1], model.getRefreshToken());
        parameters.set(2, COLUMNS_NAME[2], model.getExpireTimeAccess());
        parameters.set(3, COLUMNS_NAME[3], model.getExpireTimeRefresh());
        parameters.set(4, COLUMNS_NAME[4], model.getIssuedAt());
        parameters.set(5, COLUMNS_NAME[5], model.getIssuer());
        parameters.set(6, COLUMNS_NAME[6], model.getSubject());
        parameters.set(7, COLUMNS_NAME[7], model.getNotBefore());
        parameters.set(8, COLUMNS_NAME[8], model.getAudience());
        parameters.set(9, COLUMNS_NAME[9], model.getSecret());
        parameters.set(10, COLUMNS_NAME[10], model.getAlgorithm());
        parameters.set(11, COLUMNS_NAME[11], model.getType());
        parameters.set(12, COLUMNS_NAME[12], model.getUserId());
        return parameters;
    }

    @Override
    protected JWTToken<ID> fill(JWTToken<ID> model) {
        // Добавление пользователя
        IRepository<ID, User<ID>> userRepository =
                new UserRepository(getConnectionSource(), GenerateIdType.NONE, getIdSqlType());
        model.setUser(userRepository.read(model.getUserId()));
        return model;
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
