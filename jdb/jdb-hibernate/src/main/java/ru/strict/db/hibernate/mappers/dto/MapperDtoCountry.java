package ru.strict.db.hibernate.mappers.dto;

import ru.strict.models.City;
import ru.strict.models.Country;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.hibernate.entities.EntityCity;
import ru.strict.db.hibernate.entities.EntityCountry;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа EntityCountry и DtoCountry
 */
public class MapperDtoCountry extends MapperDtoBase<Long, EntityCountry, Country<Long>> {

    private MapperDtoBase<Long, EntityCity, City<Long>> mapperCity;

    public MapperDtoCountry(){
        mapperCity = null;
    }

    public MapperDtoCountry(MapperDtoBase<Long, EntityCity, City<Long>> mapperCity){
        this.mapperCity = mapperCity;
    }

    @Override
    protected EntityCountry implementMap(Country<Long> dto) {
        EntityCountry entity = new EntityCountry();
        entity.setId(dto.getId());
        entity.setCaption(dto.getCaption());
        Optional.ofNullable(mapperCity).ifPresent((mapper) ->
                dto.getCities().stream().forEach((r) -> entity.addCity(mapper.map(r))));
        return entity;
    }

    @Override
    protected Country<Long> implementMap(EntityCountry entity) {
        Country<Long> dto = new Country();
        dto.setId(entity.getId());
        dto.setCaption(entity.getCaption());
        Optional.ofNullable(mapperCity).ifPresent((mapper) ->
                entity.getCities().stream().forEach((r) -> dto.addCity(mapper.map(r))));
        return dto;
    }
}
