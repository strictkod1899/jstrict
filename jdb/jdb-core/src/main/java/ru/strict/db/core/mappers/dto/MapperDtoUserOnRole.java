package ru.strict.db.core.mappers.dto;

import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.core.dto.DtoUser;
import ru.strict.db.core.dto.DtoUserOnRole;
import ru.strict.db.core.entities.EntityRoleuser;
import ru.strict.db.core.entities.EntityUser;
import ru.strict.db.core.entities.EntityUserOnRole;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа EntityUserOnRole и DtoUserOnRole
 */
public class MapperDtoUserOnRole
        extends MapperDtoBase<EntityUserOnRole, DtoUserOnRole> {

    private MapperDtoBase<EntityUser, DtoUser> mapperUser;
    private MapperDtoBase<EntityRoleuser, DtoRoleuser> mapperRoleuser;

    public MapperDtoUserOnRole(){
        this.mapperUser = null;
        this.mapperRoleuser = null;
    }

    public MapperDtoUserOnRole(MapperDtoBase<EntityUser, DtoUser> mapperUser
            , MapperDtoBase<EntityRoleuser, DtoRoleuser> mapperRoleuser){
        this.mapperUser = mapperUser;
        this.mapperRoleuser = mapperRoleuser;
    }

    @Override
    protected EntityUserOnRole implementMap(DtoUserOnRole dto) {
        EntityUserOnRole entity = new EntityUserOnRole();
        entity.setId(dto.getId());
        entity.setRoleId(dto.getRoleId());
        Optional.ofNullable(mapperRoleuser).ifPresent((mapper) -> entity.setRole(mapper.map(dto.getRole())));
        entity.setUserId(dto.getUserId());
        Optional.ofNullable(mapperUser).ifPresent((mapper) -> entity.setUser(mapper.map(dto.getUser())));
        return entity;
    }

    @Override
    protected DtoUserOnRole implementMap(EntityUserOnRole entity) {
        DtoUserOnRole dto = new DtoUserOnRole();
        dto.setId(entity.getId());
        dto.setRoleId(entity.getRoleId());
        Optional.ofNullable(mapperRoleuser).ifPresent((mapper) -> dto.setRole(mapper.map(entity.getRole())));
        dto.setUserId(entity.getUserId());
        Optional.ofNullable(mapperUser).ifPresent((mapper) -> dto.setUser(mapper.map(entity.getUser())));
        return dto;
    }
}
