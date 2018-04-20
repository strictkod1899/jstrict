package ru.strict.db.repositories;

import ru.strict.db.dto.StrictDtoBase;
import ru.strict.db.entities.StrictEntityBase;
import ru.strict.db.mappers.StrictBaseMapper;
import ru.strict.utils.StrictUtilLogger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class StrictRepositoryDataSource
        <ID, E extends StrictEntityBase, DTO extends StrictDtoBase>
        extends StrictRepositoryBase <ID, DataSource, E, DTO> {

    public StrictRepositoryDataSource(DataSource connectionSource, StrictBaseMapper<E, DTO> mapper) {
        super(connectionSource, mapper);
    }

    @Override
    protected Connection createConnection() {
        StrictUtilLogger.info(StrictRepositoryDataSource.class, "createConnection - started");
        try {
            return getConnectionSource().getConnection();
        } catch (SQLException ex) {
            StrictUtilLogger.error(StrictRepositoryDataSource.class, ex.getClass().toString(), ex.getMessage());
            return null;
        }
    }
}
