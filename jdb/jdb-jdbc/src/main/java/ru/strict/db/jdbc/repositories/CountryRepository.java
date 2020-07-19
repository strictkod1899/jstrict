package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.connections.IConnectionCreator;
import ru.strict.db.core.repositories.DefaultColumns;
import ru.strict.db.core.repositories.DefaultTable;
import ru.strict.models.Country;
import ru.strict.db.jdbc.mappers.sql.CountrySqlMapper;

import java.sql.Connection;
import java.sql.SQLType;

public class CountryRepository<ID> extends NamedJdbcRepository<ID, Country<ID>> {

    private static final String[] COLUMNS_NAME = DefaultColumns.COUNTRY.columns();

    public CountryRepository(IConnectionCreator<Connection> connectionSource,
            GenerateIdType generateIdType,
            SQLType sqlIdType) {
        super(DefaultTable.COUNTRY.table(),
                COLUMNS_NAME,
                connectionSource,
                generateIdType,
                sqlIdType);
        setSqlMapper(new CountrySqlMapper<>(COLUMNS_NAME, sqlIdType, getIdColumnName()));
    }

    @Override
    protected SqlParameters getParameters(Country<ID> model) {
        SqlParameters parameters = new SqlParameters();
        parameters.set(0, COLUMNS_NAME[0], model.getCaption());
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
