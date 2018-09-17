package ru.strict.db.core.mappers.dto;

import ru.strict.db.core.dto.DtoCity;
import ru.strict.db.core.dto.DtoCountry;
import ru.strict.db.core.dto.DtoUser;
import ru.strict.db.core.entities.EntityCity;
import ru.strict.db.core.entities.EntityCountry;
import ru.strict.db.core.entities.EntityUser;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа EntityCity и DtoCity
 */
public class MapperDtoCity<ID> extends MapperDtoBase<ID, EntityCity<ID>, DtoCity<ID>> {

    private MapperDtoBase<ID, EntityCountry<ID>, DtoCountry<ID>> mapperCountry;

    public MapperDtoCity(){
        mapperCountry = null;
    }

    public MapperDtoCity(MapperDtoBase<ID, EntityCountry<ID>, DtoCountry<ID>> mapperCountry){
        this.mapperCountry = mapperCountry;
    }

    @Override
    protected EntityCity<ID> implementMap(DtoCity<ID> dto) {
        EntityCity<ID> entity = new EntityCity();
        entity.setId(dto.getId());
        entity.setCaption(dto.getCaption());
        entity.setCountryId(dto.getCountryId());
        Optional.ofNullable(mapperCountry).ifPresent((mapper) -> entity.setCountry(mapper.map(dto.getCountry())));
        return entity;
    }

    @Override
    protected DtoCity<ID> implementMap(EntityCity<ID> entity) {
        DtoCity<ID> dto = new DtoCity();
        dto.setId(entity.getId());
        dto.setCaption(entity.getCaption());
        dto.setCountryId(entity.getCountryId());
        Optional.ofNullable(mapperCountry).ifPresent((mapper) -> dto.setCountry(mapper.map(entity.getCountry())));
        return dto;
    }
}
