package ru.strict.db.mappers.dto;

import ru.strict.db.dto.StrictDtoRoleuser;
import ru.strict.db.dto.StrictDtoUser;
import ru.strict.db.entities.StrictEntityRoleuser;
import ru.strict.db.entities.StrictEntityUser;

/**
 * Двухсторонний маппинг объектов типа StrictEntityUser и StrictDtoUser
 */
public class StrictMapperDtoUser extends StrictMapperDtoBase<StrictEntityUser, StrictDtoUser> {

    private StrictMapperDtoRoleuser mapperRoleuser;

    public StrictMapperDtoUser(StrictMapperDtoRoleuser mapperRoleuser){
        this.mapperRoleuser = mapperRoleuser;
    }

    @Override
    protected StrictEntityUser implementMap(StrictDtoUser dto) {
        StrictEntityUser entity = new StrictEntityUser();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        dto.getRolesuser().stream().forEach(r -> entity.addRoleuser(mapperRoleuser.map((StrictDtoRoleuser) r)));
        entity.setToken(dto.getToken());
        entity.setPasswordEncode(dto.getPasswordEncode());
        return entity;
    }

    @Override
    protected StrictDtoUser implementMap(StrictEntityUser entity) {
        StrictDtoUser dto = new StrictDtoUser();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        entity.getRolesuser().stream().forEach(r -> dto.addRoleuser(mapperRoleuser.map((StrictEntityRoleuser) r)));
        dto.setToken(entity.getToken());
        dto.setPasswordEncode(entity.getPasswordEncode());
        return dto;
    }
}
