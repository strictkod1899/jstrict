package ru.strict.db.mappers;

import ru.strict.db.dto.StrictDtoNamed;
import ru.strict.db.entities.StrictEntityNamed;

public class StrictMapperNamed extends StrictBaseMapper<StrictEntityNamed, StrictDtoNamed>{

    @Override
    protected StrictEntityNamed implementMap(StrictDtoNamed dto) {
        StrictEntityNamed entity = new StrictEntityNamed();
        entity.setId(dto.getId());
        entity.setCaption(dto.getCaption());
        return entity;
    }

    @Override
    protected StrictDtoNamed implementMap(StrictEntityNamed entity) {
        StrictDtoNamed dto = new StrictDtoNamed();
        dto.setId(entity.getId());
        dto.setCaption(entity.getCaption());
        return dto;
    }
}
