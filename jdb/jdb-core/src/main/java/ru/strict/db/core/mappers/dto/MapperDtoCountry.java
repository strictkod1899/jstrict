package ru.strict.db.core.mappers.dto;

import ru.strict.models.City;
import ru.strict.models.Country;
import ru.strict.db.core.entities.EntityCity;
import ru.strict.db.core.entities.EntityCountry;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа EntityCountry и DtoCountry
 */
public class MapperDtoCountry<ID> extends MapperDtoBase<ID, EntityCountry<ID>, Country<ID>> {

    private MapperDtoBase<ID, EntityCity<ID>, City<ID>> mapperCity;

    public MapperDtoCountry(){
        mapperCity = null;
    }

    public MapperDtoCountry(MapperDtoBase<ID, EntityCity<ID>, City<ID>> mapperCity){
        this.mapperCity = mapperCity;
    }

    @Override
    protected EntityCountry<ID> implementMap(Country<ID> dto) {
        EntityCountry<ID> entity = new EntityCountry();
        entity.setId(dto.getId());
        entity.setCaption(dto.getCaption());
        Optional.ofNullable(mapperCity).ifPresent((mapper) ->
                dto.getCities().stream().forEach((r) -> entity.addCity(mapper.map(r))));
        return entity;
    }

    @Override
    protected Country<ID> implementMap(EntityCountry<ID> entity) {
        Country<ID> dto = new Country();
        dto.setId(entity.getId());
        dto.setCaption(entity.getCaption());
        Optional.ofNullable(mapperCity).ifPresent((mapper) ->
                entity.getCities().stream().forEach((r) -> dto.addCity(mapper.map(r))));
        return dto;
    }
}
