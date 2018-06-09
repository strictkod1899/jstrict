package ru.strict.db.core.mappers.dto;

import ru.strict.db.core.dto.StrictDtoProfile;
import ru.strict.db.core.dto.StrictDtoRoleuser;
import ru.strict.db.core.dto.StrictDtoUserBase;
import ru.strict.db.core.dto.StrictDtoUserToken;
import ru.strict.db.core.entities.StrictEntityProfile;
import ru.strict.db.core.entities.StrictEntityRoleuser;
import ru.strict.db.core.entities.StrictEntityUser;

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
