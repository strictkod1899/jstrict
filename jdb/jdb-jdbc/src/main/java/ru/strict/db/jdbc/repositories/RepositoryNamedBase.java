package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.dto.DtoBase;
import ru.strict.db.core.entities.EntityBase;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.mappers.sql.MapperSqlBase;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhere;

import java.sql.Connection;
import java.util.List;

/**
 * Базовый класс репозитория с использованием Jdbc для таблиц со столбцом наименования (caption)
 * @param <ID> Тип идентификатора
 * @param <E> Тип сущности базы данных (entity)
 * @param <DTO> Тип Dto-сущности базы данных
 */
public abstract class RepositoryNamedBase
        <ID, E extends EntityBase<ID>, DTO extends DtoBase<ID>>
        extends RepositoryJdbcBase<ID, E, DTO>
        implements IRepositoryNamed<ID, DTO> {

    public RepositoryNamedBase(String tableName,
                               String[] columnsName,
                               ICreateConnection<Connection> connectionSource,
                               MapperDtoBase<ID, E, DTO> dtoMapper,
                               MapperSqlBase<ID, E> sqlMapper,
                               GenerateIdType generateIdType) {
        super(tableName, columnsName, connectionSource, dtoMapper, sqlMapper, generateIdType);
    }

    @Override
    public DTO readByName(String caption){
        DbRequests requests = new DbRequests(getTableName(), true);
        requests.addWhere(new DbWhere(getTableName(), getColumnWithName(), caption, "="));

        DTO result = readAll(requests).stream().findFirst().orElse(null);
        return result;
    }

    @Override
    public List<DTO> readAllByName(String caption){
        DbRequests requests = new DbRequests(getTableName(), true);
        requests.addWhere(new DbWhere(getTableName(), getColumnWithName(), caption, "="));

        List<DTO> result = readAll(requests);
        return result;
    }

    /**
     * Получить наименование столбца, который выполняет роль наименования записи
     * @return
     */
    protected abstract String getColumnWithName();
}
