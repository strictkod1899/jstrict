package ru.strict.db.mappers.dto;

import ru.strict.db.dto.StrictDtoRoleuser;
import ru.strict.db.dto.StrictDtoUser;
import ru.strict.db.dto.StrictDtoUserOnRole;
import ru.strict.db.entities.StrictEntityRoleuser;
import ru.strict.db.entities.StrictEntityUser;
import ru.strict.db.entities.StrictEntityUserOnRole;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа StrictEntityUserOnRole и StrictDtoUserOnRole
 */
public class StrictMapperDtoUserOnRole
        extends StrictMapperDtoBase<StrictEntityUserOnRole, StrictDtoUserOnRole> {

    private Optional<StrictMapperDtoBase<StrictEntityUser, StrictDtoUser>> mapperUser;
    private Optional<StrictMapperDtoBase<StrictEntityRoleuser, StrictDtoRoleuser>> mapperRoleuser;

    public StrictMapperDtoUserOnRole(){
        this.mapperUser = Optional.empty();
        this.mapperRoleuser = Optional.empty();
    }

    public StrictMapperDtoUserOnRole(StrictMapperDtoBase<StrictEntityUser, StrictDtoUser> mapperUser
            , StrictMapperDtoBase<StrictEntityRoleuser, StrictDtoRoleuser> mapperRoleuser){
        this.mapperUser = Optional.ofNullable(mapperUser);
        this.mapperRoleuser = Optional.ofNullable(mapperRoleuser);
    }

    @Override
    protected StrictEntityUserOnRole implementMap(StrictDtoUserOnRole dto) {
        StrictEntityUserOnRole entity = new StrictEntityUserOnRole();
        entity.setId(dto.getId());
        entity.setRoleId(dto.getRoleId());
        mapperRoleuser.ifPresent((mapper) -> entity.setRole(mapper.map(dto.getRole())));
        entity.setUserId(dto.getUserId());
        mapperUser.ifPresent((mapper) -> entity.setUser(mapper.map(dto.getUser())));
        return entity;
    }

    @Override
    protected StrictDtoUserOnRole implementMap(StrictEntityUserOnRole entity) {
        StrictDtoUserOnRole dto = new StrictDtoUserOnRole();
        dto.setId(entity.getId());
        dto.setRoleId(entity.getRoleId());
        mapperRoleuser.ifPresent((mapper) -> dto.setRole(mapper.map(entity.getRole())));
        dto.setUserId(entity.getUserId());
        mapperUser.ifPresent((mapper) -> dto.setUser(mapper.map(entity.getUser())));
        return dto;
    }
}
