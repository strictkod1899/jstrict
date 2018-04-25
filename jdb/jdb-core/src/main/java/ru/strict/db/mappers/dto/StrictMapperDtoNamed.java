package ru.strict.db.mappers.dto;

import ru.strict.db.dto.StrictDtoNamed;
import ru.strict.db.entities.StrictEntityNamed;

/**
 * Двухсторонний маппинг объектов типа StrictEntityNamed и StrictDtoNamed
 */
public class StrictMapperDtoNamed extends StrictMapperDtoBase<StrictEntityNamed, StrictDtoNamed> {

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
