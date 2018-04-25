package ru.strict.db.mappers.dto;

import ru.strict.db.dto.StrictDtoRoleuser;
import ru.strict.db.entities.StrictEntityRoleuser;

/**
 * Двухсторонний маппинг объектов типа StrictEntityRoleuser и StrictDtoRoleuser
 */
public class StrictMapperDtoRoleuser extends StrictMapperDtoBase<StrictEntityRoleuser, StrictDtoRoleuser> {

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
