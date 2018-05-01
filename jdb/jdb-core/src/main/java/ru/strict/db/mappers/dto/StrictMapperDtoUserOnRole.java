package ru.strict.db.mappers.dto;

import ru.strict.db.dto.StrictDtoRoleuser;
import ru.strict.db.dto.StrictDtoUser;
import ru.strict.db.dto.StrictDtoUserOnRole;
import ru.strict.db.entities.StrictEntityRoleuser;
import ru.strict.db.entities.StrictEntityUser;
import ru.strict.db.entities.StrictEntityUserOnRole;

/**
 * Двухсторонний маппинг объектов типа StrictEntityUserOnRole и StrictDtoUserOnRole
 */
public class StrictMapperDtoUserOnRole extends StrictMapperDtoBase<StrictEntityUserOnRole, StrictDtoUserOnRole> {

    private StrictMapperDtoRoleuser mapperRoleuser;
    private StrictMapperDtoUser mapperUser;

    public StrictMapperDtoUserOnRole(StrictMapperDtoUser mapperUser,  StrictMapperDtoRoleuser mapperRoleuser){
        this.mapperUser = mapperUser;
        this.mapperRoleuser = mapperRoleuser;
    }

    @Override
    protected StrictEntityUserOnRole implementMap(StrictDtoUserOnRole dto) {
        StrictEntityUserOnRole entity = new StrictEntityUserOnRole();
        entity.setId(dto.getId());
        entity.setRoleId(dto.getRoleId());
        entity.setRole(mapperRoleuser.map(dto.getRole()));
        entity.setUserId(dto.getUserId());
        entity.setUser(mapperUser.map(dto.getUser()));
        return entity;
    }

    @Override
    protected StrictDtoUserOnRole implementMap(StrictEntityUserOnRole entity) {
        StrictDtoUserOnRole dto = new StrictDtoUserOnRole();
        dto.setId(entity.getId());
        dto.setRoleId(entity.getRoleId());
        dto.setRole(mapperRoleuser.map(entity.getRole()));
        dto.setUserId(entity.getUserId());
        dto.setUser(mapperUser.map(entity.getUser()));
        return dto;
    }
}
