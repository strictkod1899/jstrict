package ru.strict.db.spring.security;

import ru.strict.db.core.dto.StrictDtoRoleuser;
import ru.strict.db.core.entities.StrictEntityRoleuser;
import ru.strict.db.core.mappers.dto.StrictMapperDtoBase;
import ru.strict.db.core.mappers.dto.StrictMapperDtoRoleuser;

/**
 * Двухсторонний маппинг объектов типа StrictEntityUserSecurity и StrictDtoUserSecurity
 */
public class StrictMapperDtoUserSecurity extends StrictMapperDtoBase<StrictEntityUserSecurity, StrictDtoUserSecurity> {

    private StrictMapperDtoRoleuser mapperRoleuser;

    public StrictMapperDtoUserSecurity(StrictMapperDtoRoleuser mapperRoleuser){
        this.mapperRoleuser = mapperRoleuser;
    }

    @Override
    protected StrictEntityUserSecurity implementMap(StrictDtoUserSecurity dto) {
        StrictEntityUserSecurity entity = new StrictEntityUserSecurity();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        dto.getRolesuser().stream().forEach(r -> entity.addRoleuser(mapperRoleuser.map((StrictDtoRoleuser) r)));
        entity.setToken(dto.getToken());
        entity.setPasswordEncode(dto.getPasswordEncode());
        return entity;
    }

    @Override
    protected StrictDtoUserSecurity implementMap(StrictEntityUserSecurity entity) {
        StrictDtoUserSecurity dto = new StrictDtoUserSecurity();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        entity.getRolesuser().stream().forEach(r -> dto.addRoleuser(mapperRoleuser.map((StrictEntityRoleuser) r)));
        dto.setToken(entity.getToken());
        dto.setPasswordEncode(entity.getPasswordEncode());
        return dto;
    }
}
