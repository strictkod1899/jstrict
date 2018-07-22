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
        <ID, E extends EntityBase, DTO extends DtoBase>
        extends RepositoryJdbcBase<ID, E, DTO>
        implements IRepositoryNamed<ID, DTO> {

    public RepositoryNamedBase(String tableName,
                               String[] columnsName,
                               ICreateConnection<Connection> connectionSource,
                               MapperDtoBase<E, DTO> dtoMapper,
                               MapperSqlBase<E> sqlMapper,
                               GenerateIdType isGenerateId) {
        super(tableName, columnsName, connectionSource, dtoMapper, sqlMapper, isGenerateId);
    }

    @Override
    public DTO readByName(String caption){
        DbRequests requests = new DbRequests(getTableName(), true);
        requests.add(new DbWhere(getTableName(), getColumnWithName(), caption, "="));

        DTO result = readAll(requests).stream().findFirst().orElse(null);
        return result;
    }

    @Override
    public List<DTO> readAllByName(String caption){
        DbRequests requests = new DbRequests(getTableName(), true);
        requests.add(new DbWhere(getTableName(), getColumnWithName(), caption, "="));

        List<DTO> result = readAll(requests);
        return result;
    }

    /**
     * Получить наименование столбца, который выполняет роль наименования записи
     * @return
     */
    protected abstract String getColumnWithName();
}