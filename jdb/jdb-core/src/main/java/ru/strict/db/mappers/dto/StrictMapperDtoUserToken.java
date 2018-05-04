package ru.strict.db.mappers.dto;

import ru.strict.db.dto.*;
import ru.strict.db.entities.StrictEntityProfile;
import ru.strict.db.entities.StrictEntityRoleuser;
import ru.strict.db.entities.StrictEntityUser;

/**
 * Двухсторонний маппинг объектов типа StrictEntityUser и StrictDtoUserToken
 */
public class StrictMapperDtoUserToken<E extends StrictEntityUser, DTO extends StrictDtoUserToken>
        extends StrictMapperDtoUserBase<E, DTO> {

    public StrictMapperDtoUserToken(){
        super();
    }

    public StrictMapperDtoUserToken(StrictMapperDtoBase<StrictEntityRoleuser, StrictDtoRoleuser> mapperRoleuser
            , StrictMapperDtoBase<StrictEntityProfile, StrictDtoProfile> mapperProfile){
        super(mapperRoleuser, mapperProfile);
    }

    @Override
    protected StrictEntityUser implementMap(StrictDtoUserToken dto) {
        StrictEntityUser baseEntity = super.implementMap(dto);

        StrictEntityUser entity = new StrictEntityUser();
        entity.setId(baseEntity.getId());
        entity.setUsername(baseEntity.getUsername());
        entity.setRolesuser(baseEntity.getRolesuser());
        entity.setToken(dto.getToken());
        return entity;
    }

    @Override
    protected StrictDtoUserToken implementMap(StrictEntityUser entity) {
        StrictDtoUserBase baseDto = super.implementMap(entity);

        StrictDtoUserToken dto = new StrictDtoUserToken();
        dto.setId(baseDto.getId());
        dto.setUsername(baseDto.getUsername());
        entity.setRolesuser(baseDto.getRolesuser());
        dto.setToken(entity.getToken());
        return dto;
    }
}
