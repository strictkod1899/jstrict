package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.core.dto.DtoUser;
import ru.strict.db.core.dto.DtoUserOnRole;
import ru.strict.db.core.entities.EntityUser;
import ru.strict.db.core.entities.EntityUserOnRole;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.jdbc.mappers.sql.MapperSqlUserOnRole;
import ru.strict.utils.UtilClass;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;

public class RepositoryUserOnRole<ID>
        extends RepositoryJdbcBase<ID, EntityUserOnRole<ID>, DtoUserOnRole<ID>> {

    private static final String[] COLUMNS_NAME = new String[] {"userx_id", "roleuser_id"};

    public RepositoryUserOnRole(ICreateConnection<Connection> connectionSource, GenerateIdType generateIdType) {
        super("user_on_role", COLUMNS_NAME, connectionSource,
                new MapperDtoFactory<ID>().instance(UtilClass.castClass(EntityUserOnRole.class), UtilClass.castClass(DtoUserOnRole.class)),
                new MapperSqlUserOnRole<ID>(COLUMNS_NAME),
                generateIdType);
    }

    @Override
    protected Map<Integer, Object> getValueByColumn(EntityUserOnRole<ID> entity) {
        Map<Integer, Object> valuesByColumn = new LinkedHashMap();
        valuesByColumn.put(0, entity.getUserId());
        valuesByColumn.put(1, entity.getRoleId());
        return valuesByColumn;
    }

    @Override
    protected DtoUserOnRole<ID> fill(DtoUserOnRole<ID> dto){
        IRepository<ID, DtoUser<ID>> repositoryUser = null;
        IRepository<ID, DtoRoleuser<ID>> repositoryRoleuser = null;
        try {
            // Добавление пользователя
            repositoryUser = new RepositoryUser(getConnectionSource(),
                    new MapperDtoFactory().instance(UtilClass.castClass(EntityUser.class), UtilClass.castClass(DtoUser.class)),
                    GenerateIdType.NONE);
            dto.setUser(repositoryUser.read(dto.getUserId()));

            // Добавление роли пользователя
            repositoryRoleuser = new RepositoryRoleuser(getConnectionSource(), GenerateIdType.NONE);
            dto.setRole(repositoryRoleuser.read(dto.getRoleId()));
        }finally {
            if(repositoryUser != null){
                repositoryUser.close();
            }
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
