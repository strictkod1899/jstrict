package ru.strict.db.mappers.dto;

import ru.strict.db.dto.StrictDtoProfile;
import ru.strict.db.dto.StrictDtoRoleuser;
import ru.strict.db.dto.StrictDtoUser;
import ru.strict.db.dto.StrictDtoUserToken;
import ru.strict.db.entities.StrictEntityProfile;
import ru.strict.db.entities.StrictEntityRoleuser;
import ru.strict.db.entities.StrictEntityUser;

/**
 * Двухсторонний маппинг объектов типа StrictEntityUser и StrictDtoUser
 */
public class StrictMapperDtoUser<E extends StrictEntityUser, DTO extends StrictDtoUser>
        extends StrictMapperDtoUserToken<E, DTO> {

    public StrictMapperDtoUser(){
        super();
    }

    public StrictMapperDtoUser(StrictMapperDtoBase<StrictEntityRoleuser, StrictDtoRoleuser> mapperRoleuser
            , StrictMapperDtoBase<StrictEntityProfile, StrictDtoProfile> mapperProfile){
        super(mapperRoleuser, mapperProfile);
    }

    @Override
    protected StrictEntityUser implementMap(StrictDtoUser dto) {
        StrictEntityUser baseEntity = super.implementMap(dto);

        StrictEntityUser entity = new StrictEntityUser();
        entity.setId(baseEntity.getId());
        entity.setUsername(baseEntity.getUsername());
        entity.setRolesuser(baseEntity.getRolesuser());
        entity.setToken(baseEntity.getToken());
        entity.setPasswordEncode(dto.getPasswordEncode());
        return entity;
    }

    @Override
    protected StrictDtoUser implementMap(StrictEntityUser entity) {
        StrictDtoUserToken baseDto = super.implementMap(entity);

        StrictDtoUser dto = new StrictDtoUser();
        dto.setId(baseDto.getId());
        dto.setUsername(baseDto.getUsername());
        entity.setRolesuser(baseDto.getRolesuser());
        dto.setToken(baseDto.getToken());
        dto.setPasswordEncode(entity.getPasswordEncode());
        return dto;
    }
}
