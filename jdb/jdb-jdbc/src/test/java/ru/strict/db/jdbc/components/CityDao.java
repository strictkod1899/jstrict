package ru.strict.db.jdbc.components;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.connection.IConnectionCreator;
import ru.strict.db.jdbc.dao.NamedJdbcDao;

import java.sql.Connection;
import java.sql.SQLType;

public class CityDao<ID> extends NamedJdbcDao<ID, City<ID>> {

    private static final String[] COLUMNS_NAME = DefaultColumns.CITY.columns();

    public CityDao(IConnectionCreator<Connection> connectionSource,
            GenerateIdType generateIdType,
            SQLType sqlIdType) {
        super(DefaultTable.CITY.table(),
                COLUMNS_NAME,
                connectionSource,
                generateIdType,
                sqlIdType);
        setSqlMapper(new CitySqlMapper<>(COLUMNS_NAME, sqlIdType, getIdColumnName()));
    }

    @Override
    protected SqlParameters getParameters(City<ID> model) {
        SqlParameters parameters = new SqlParameters();
        parameters.set(0, COLUMNS_NAME[0], model.getName());
        parameters.set(1, COLUMNS_NAME[1], model.getCountryId());
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
