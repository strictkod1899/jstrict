package ru.strict.db.hibernate.mappers.dto;

import ru.strict.models.City;
import ru.strict.models.Profile;
import ru.strict.models.ProfileDetails;
import ru.strict.db.hibernate.entities.EntityCity;
import ru.strict.db.hibernate.entities.EntityProfileBase;
import ru.strict.db.hibernate.entities.EntityProfileInfo;
import ru.strict.db.core.mappers.dto.MapperDtoBase;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа EntityProfileInfo и DtoProfileInfo
 */
public class MapperDtoProfileInfo extends MapperDtoBase<Long, EntityProfileInfo, ProfileDetails<Long>> {

    private MapperDtoBase<Long, EntityProfileBase, Profile<Long>> mapperBase;
    private MapperDtoBase<Long, EntityCity, City<Long>> mapperCity;

    public MapperDtoProfileInfo(MapperDtoBase<Long, EntityProfileBase, Profile<Long>> mapperBase){
        super();
        mapperCity = null;
        this.mapperBase = mapperBase;
    }

    public MapperDtoProfileInfo(MapperDtoBase<Long, EntityProfileBase, Profile<Long>> mapperBase,
                                MapperDtoBase<Long, EntityCity, City<Long>> mapperCity){
        this.mapperBase = mapperBase;
        this.mapperCity = mapperCity;
    }

    @Override
    protected EntityProfileInfo implementMap(ProfileDetails<Long> dto) {
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
    protected ProfileDetails<Long> implementMap(EntityProfileInfo entity) {
        Profile<Long> baseDto = mapperBase.map(entity);

        ProfileDetails<Long> dto = new ProfileDetails();
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
