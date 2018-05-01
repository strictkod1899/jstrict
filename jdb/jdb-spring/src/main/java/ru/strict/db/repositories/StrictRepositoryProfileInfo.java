package ru.strict.db.repositories;

import ru.strict.db.connections.StrictCreateConnectionByDataSource;
import ru.strict.db.dto.StrictDtoProfile;
import ru.strict.db.dto.StrictDtoProfileInfo;
import ru.strict.db.dto.StrictDtoUser;
import ru.strict.db.entities.StrictEntityProfileInfo;
import ru.strict.db.mappers.dto.StrictMapperDtoProfile;
import ru.strict.db.mappers.dto.StrictMapperDtoProfileInfo;
import ru.strict.db.mappers.dto.StrictMapperDtoRoleuser;
import ru.strict.db.mappers.dto.StrictMapperDtoUser;
import ru.strict.db.mappers.spring.StrictMapperSqlProfileInfo;

import java.util.LinkedHashMap;
import java.util.Map;

public class StrictRepositoryProfileInfo<ID>
        extends StrictRepositorySpringBase<ID, StrictEntityProfileInfo, StrictDtoProfileInfo> {

    private static final String[] COLUMNS_NAME = new String[] {"name", "surname", "middlename", "user_id", "datebirth",
            "phone", "country", "city", "address"};

    public StrictRepositoryProfileInfo(StrictCreateConnectionByDataSource connectionSource, boolean isGenerateId) {
        super("profileinfo", COLUMNS_NAME, connectionSource
                , new StrictMapperDtoProfileInfo(new StrictMapperDtoUser(new StrictMapperDtoRoleuser()))
                , isGenerateId,
                new StrictMapperSqlProfileInfo(COLUMNS_NAME));
    }

    @Override
    protected Map<Integer, Object> getValueByColumn(StrictDtoProfileInfo dto){
        Map<Integer, Object> valuesByColumn = new LinkedHashMap();
        valuesByColumn.put(0, dto.getName());
        valuesByColumn.put(1, dto.getSurname());
        valuesByColumn.put(2, dto.getMiddlename());
        valuesByColumn.put(3, dto.getUserId());
        valuesByColumn.put(4, dto.getDateBirth());
        valuesByColumn.put(5, dto.getPhone());
        valuesByColumn.put(6, dto.getCountry());
        valuesByColumn.put(7, dto.getCity());
        valuesByColumn.put(8, dto.getAddress());
        return valuesByColumn;
    }

    @Override
    protected StrictDtoProfileInfo fill(StrictDtoProfileInfo dto){
        StrictRepositoryAny<ID, StrictDtoUser> rUser =
                new StrictRepositoryUser<>(getConnectionSource()
                        , new StrictMapperDtoUser(new StrictMapperDtoRoleuser())
                        , false);
        dto.setUser(rUser.read((ID) dto.getUserId()));
        return dto;
    }
}