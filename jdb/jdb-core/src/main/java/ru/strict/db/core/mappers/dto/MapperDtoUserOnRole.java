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

    private Optional<MapperDtoBase<EntityUser, DtoUser>> mapperUser;
    private Optional<MapperDtoBase<EntityRoleuser, DtoRoleuser>> mapperRoleuser;

    public MapperDtoUserOnRole(){
        this.mapperUser = Optional.empty();
        this.mapperRoleuser = Optional.empty();
    }

    public MapperDtoUserOnRole(MapperDtoBase<EntityUser, DtoUser> mapperUser
            , MapperDtoBase<EntityRoleuser, DtoRoleuser> mapperRoleuser){
        this.mapperUser = Optional.ofNullable(mapperUser);
        this.mapperRoleuser = Optional.ofNullable(mapperRoleuser);
    }

    @Override
    protected EntityUserOnRole implementMap(DtoUserOnRole dto) {
        EntityUserOnRole entity = new EntityUserOnRole();
        entity.setId(dto.getId());
        entity.setRoleId(dto.getRoleId());
        mapperRoleuser.ifPresent((mapper) -> entity.setRole(mapper.map(dto.getRole())));
        entity.setUserId(dto.getUserId());
        mapperUser.ifPresent((mapper) -> entity.setUser(mapper.map(dto.getUser())));
        return entity;
    }

    @Override
    protected DtoUserOnRole implementMap(EntityUserOnRole entity) {
        DtoUserOnRole dto = new DtoUserOnRole();
        dto.setId(entity.getId());
        dto.setRoleId(entity.getRoleId());
        mapperRoleuser.ifPresent((mapper) -> dto.setRole(mapper.map(entity.getRole())));
        dto.setUserId(entity.getUserId());
        mapperUser.ifPresent((mapper) -> dto.setUser(mapper.map(entity.getUser())));
        return dto;
    }
}
