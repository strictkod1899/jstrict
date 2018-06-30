package ru.strict.db.jdbc.repositories;

import ru.strict.db.core.common.GenerateIdType;
import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.connections.ICreateConnection;
import ru.strict.db.core.dto.DtoProfile;
import ru.strict.db.core.dto.DtoUser;
import ru.strict.db.core.entities.EntityProfile;
import ru.strict.db.core.mappers.dto.MapperDtoFactory;
import ru.strict.db.core.repositories.IRepository;
import ru.strict.db.jdbc.mappers.sql.MapperSqlProfile;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Репозиторий таблицы "profile". Определяет столбцы: "name", "surname", "middlename", "user_id"
 * @param <ID> Тип идентификатора
 */
public class RepositoryProfile<ID, SOURCE extends ICreateConnection>
        extends RepositoryJdbcBase<ID, SOURCE, EntityProfile, DtoProfile> {

    private static final String[] COLUMNS_NAME = new String[] {"name", "surname", "middlename", "userx_id"};

    public RepositoryProfile(SOURCE connectionSource, GenerateIdType isGenerateId) {
        super("profile", COLUMNS_NAME, connectionSource,
                new MapperDtoFactory().instance(MapperDtoType.PROFILE),
                new MapperSqlProfile(COLUMNS_NAME),
                isGenerateId);
    }

    @Override
    protected Map<Integer, Object> getValueByColumn(DtoProfile dto){
        Map<Integer, Object> valuesByColumn = new LinkedHashMap();
        valuesByColumn.put(0, dto.getName());
        valuesByColumn.put(1, dto.getSurname());
        valuesByColumn.put(2, dto.getMiddlename());
        valuesByColumn.put(3, dto.getUserId());
        return valuesByColumn;
    }

    @Override
    protected DtoProfile fill(DtoProfile dto){
        IRepository<ID, DtoUser> repositoryUser =
                new RepositoryUser(getConnectionSource(),
                        new MapperDtoFactory().instance(MapperDtoType.USER),
                        GenerateIdType.NONE);
            dto.setUser(repositoryUser.read((ID) dto.getUserId()));
        return dto;
    }
}
