package ru.strict.db.hibernate.mappers.dto;

import ru.strict.models.City;
import ru.strict.models.Country;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.hibernate.entities.EntityCity;
import ru.strict.db.hibernate.entities.EntityCountry;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа EntityCity и DtoCity
 */
public class MapperDtoCity extends MapperDtoBase<Long, EntityCity, City<Long>> {

    private MapperDtoBase<Long, EntityCountry, Country<Long>> mapperCountry;

    public MapperDtoCity(){
        mapperCountry = null;
    }

    public MapperDtoCity(MapperDtoBase<Long, EntityCountry, Country<Long>> mapperCountry){
        this.mapperCountry = mapperCountry;
    }

    @Override
    protected EntityCity implementMap(City<Long> dto) {
        EntityCity entity = new EntityCity();
        entity.setId(dto.getId());
        entity.setCaption(dto.getCaption());
        entity.setCountryId(dto.getCountryId());
        Optional.ofNullable(mapperCountry).ifPresent((mapper) -> entity.setCountry(mapper.map(dto.getCountry())));
        return entity;
    }

    @Override
    protected City<Long> implementMap(EntityCity entity) {
        City<Long> dto = new City();
        dto.setId(entity.getId());
        dto.setCaption(entity.getCaption());
        dto.setCountryId(entity.getCountryId());
        Optional.ofNullable(mapperCountry).ifPresent((mapper) -> dto.setCountry(mapper.map(entity.getCountry())));
        return dto;
    }
}
