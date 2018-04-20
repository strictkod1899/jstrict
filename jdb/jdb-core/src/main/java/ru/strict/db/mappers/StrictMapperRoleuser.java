package ru.strict.db.mappers;

import ru.strict.db.dto.StrictDtoRoleuser;
import ru.strict.db.entities.StrictEntityRoleuser;

public class StrictMapperRoleuser extends StrictBaseMapper<StrictEntityRoleuser, StrictDtoRoleuser>{

    @Override
    protected StrictEntityRoleuser implementMap(StrictDtoRoleuser dto) {
        StrictEntityRoleuser entity = new StrictEntityRoleuser();
        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());
        entity.setSymbols(dto.getSymbols());
        return entity;
    }

    @Override
    protected StrictDtoRoleuser implementMap(StrictEntityRoleuser entity) {
        StrictDtoRoleuser dto = new StrictDtoRoleuser();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setSymbols(entity.getSymbols());
        return dto;
    }
}
