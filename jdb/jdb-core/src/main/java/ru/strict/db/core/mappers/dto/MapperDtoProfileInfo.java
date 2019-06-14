package ru.strict.db.core.mappers.dto;

import ru.strict.models.City;
import ru.strict.models.Profile;
import ru.strict.models.ProfileDetails;
import ru.strict.db.core.entities.EntityCity;
import ru.strict.db.core.entities.EntityProfile;
import ru.strict.db.core.entities.EntityProfileInfo;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа EntityProfileInfo и DtoProfileInfo
 */
public class MapperDtoProfileInfo<ID>
        extends MapperDtoBase<ID, EntityProfileInfo<ID>, ProfileDetails<ID>> {

    private MapperDtoBase<ID, EntityProfile<ID>, Profile<ID>> mapperBase;
    private MapperDtoBase<ID, EntityCity<ID>, City<ID>> mapperCity;

    public MapperDtoProfileInfo(MapperDtoBase<ID, EntityProfile<ID>, Profile<ID>> mapperBase){
        super();
        mapperCity = null;
        this.mapperBase = mapperBase;
    }

    public MapperDtoProfileInfo(MapperDtoBase<ID, EntityProfile<ID>, Profile<ID>> mapperBase,
                                MapperDtoBase<ID, EntityCity<ID>, City<ID>> mapperCity){
        this.mapperBase = mapperBase;
        this.mapperCity = mapperCity;
    }

    @Override
    protected EntityProfileInfo<ID> implementMap(ProfileDetails<ID> dto) {
        EntityProfile<ID> baseEntity = mapperBase.map(dto);

        EntityProfileInfo<ID> entity = new EntityProfileInfo();
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
    protected ProfileDetails<ID> implementMap(EntityProfileInfo<ID> entity) {
        Profile<ID> baseDto = mapperBase.map(entity);

        ProfileDetails<ID> dto = new ProfileDetails();
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
