package ru.strict.db.hibernate.mappers.dto;

import ru.strict.db.core.dto.DtoCity;
import ru.strict.db.core.dto.DtoCountry;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.hibernate.entities.EntityCity;
import ru.strict.db.hibernate.entities.EntityCountry;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа EntityCity и DtoCity
 */
public class MapperDtoCity extends MapperDtoBase<Long, EntityCity, DtoCity<Long>> {

    private MapperDtoBase<Long, EntityCountry, DtoCountry<Long>> mapperCountry;

    public MapperDtoCity(){
        mapperCountry = null;
    }

    public MapperDtoCity(MapperDtoBase<Long, EntityCountry, DtoCountry<Long>> mapperCountry){
        this.mapperCountry = mapperCountry;
    }

    @Override
    protected EntityCity implementMap(DtoCity<Long> dto) {
        EntityCity entity = new EntityCity();
        entity.setId(dto.getId());
        entity.setCaption(dto.getCaption());
        entity.setCountryId(dto.getCountryId());
        Optional.ofNullable(mapperCountry).ifPresent((mapper) -> entity.setCountry(mapper.map(dto.getCountry())));
        return entity;
    }

    @Override
    protected DtoCity<Long> implementMap(EntityCity entity) {
        DtoCity<Long> dto = new DtoCity();
        dto.setId(entity.getId());
        dto.setCaption(entity.getCaption());
        dto.setCountryId(entity.getCountryId());
        Optional.ofNullable(mapperCountry).ifPresent((mapper) -> dto.setCountry(mapper.map(entity.getCountry())));
        return dto;
    }
}
