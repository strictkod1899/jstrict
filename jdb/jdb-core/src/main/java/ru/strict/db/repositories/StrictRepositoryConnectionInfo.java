package ru.strict.db.repositories;

import ru.strict.db.StrictConnectionInfo;
import ru.strict.db.dto.StrictDtoBase;
import ru.strict.db.entities.StrictEntityBase;
import ru.strict.db.mappers.StrictBaseMapper;
import ru.strict.utils.StrictUtilLogger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class StrictRepositoryConnectionInfo
        <ID, E extends StrictEntityBase, DTO extends StrictDtoBase>
        extends StrictRepositoryBase <ID, StrictConnectionInfo, E, DTO>{

    public StrictRepositoryConnectionInfo(StrictConnectionInfo connectionSource, StrictBaseMapper<E, DTO> mapper) {
        super(connectionSource, mapper);
    }

    @Override
    protected Connection createConnection() {
        StrictUtilLogger.info(StrictRepositoryConnectionInfo.class, "createConnection - started");
        try {
            // Путь к базе данных
            String connectUrl = getConnectionSource().getDbType().getUrl() + getConnectionSource().getDbCaption();
            Driver jdbcDriver = (Driver) Class.forName(getConnectionSource().getDbType().getDriver()).
                    newInstance();
            // Регистрация данного драйвера
            DriverManager.registerDriver(jdbcDriver);
            // Соединение с Базой Данных
            return DriverManager.getConnection(connectUrl, getConnectionSource().getUsername(), getConnectionSource().getPassword());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            StrictUtilLogger.error(StrictRepositoryConnectionInfo.class, ex.getClass().toString(), ex.getMessage());
        }
        return null;
    }
}
