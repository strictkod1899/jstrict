package ru.strict.db.hibernate.mappers.dto;

import ru.strict.db.core.dto.DtoCity;
import ru.strict.db.core.dto.DtoProfile;
import ru.strict.db.core.dto.DtoProfileInfo;
import ru.strict.db.hibernate.entities.EntityCity;
import ru.strict.db.hibernate.entities.EntityProfile;
import ru.strict.db.hibernate.entities.EntityProfileBase;
import ru.strict.db.hibernate.entities.EntityProfileInfo;
import ru.strict.db.core.mappers.dto.MapperDtoBase;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа EntityProfileInfo и DtoProfileInfo
 */
public class MapperDtoProfileInfo extends MapperDtoBase<Long, EntityProfileInfo, DtoProfileInfo<Long>> {

    private MapperDtoBase<Long, EntityProfileBase, DtoProfile<Long>> mapperBase;
    private MapperDtoBase<Long, EntityCity, DtoCity<Long>> mapperCity;

    public MapperDtoProfileInfo(MapperDtoBase<Long, EntityProfileBase, DtoProfile<Long>> mapperBase){
        super();
        mapperCity = null;
        this.mapperBase = mapperBase;
    }

    public MapperDtoProfileInfo(MapperDtoBase<Long, EntityProfileBase, DtoProfile<Long>> mapperBase,
                                MapperDtoBase<Long, EntityCity, DtoCity<Long>> mapperCity){
        this.mapperBase = mapperBase;
        this.mapperCity = mapperCity;
    }

    @Override
    protected EntityProfileInfo implementMap(DtoProfileInfo<Long> dto) {
        EntityProfileBase baseEntity = mapperBase.map(dto);

        EntityProfileInfo entity = new EntityProfileInfo();
        entity.setId(baseEntity.getId());
        entity.setName(baseEntity.getName());
        entity.setSurname(baseEntity.getSurname());
        entity.setMiddlename(baseEntity.getMiddlename());
        entity.setUserId(baseEntity.getUserId());
        entity.setUser(baseEntity.getUser());
        entity.setDateBirth(dto.getDateBirth());
        entity.setPhone(dto.getPhone());
        entity.setCityId(dto.getCityId());
        Optional.ofNullable(mapperCity).ifPresent((mapper) -> entity.setCity(mapper.map(dto.getCity())));
        return entity;
    }

    @Override
    protected DtoProfileInfo<Long> implementMap(EntityProfileInfo entity) {
        DtoProfile<Long> baseDto = mapperBase.map(entity);

        DtoProfileInfo<Long> dto = new DtoProfileInfo();
        dto.setId(baseDto.getId());
        dto.setName(baseDto.getName());
        dto.setSurname(baseDto.getSurname());
        dto.setMiddlename(baseDto.getMiddlename());
        dto.setUserId(baseDto.getUserId());
        dto.setUser(baseDto.getUser());
        dto.setDateBirth(entity.getDateBirth());
        dto.setPhone(entity.getPhone());
        dto.setCityId(entity.getCityId());
        Optional.ofNullable(mapperCity).ifPresent((mapper) -> dto.setCity(mapper.map(entity.getCity())));
        return dto;
    }
}
