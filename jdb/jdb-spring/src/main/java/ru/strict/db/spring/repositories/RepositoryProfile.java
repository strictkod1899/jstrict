package ru.strict.db.spring.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.connections.CreateConnectionByDataSource;
import ru.strict.db.core.dto.DtoProfile;
import ru.strict.db.core.dto.DtoUser;
import ru.strict.db.core.entities.EntityProfile;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.spring.mappers.sql.MapperSqlProfile;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Репозиторий таблицы "profile". Определяет столбцы: "name", "surname", "middlename", "user_id"
 * @param <ID> Тип идентификатора
 */
public class RepositoryProfile<ID>
        extends RepositorySpringBase<ID, EntityProfile<ID>, DtoProfile<ID>> {

    private static final String[] COLUMNS_NAME = new String[] {"name", "surname", "middlename", "userx_id"};

    public RepositoryProfile(CreateConnectionByDataSource connectionSource, GenerateIdType generateIdType) {
        super("profile", COLUMNS_NAME, connectionSource,
                new MapperDtoFactory<ID, EntityProfile<ID>, DtoProfile<ID>>().instance(MapperDtoType.PROFILE),
                new MapperSqlProfile<ID>(COLUMNS_NAME),
                generateIdType);
    }

    @Override
    protected Map<Integer, Object> getValueByColumn(EntityProfile<ID> entity){
        Map<Integer, Object> valuesByColumn = new LinkedHashMap();
        valuesByColumn.put(0, entity.getName());
        valuesByColumn.put(1, entity.getSurname());
        valuesByColumn.put(2, entity.getMiddlename());
        valuesByColumn.put(3, entity.getUserId());
        return valuesByColumn;
    }

    @Override
    protected DtoProfile<ID> fill(DtoProfile<ID> dto){
        IRepository<ID, DtoUser<ID>> repositoryUser =
                new RepositoryUser(getConnectionSource(),
                        new MapperDtoFactory().instance(MapperDtoType.USER),
                        GenerateIdType.NONE);
        dto.setUser(repositoryUser.read(dto.getUserId()));
        return dto;
    }

    @Override
    protected Class getThisClass() {
        return this.getClass();
    }
}
