package ru.strict.db.spring.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.connections.CreateConnectionByDataSource;
import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.core.dto.DtoServiceOnRole;
import ru.strict.db.core.entities.EntityServiceOnRole;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.repositories.interfaces.IRepositoryServiceOnRole;
import ru.strict.db.spring.mappers.sql.MapperSqlServiceOnRole;

import java.util.LinkedHashMap;
import java.util.Map;

public class RepositoryServiceOnRole<ID, SERVICE>
        extends RepositorySpringBase<ID, EntityServiceOnRole<ID, SERVICE>, DtoServiceOnRole<ID, SERVICE>>
        implements IRepositoryServiceOnRole<ID, SERVICE> {

    private static final String[] COLUMNS_NAME = new String[] {"service_id", "roleuser_id"};

    public RepositoryServiceOnRole(CreateConnectionByDataSource connectionSource,
                                   MapperDtoBase<ID, EntityServiceOnRole<ID, SERVICE>, DtoServiceOnRole<ID, SERVICE>> dtoMapper,
                                   GenerateIdType generateIdType) {
        super("service_on_role", COLUMNS_NAME, connectionSource,
                dtoMapper,
                new MapperSqlServiceOnRole<ID, SERVICE>(COLUMNS_NAME),
                generateIdType);
    }

    @Override
    protected SqlParameters getParameters(EntityServiceOnRole<ID, SERVICE> entity){
        SqlParameters parameters = new SqlParameters();
        parameters.add(0, COLUMNS_NAME[0], entity.getServiceId());
        parameters.add(1, COLUMNS_NAME[1], entity.getRoleId());
        return parameters;
    }

    @Override
    protected DtoServiceOnRole<ID, SERVICE> fill(DtoServiceOnRole<ID, SERVICE> dto){
        IRepository<ID, DtoRoleuser<ID>> repositoryRoleuser = null;
        try {
            // Добавление роли пользователя
            repositoryRoleuser = new RepositoryRoleuser(getConnectionSource(), GenerateIdType.NONE);
            dto.setRole(repositoryRoleuser.read(dto.getRoleId()));
        }finally {
            if(repositoryRoleuser != null){
                repositoryRoleuser.close();
            }
        }
        return dto;
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
