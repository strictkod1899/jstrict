package ru.strict.db.mybatis.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.dto.DtoBase;
import ru.strict.db.core.entities.EntityBase;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhere;
import ru.strict.db.core.requests.DbWhereItem;
import ru.strict.db.mybatis.connection.CreateConnectionByMybatis;
import ru.strict.db.mybatis.mappers.sql.MapperSqlExtension;

import java.util.List;

/**
 * Базовый класс репозитория с использованием Spring для таблиц со столбцом наименования (caption)
 * @param <ID> Тип идентификатора
 * @param <E> Тип сущности базы данных (entity)
 * @param <DTO> Тип Dto-сущности базы данных
 */
public abstract class RepositoryNamedBase
        <ID, E extends EntityBase<ID>, DTO extends DtoBase<ID>, MAPPER extends MapperSqlExtension<ID, E>>
        extends RepositoryMybatisBase<ID, E, DTO, MAPPER>
        implements IRepositoryNamed<ID, DTO> {

    public RepositoryNamedBase(String tableName,
                               String[] columnsName,
                               CreateConnectionByMybatis connectionSource,
                               Class<MAPPER> mybatisMapper,
                               MapperDtoBase<ID, E, DTO> dtoMapper,
                               GenerateIdType generateIdType) {
        super(tableName, columnsName, connectionSource, mybatisMapper, dtoMapper, generateIdType);
    }

    @Override
    public DTO readByName(String caption){
        DbRequests requests = new DbRequests(getTableName());
        requests.addWhere(new DbWhereItem(getTableName(), getColumnWithName(), caption, "="));

        DTO result = readAll(requests).stream().findFirst().orElse(null);
        return result;
    }

    @Override
    public List<DTO> readAllByName(String caption){
        DbRequests requests = new DbRequests(getTableName());
        requests.addWhere(new DbWhereItem(getTableName(), getColumnWithName(), caption, "="));

        List<DTO> result = readAll(requests);
        return result;
    }

    /**
     * Получить наименование столбца, который выполняет роль наименования записи
     * @return
     */
    protected abstract String getColumnWithName();
}
