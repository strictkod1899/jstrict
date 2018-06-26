package ru.strict.db.spring.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.connections.CreateConnectionByDataSource;
import ru.strict.db.core.dto.*;
import ru.strict.db.core.dto.DtoUserBase;
import ru.strict.db.core.dto.DtoUserOnRole;
import ru.strict.db.core.entities.EntityProfileInfo;
import ru.strict.db.core.entities.EntityUser;
import ru.strict.db.core.entities.EntityUserOnRole;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.repositories.RepositoryBase;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhere;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.spring.mappers.sql.MapperSqlUser;
import ru.strict.utils.UtilLogger;

import java.util.*;

public class RepositoryUser<ID, DTO extends DtoUserBase>
        extends RepositorySpringBase<ID, EntityUser, DTO> {

    private static final String[] COLUMNS_NAME = new String[] {"username", "passwordencode", "token"};

    public RepositoryUser(CreateConnectionByDataSource connectionSource,
                          MapperDtoBase<EntityUser, DTO> dtoMapper,
                          GenerateIdType isGenerateId) {
        super("userx", COLUMNS_NAME, connectionSource, dtoMapper, new MapperSqlUser(COLUMNS_NAME), isGenerateId);
    }

    @Override
    protected Map<Integer, Object> getValueByColumn(DTO dto){
        if(dto instanceof DtoUser) {
            DtoUser dtoUser = (DtoUser) dto;
            Map<Integer, Object> valuesByColumn = new LinkedHashMap();
            valuesByColumn.put(0, dtoUser.getUsername());
            valuesByColumn.put(1, dtoUser.getPasswordEncode());
            valuesByColumn.put(2, dtoUser.getToken());
            return valuesByColumn;
        }else {
            UtilLogger.error(RepositoryUser.class, "Expected a type dto-object is DtoUser");
            throw new IllegalArgumentException("Expected a type dto-object is DtoUser");
        }
    }

    @Override
    protected DTO fill(DTO dto){
        // Добавление ролей пользователей
        RepositorySpringBase<ID, EntityUserOnRole, DtoUserOnRole> repositoryUserOnRole =
                new RepositoryUserOnRole(getConnectionSource(), GenerateIdType.NONE);
        DbRequests requests = new DbRequests(repositoryUserOnRole.getTableName(), true);
        requests.add(new DbWhere(repositoryUserOnRole.getTableName(), "userx_id", dto.getId(), "="));
        List<DtoUserOnRole> userOnRoles = repositoryUserOnRole.readAll(requests);

        IRepository<ID, DtoRoleuser> repositoryRoleuser = new RepositoryRoleuser<>(getConnectionSource(), GenerateIdType.NONE);
        Collection<DtoRoleuser> roleusers = new LinkedList<>();
        for(DtoUserOnRole<ID> userOnRole : userOnRoles) {
            roleusers.add(repositoryRoleuser.read(userOnRole.getRoleId()));
        }
        dto.setRolesuser(roleusers);

        // Добавления профиля
        RepositorySpringBase<ID, EntityProfileInfo, DtoProfileInfo> repositoryProfile =
                new RepositoryProfileInfo<>(getConnectionSource(), GenerateIdType.NONE);
        requests = new DbRequests(repositoryProfile.getTableName(), true);
        requests.add(new DbWhere(repositoryProfile.getTableName(), "userx_id", dto.getId(), "="));
        dto.setProfile(repositoryProfile.readAll(requests).stream().findFirst().orElse(null));
        return dto;
    }
}
