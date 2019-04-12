package ru.strict.db.hibernate.mappers.dto;

import ru.strict.db.core.dto.DtoCity;
import ru.strict.db.core.dto.DtoCountry;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.hibernate.entities.EntityCity;
import ru.strict.db.hibernate.entities.EntityCountry;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа EntityCountry и DtoCountry
 */
public class MapperDtoCountry extends MapperDtoBase<Long, EntityCountry, DtoCountry<Long>> {

    private MapperDtoBase<Long, EntityCity, DtoCity<Long>> mapperCity;

    public MapperDtoCountry(){
        mapperCity = null;
    }

    public MapperDtoCountry(MapperDtoBase<Long, EntityCity, DtoCity<Long>> mapperCity){
        this.mapperCity = mapperCity;
    }

    @Override
    protected EntityCountry implementMap(DtoCountry<Long> dto) {
        EntityCountry entity = new EntityCountry();
        entity.setId(dto.getId());
        entity.setCaption(dto.getCaption());
        Optional.ofNullable(mapperCity).ifPresent((mapper) ->
                dto.getCities().stream().forEach((r) -> entity.addCity(mapper.map(r))));
        return entity;
    }

    @Override
    protected DtoCountry<Long> implementMap(EntityCountry entity) {
        DtoCountry<Long> dto = new DtoCountry();
        dto.setId(entity.getId());
        dto.setCaption(entity.getCaption());
        Optional.ofNullable(mapperCity).ifPresent((mapper) ->
                entity.getCities().stream().forEach((r) -> dto.addCity(mapper.map(r))));
        return dto;
    }
}
