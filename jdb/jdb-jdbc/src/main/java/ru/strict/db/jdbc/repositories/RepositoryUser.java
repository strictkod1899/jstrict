package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.dto.*;
import ru.strict.db.core.dto.DtoUserOnRole;
import ru.strict.db.core.entities.EntityUser;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.repositories.interfaces.IRepositoryUser;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;
import ru.strict.db.core.requests.WhereType;
import ru.strict.db.jdbc.mappers.sql.MapperSqlUser;

import java.sql.Connection;
import java.util.*;

public class RepositoryUser<ID, DTO extends DtoUserBase<ID>>
        extends RepositoryNamedBase<ID, EntityUser<ID>, DTO>
        implements IRepositoryUser<ID, DTO> {

    private static final String[] COLUMNS_NAME = new String[] {"username", "passwordencode", "email",
            "isBlocked", "isDeleted", "isConfirmEmail"};

    public RepositoryUser(ICreateConnection<Connection> connectionSource,
                          MapperDtoBase<ID, EntityUser<ID>, DTO> dtoMapper,
                          GenerateIdType generateIdType) {
        super("userx", COLUMNS_NAME,
                connectionSource,
                dtoMapper,
                new MapperSqlUser<ID>(COLUMNS_NAME),
                generateIdType);
    }

    @Override
    protected Map<Integer, Object> getValueByColumn(EntityUser<ID> entity){
        Map<Integer, Object> valuesByColumn = new LinkedHashMap();
        valuesByColumn.put(0, entity.getUsername());
        valuesByColumn.put(1, entity.getPasswordEncode());
        valuesByColumn.put(2, entity.getEmail());
        valuesByColumn.put(3, entity.isBlocked());
        valuesByColumn.put(4, entity.isDeleted());
        valuesByColumn.put(5, entity.isConfirmEmail());
        return valuesByColumn;
    }

    @Override
    protected DTO fill(DTO dto){
        IRepository<ID, DtoUserOnRole<ID>> repositoryUserOnRole = null;
        IRepository<ID, DtoRoleuser<ID>> repositoryRoleuser = null;
        IRepository<ID, DtoProfileInfo<ID>> repositoryProfile = null;
        try {
            // Добавление ролей пользователей
            repositoryUserOnRole = new RepositoryUserOnRole(getConnectionSource(), GenerateIdType.NONE);
            DbRequests requests = new DbRequests(repositoryUserOnRole.getTableName());
            requests.addWhere(new DbWhereItem(repositoryUserOnRole.getTableName(), "userx_id", dto.getId(), "="));
            List<DtoUserOnRole<ID>> userOnRoles = repositoryUserOnRole.readAll(requests);

            repositoryRoleuser = new RepositoryRoleuser<>(getConnectionSource(), GenerateIdType.NONE);
            Collection<DtoRoleuser<ID>> roleusers = new ArrayList<>();
            for (DtoUserOnRole<ID> userOnRole : userOnRoles) {
                roleusers.add(repositoryRoleuser.read(userOnRole.getRoleId()));
            }
            dto.setRoles(roleusers);

            // Добавления профиля
            repositoryProfile = new RepositoryProfileInfo<>(getConnectionSource(), GenerateIdType.NONE);
            requests = new DbRequests(repositoryProfile.getTableName());
            requests.addWhere(new DbWhereItem(repositoryProfile.getTableName(), "userx_id", dto.getId(), "="));
            dto.setProfile(repositoryProfile.readAll(requests).stream().findFirst().orElse(null));
        }finally {
            if(repositoryUserOnRole != null){
                repositoryUserOnRole.close();
            }
            if(repositoryRoleuser != null){
                repositoryRoleuser.close();
            }
            if(repositoryProfile != null){
                repositoryProfile.close();
            }
        }
        return dto;
    }

    @Override
    public DTO readByEmail(String email) {
        DbRequests requests = new DbRequests(getTableName());
        requests.addWhere(new DbWhereItem(getTableName(), "email", email, "="));

        DTO result = readAll(requests).stream().findFirst().orElse(null);
        return result;
    }

    @Override
    protected String getColumnWithName() {
        return COLUMNS_NAME[0];
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
