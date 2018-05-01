package ru.strict.db.mappers.dto;

import ru.strict.db.dto.StrictDtoRoleuser;
import ru.strict.db.dto.StrictDtoUserToken;
import ru.strict.db.entities.StrictEntityRoleuser;
import ru.strict.db.entities.StrictEntityUser;

/**
 * Двухсторонний маппинг объектов типа StrictEntityUser и StrictDtoUserToken
 */
public class StrictMapperDtoUserToken extends StrictMapperDtoBase<StrictEntityUser, StrictDtoUserToken> {

    private StrictMapperDtoRoleuser mapperRoleuser;

    public StrictMapperDtoUserToken(StrictMapperDtoRoleuser mapperRoleuser){
        this.mapperRoleuser = mapperRoleuser;
    }

    @Override
    protected StrictEntityUser implementMap(StrictDtoUserToken dto) {
        StrictEntityUser entity = new StrictEntityUser();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        dto.getRolesuser().stream().forEach(r -> entity.addRoleuser(mapperRoleuser.map((StrictDtoRoleuser) r)));
        entity.setToken(dto.getToken());
        return entity;
    }

    @Override
    protected StrictDtoUserToken implementMap(StrictEntityUser entity) {
        StrictDtoUserToken dto = new StrictDtoUserToken();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        entity.getRolesuser().stream().forEach(r -> dto.addRoleuser(mapperRoleuser.map((StrictEntityRoleuser) r)));
        dto.setToken(entity.getToken());
        return dto;
    }
}
