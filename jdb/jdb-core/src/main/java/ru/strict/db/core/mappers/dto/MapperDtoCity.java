package ru.strict.db.core.mappers.dto;

import ru.strict.models.City;
import ru.strict.models.Country;
import ru.strict.db.core.entities.EntityCity;
import ru.strict.db.core.entities.EntityCountry;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа EntityCity и DtoCity
 */
public class MapperDtoCity<ID> extends MapperDtoBase<ID, EntityCity<ID>, City<ID>> {

    private MapperDtoBase<ID, EntityCountry<ID>, Country<ID>> mapperCountry;

    public MapperDtoCity(){
        mapperCountry = null;
    }

    public MapperDtoCity(MapperDtoBase<ID, EntityCountry<ID>, Country<ID>> mapperCountry){
        this.mapperCountry = mapperCountry;
    }

    @Override
    protected EntityCity<ID> implementMap(City<ID> dto) {
        EntityCity<ID> entity = new EntityCity();
        entity.setId(dto.getId());
        entity.setCaption(dto.getCaption());
        entity.setCountryId(dto.getCountryId());
        Optional.ofNullable(mapperCountry).ifPresent((mapper) -> entity.setCountry(mapper.map(dto.getCountry())));
        return entity;
    }

    @Override
    protected City<ID> implementMap(EntityCity<ID> entity) {
        City<ID> dto = new City();
        dto.setId(entity.getId());
        dto.setCaption(entity.getCaption());
        dto.setCountryId(entity.getCountryId());
        Optional.ofNullable(mapperCountry).ifPresent((mapper) -> dto.setCountry(mapper.map(entity.getCountry())));
        return dto;
    }
}
