package ru.strict.db.mappers.dto;

import ru.strict.db.dto.StrictDtoProfileInfo;
import ru.strict.db.dto.StrictDtoUser;
import ru.strict.db.entities.StrictEntityProfile;
import ru.strict.db.entities.StrictEntityProfileInfo;
import ru.strict.db.entities.StrictEntityUser;
import ru.strict.db.mappers.StrictMapperBase;

/**
 * Двухсторонний маппинг объектов типа StrictEntityProfileInfo и StrictDtoProfileInfo
 */
public class StrictMapperDtoProfileInfo extends StrictMapperDtoBase<StrictEntityProfileInfo, StrictDtoProfileInfo> {

    private StrictMapperDtoUser mapperUser;

    public StrictMapperDtoProfileInfo(StrictMapperDtoUser mapperUser){
        this.mapperUser = mapperUser;
    }

    @Override
    protected StrictEntityProfileInfo implementMap(StrictDtoProfileInfo dto) {
        StrictEntityProfileInfo entity = new StrictEntityProfileInfo();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setMiddlename(dto.getMiddlename());
        entity.setUserId(dto.getUserId());
        entity.setUser((StrictEntityUser) mapperUser.map(dto.getUser()));
        entity.setDateBirth(dto.getDateBirth());
        entity.setPhone(dto.getPhone());
        entity.setCountry(dto.getCountry());
        entity.setCity(dto.getCity());
        entity.setAddress(dto.getAddress());
        return entity;
    }

    @Override
    protected StrictDtoProfileInfo implementMap(StrictEntityProfileInfo entity) {
        StrictDtoProfileInfo dto = new StrictDtoProfileInfo();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setMiddlename(entity.getMiddlename());
        dto.setUserId(entity.getUserId());
        dto.setUser((StrictDtoUser) mapperUser.map(entity.getUser()));
        dto.setDateBirth(entity.getDateBirth());
        dto.setPhone(entity.getPhone());
        dto.setCountry(entity.getCountry());
        dto.setCity(entity.getCity());
        dto.setAddress(entity.getAddress());
        return dto;
    }
}
