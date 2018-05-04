package ru.strict.db.mappers.dto;

import ru.strict.db.dto.StrictDtoProfile;
import ru.strict.db.dto.StrictDtoProfileInfo;
import ru.strict.db.dto.StrictDtoUser;
import ru.strict.db.entities.StrictEntityProfile;
import ru.strict.db.entities.StrictEntityProfileInfo;
import ru.strict.db.entities.StrictEntityUser;
import ru.strict.db.mappers.StrictMapperBase;

/**
 * Двухсторонний маппинг объектов типа StrictEntityProfileInfo и StrictDtoProfileInfo
 */
public class StrictMapperDtoProfileInfo<E extends StrictEntityProfileInfo, DTO extends StrictDtoProfileInfo>
        extends StrictMapperDtoProfile<E, DTO> {

    public StrictMapperDtoProfileInfo(){
        super();
    }

    public StrictMapperDtoProfileInfo(StrictMapperDtoBase<StrictEntityUser, StrictDtoUser> mapperUser){
        super(mapperUser);
    }

    @Override
    protected StrictEntityProfileInfo implementMap(StrictDtoProfileInfo dto) {
        StrictEntityProfile baseEntity = super.implementMap(dto);

        StrictEntityProfileInfo entity = new StrictEntityProfileInfo();
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
        entity.setAddress(dto.getAddress());
        return entity;
    }

    @Override
    protected StrictDtoProfileInfo implementMap(StrictEntityProfileInfo entity) {
        StrictDtoProfile baseDto = super.implementMap(entity);

        StrictDtoProfileInfo dto = new StrictDtoProfileInfo();
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
        dto.setAddress(entity.getAddress());
        return dto;
    }
}
