package ru.strict.db.repositories;

import ru.strict.db.connections.StrictCreateConnectionByDataSource;
import ru.strict.db.dto.StrictDtoProfile;
import ru.strict.db.dto.StrictDtoRoleuser;
import ru.strict.db.dto.StrictDtoUser;
import ru.strict.db.dto.StrictDtoUserOnRole;
import ru.strict.db.entities.StrictEntityProfile;
import ru.strict.db.entities.StrictEntityUserOnRole;
import ru.strict.db.mappers.dto.StrictMapperDtoProfile;
import ru.strict.db.mappers.dto.StrictMapperDtoRoleuser;
import ru.strict.db.mappers.dto.StrictMapperDtoUser;
import ru.strict.db.mappers.spring.StrictMapperSqlProfile;
import ru.strict.db.requests.StrictDbRequests;
import ru.strict.db.requests.StrictDbWhere;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StrictRepositoryProfile<ID>
        extends StrictRepositorySpringBase<ID, StrictEntityProfile, StrictDtoProfile> {

    private static final String[] COLUMNS_NAME = new String[] {"name", "surname", "middlename", "user_id"};

    public StrictRepositoryProfile(StrictCreateConnectionByDataSource connectionSource, boolean isGenerateId) {
        super("profile", COLUMNS_NAME, connectionSource
                , new StrictMapperDtoProfile(new StrictMapperDtoUser(new StrictMapperDtoRoleuser()))
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
        StrictRepositoryAny<ID, StrictDtoUser> rUser =
                new StrictRepositoryUser<>(getConnectionSource()
                        , new StrictMapperDtoUser(new StrictMapperDtoRoleuser())
                        , false);
            dto.setUser(rUser.read((ID) dto.getUserId()));
        return dto;
    }
}
