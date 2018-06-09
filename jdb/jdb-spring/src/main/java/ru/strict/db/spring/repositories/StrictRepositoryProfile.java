package ru.strict.db.spring.repositories;

import ru.strict.db.core.connections.StrictCreateConnectionByDataSource;
import ru.strict.db.core.dto.StrictDtoProfile;
import ru.strict.db.core.dto.StrictDtoUser;
import ru.strict.db.core.entities.StrictEntityProfile;
import ru.strict.db.core.mappers.dto.StrictMapperDtoFactory;
import ru.strict.db.core.repositories.IStrictRepository;
import ru.strict.db.spring.mappers.sql.StrictMapperSqlProfile;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Репозиторий таблицы "profile". Определяет столбцы: "name", "surname", "middlename", "user_id"
 * @param <ID> Тип идентификатора
 */
public class StrictRepositoryProfile<ID>
        extends StrictRepositorySpringBase<ID, StrictEntityProfile, StrictDtoProfile> {

    private static final String[] COLUMNS_NAME = new String[] {"name", "surname", "middlename", "user_id"};

    public StrictRepositoryProfile(StrictCreateConnectionByDataSource connectionSource
            , boolean isGenerateId) {
        super("profile", COLUMNS_NAME, connectionSource
                , StrictMapperDtoFactory.createMapperProfile()
                , isGenerateId,
                new StrictMapperSqlProfile(COLUMNS_NAME));
    }

    @Override
    protected Map<Integer, Object> getValueByColumn(StrictDtoProfile dto){
        Map<Integer, Object> valuesByColumn = new LinkedHashMap();
        valuesByColumn.put(0, dto.getName());
        valuesByColumn.put(1, dto.getSurname());
        valuesByColumn.put(2, dto.getMiddlename());
        valuesByColumn.put(3, dto.getUserId());
        return valuesByColumn;
    }

    @Override
    protected StrictDtoProfile fill(StrictDtoProfile dto){
        IStrictRepository<ID, StrictDtoUser> rUser =
                new StrictRepositoryUser(getConnectionSource()
                        , StrictMapperDtoFactory.createMapperUser()
                        , false);
            dto.setUser(rUser.read((ID) dto.getUserId()));
        return dto;
    }
}
