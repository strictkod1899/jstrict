package ru.strict.db.mappers.dto;

import ru.strict.db.dto.StrictDtoProfile;
import ru.strict.db.entities.StrictEntityProfile;

public class StrictMapperDtoProfile extends StrictMapperDtoBase<StrictEntityProfile, StrictDtoProfile> {

    @Override
    protected StrictEntityProfile implementMap(StrictDtoProfile dto) {
        StrictEntityProfile entity = new StrictEntityProfile();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setMiddlename(dto.getMiddlename());
        return entity;
    }

    @Override
    protected StrictDtoProfile implementMap(StrictEntityProfile entity) {
        StrictDtoProfile dto = new StrictDtoProfile();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setMiddlename(entity.getMiddlename());
        return dto;
    }
}
