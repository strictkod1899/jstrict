package ru.strict.db.hibernate.mappers.dto;

import ru.strict.db.core.dto.DtoCity;
import ru.strict.db.core.dto.DtoCountry;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.hibernate.entities.EntityCity;
import ru.strict.db.hibernate.entities.EntityCountry;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

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
        entity.setId((UUID)dto.getId());
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
