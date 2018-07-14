package ru.strict.db.spring.repositories;

import org.springframework.jdbc.core.RowMapper;
import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.connections.CreateConnectionByDataSource;
import ru.strict.db.core.dto.DtoBase;
import ru.strict.db.core.entities.EntityBase;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhere;

/**
 * Базовый класс репозитория с использованием Spring для таблиц со столбцом наименования (caption)
 * @param <ID> Тип идентификатора
 * @param <E> Тип сущности базы данных (entity)
 * @param <DTO> Тип Dto-сущности базы данных
 */
public abstract class RepositoryNamedBase
        <ID, E extends EntityBase, DTO extends DtoBase>
        extends RepositorySpringBase<ID, E, DTO> {

    public RepositoryNamedBase(String tableName, String[] columnsName, CreateConnectionByDataSource connectionSource, MapperDtoBase<E, DTO> dtoMapper, RowMapper<E> springMapper, GenerateIdType generateIdType) {
        super(tableName, columnsName, connectionSource, dtoMapper, springMapper, generateIdType);
    }

    /**
     * Чтение записи из базы данных по наименованию
     * @param caption Значение столбца наименования
     * @return
     */
    public DTO readByName(String caption){
        DbRequests requests = new DbRequests(getTableName(), true);
        requests.add(new DbWhere(getTableName(), getColumnWithName(), caption, "="));

        DTO result = readAll(requests).stream().findFirst().orElse(null);
        return result;
    }

    /**
     * Получить наименование столбца, который выполняет роль наименования записи
     * @return
     */
    protected abstract String getColumnWithName();
}
