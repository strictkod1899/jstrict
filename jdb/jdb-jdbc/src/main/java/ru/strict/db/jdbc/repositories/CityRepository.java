package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.connections.IConnectionCreator;
import ru.strict.db.core.repositories.DefaultColumns;
import ru.strict.db.core.repositories.DefaultTable;
import ru.strict.db.core.repositories.interfaces.ICityRepository;
import ru.strict.models.City;
import ru.strict.models.Country;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.jdbc.mappers.sql.CitySqlMapper;

import java.sql.Connection;
import java.sql.SQLType;

public class CityRepository<ID>
        extends NamedJdbcRepository<ID, City<ID>>
        implements ICityRepository<ID> {

    private static final String[] COLUMNS_NAME = DefaultColumns.CITY.columns();

    public CityRepository(IConnectionCreator<Connection> connectionSource,
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
        parameters.set(0, COLUMNS_NAME[0], model.getCaption());
        parameters.set(1, COLUMNS_NAME[1], model.getCountryId());
        return parameters;
    }

    @Override
    protected City<ID> fill(City<ID> model) {
        IRepository<ID, Country<ID>> countryRepository =
                new CountryRepository<>(getConnectionSource(), GenerateIdType.NONE, getSqlIdType());
        model.setCountry(countryRepository.read(model.getCountryId()));
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
