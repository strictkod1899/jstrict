package ru.strict.db.hibernate.mappers.dto;

import ru.strict.db.core.dto.DtoCountry;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.hibernate.entities.EntityCountry;

/**
 * Двухсторонний маппинг объектов типа EntityCountry и DtoCountry
 */
public class MapperDtoCountry extends MapperDtoBase<EntityCountry, DtoCountry> {

    public MapperDtoCountry(){}

    @Override
    protected EntityCountry implementMap(DtoCountry dto) {
        EntityCountry entity = new EntityCountry();
        entity.setId(dto.getId());
        entity.setCaption(dto.getCaption());
        return entity;
    }

    @Override
    protected DtoCountry implementMap(EntityCountry entity) {
        DtoCountry dto = new DtoCountry();
        dto.setId(entity.getId());
        dto.setCaption(entity.getCaption());
        return dto;
    }
}
