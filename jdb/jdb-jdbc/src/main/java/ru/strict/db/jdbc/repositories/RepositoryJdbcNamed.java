package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.entities.EntityBase;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.mappers.sql.MapperSqlBase;
import ru.strict.db.core.repositories.IRepositoryNamed;
import ru.strict.db.core.requests.DbTable;
import ru.strict.models.DtoBase;

import java.sql.Connection;
import java.util.List;

public abstract class RepositoryJdbcNamed<ID, E extends EntityBase<ID>, DTO extends DtoBase<ID>>
        extends RepositoryJdbcBase<ID, E, DTO>
        implements IRepositoryNamed<ID, DTO> {

    public RepositoryJdbcNamed(DbTable table,
                               String[] columnsName,
                               ICreateConnection<Connection> connectionSource,
                               MapperDtoBase<ID, E, DTO> dtoMapper,
                               MapperSqlBase<ID, E> sqlMapper,
                               GenerateIdType generateIdType) {
        super(table, columnsName, connectionSource, dtoMapper, sqlMapper, generateIdType);
    }

    @Override
    public DTO readByNameFill(String caption) {
        DTO dto = readByName(caption);
        if(dto != null) {
            dto = fill(dto);
        }
        return dto;
    }

    @Override
    public List<DTO> readAllByNameFill(String caption) {
        List<DTO> dtoList = readAllByName(caption);
        if(dtoList != null) {
            dtoList.stream().forEach((dto) -> dto = fill(dto));
        }
        return dtoList;
    }
}
