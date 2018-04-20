package ru.strict.db.mappers;

import ru.strict.db.dto.StrictDtoProfileInfo;
import ru.strict.db.entities.StrictEntityProfileInfo;

public class StrictMapperProfileInfo extends StrictMapperBase<StrictEntityProfileInfo, StrictDtoProfileInfo> {

    @Override
    protected StrictEntityProfileInfo implementMap(StrictDtoProfileInfo dto) {
        StrictEntityProfileInfo entity = new StrictEntityProfileInfo();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setMiddlename(dto.getMiddlename());
        entity.setDateBirth(dto.getDateBirth());
        entity.setPhone(dto.getPhone());
        entity.setCounrty(dto.getCounrty());
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
        dto.setDateBirth(entity.getDateBirth());
        dto.setPhone(entity.getPhone());
        dto.setCounrty(entity.getCounrty());
        dto.setCity(entity.getCity());
        dto.setAddress(entity.getAddress());
        return dto;
    }
}
