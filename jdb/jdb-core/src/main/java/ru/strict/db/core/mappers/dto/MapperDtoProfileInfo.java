package ru.strict.db.core.mappers.dto;

import ru.strict.db.core.dto.DtoProfile;
import ru.strict.db.core.dto.DtoProfileInfo;
import ru.strict.db.core.dto.DtoUser;
import ru.strict.db.core.entities.EntityProfile;
import ru.strict.db.core.entities.EntityProfileInfo;
import ru.strict.db.core.entities.EntityUser;

/**
 * Двухсторонний маппинг объектов типа EntityProfileInfo и DtoProfileInfo
 */
public class MapperDtoProfileInfo<E extends EntityProfileInfo, DTO extends DtoProfileInfo>
        extends MapperDtoProfile<E, DTO> {

    public MapperDtoProfileInfo(){
        super();
    }

    public MapperDtoProfileInfo(MapperDtoBase<EntityUser, DtoUser> mapperUser){
        super(mapperUser);
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
        entity.setCountry(dto.getCountry());
        entity.setCity(dto.getCity());
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
        dto.setCountry(entity.getCountry());
        dto.setCity(entity.getCity());
        return dto;
    }
}
