package ru.strict.db.core.mappers.dto;

import ru.strict.db.core.dto.DtoCity;
import ru.strict.db.core.dto.DtoProfile;
import ru.strict.db.core.dto.DtoProfileInfo;
import ru.strict.db.core.dto.DtoUser;
import ru.strict.db.core.entities.EntityCity;
import ru.strict.db.core.entities.EntityProfile;
import ru.strict.db.core.entities.EntityProfileInfo;
import ru.strict.db.core.entities.EntityUser;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа EntityProfileInfo и DtoProfileInfo
 */
public class MapperDtoProfileInfo<E extends EntityProfileInfo, DTO extends DtoProfileInfo>
        extends MapperDtoProfile<E, DTO> {

    private MapperDtoBase<EntityCity, DtoCity> mapperCity;

    public MapperDtoProfileInfo(){
        super();
        mapperCity = null;
    }

    public MapperDtoProfileInfo(MapperDtoBase<EntityUser, DtoUser> mapperUser, MapperDtoBase<EntityCity, DtoCity> mapperCity){
        super(mapperUser);
        this.mapperCity = mapperCity;
    }

    @Override
    protected EntityProfileInfo implementMap(DtoProfileInfo dto) {
        EntityProfile baseEntity = super.implementMap(dto);

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
    protected DtoProfileInfo implementMap(EntityProfileInfo entity) {
        DtoProfile baseDto = super.implementMap(entity);

        DtoProfileInfo dto = new DtoProfileInfo();
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
