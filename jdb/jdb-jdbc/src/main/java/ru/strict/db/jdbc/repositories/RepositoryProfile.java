package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;

import ru.strict.db.core.common.SqlParameters;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.dto.DtoProfile;
import ru.strict.db.core.dto.DtoUser;
import ru.strict.db.core.entities.EntityProfile;
import ru.strict.db.core.entities.EntityUser;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.core.repositories.interfaces.IRepositoryProfile;
import ru.strict.db.jdbc.mappers.sql.MapperSqlProfile;
import ru.strict.utils.UtilClass;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Репозиторий таблицы "profile". Определяет столбцы: "name", "surname", "middlename", "userx_id"
 * @param <ID> Тип идентификатора
 */
public class RepositoryProfile<ID>
        extends RepositoryJdbcBase<ID, EntityProfile<ID>, DtoProfile<ID>>
        implements IRepositoryProfile<ID, DtoProfile<ID>> {

    private static final String[] COLUMNS_NAME = new String[] {"name", "surname", "middlename", "userx_id"};

    public RepositoryProfile(ICreateConnection<Connection> connectionSource, GenerateIdType generateIdType) {
        super("profile", COLUMNS_NAME, connectionSource,
                new MapperDtoFactory<ID>().instance(UtilClass.castClass(EntityProfile.class), UtilClass.castClass(DtoProfile.class)),
                new MapperSqlProfile<ID>(COLUMNS_NAME),
                generateIdType);
    }

    @Override
    protected SqlParameters getParameters(EntityProfile<ID> entity){
        SqlParameters parameters = new SqlParameters();
        parameters.add(0, COLUMNS_NAME[0], entity.getName());
        parameters.add(1, COLUMNS_NAME[1], entity.getSurname());
        parameters.add(2, COLUMNS_NAME[2], entity.getMiddlename());
        parameters.add(3, COLUMNS_NAME[3], entity.getUserId());
        return parameters;
    }

    @Override
    protected DtoProfile<ID> fill(DtoProfile<ID> dto){
        IRepository<ID, DtoUser<ID>> repositoryUser = null;
        try {
            repositoryUser = new RepositoryUser(getConnectionSource(),
                    new MapperDtoFactory().instance(UtilClass.castClass(EntityUser.class), UtilClass.castClass(DtoUser.class)),
                    GenerateIdType.NONE);
            dto.setUser(repositoryUser.read(dto.getUserId()));
        }finally {
            if(repositoryUser != null){
                repositoryUser.close();
            }
        }
        return dto;
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
