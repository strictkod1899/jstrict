package ru.strict.db.core.mappers.dto;

import ru.strict.db.core.dto.DtoCity;
import ru.strict.db.core.dto.DtoCountry;
import ru.strict.db.core.entities.EntityCity;
import ru.strict.db.core.entities.EntityCountry;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа EntityCountry и DtoCountry
 */
public class MapperDtoCountry extends MapperDtoBase<EntityCountry, DtoCountry> {

    private MapperDtoBase<EntityCity, DtoCity> mapperCity;

    public MapperDtoCountry(){
        mapperCity = null;
    }

    public MapperDtoCountry(MapperDtoBase<EntityCity, DtoCity> mapperCity){
        this.mapperCity = mapperCity;
    }

    @Override
    protected EntityCountry implementMap(DtoCountry dto) {
        EntityCountry entity = new EntityCountry();
        entity.setId(dto.getId());
        entity.setCaption(dto.getCaption());
        Optional.ofNullable(mapperCity).ifPresent((mapper) ->
                dto.getCities().stream().forEach((r) -> entity.addCity(mapper.map((DtoCity) r))));
        return entity;
    }

    @Override
    protected DtoCountry implementMap(EntityCountry entity) {
        DtoCountry dto = new DtoCountry();
        dto.setId(entity.getId());
        dto.setCaption(entity.getCaption());
        Optional.ofNullable(mapperCity).ifPresent((mapper) ->
                entity.getCities().stream().forEach((r) -> dto.addCity(mapper.map((EntityCity) r))));
        return dto;
    }
}
