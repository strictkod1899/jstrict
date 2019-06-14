package ru.strict.db.core.mappers.dto;

import ru.strict.models.Roleuser;
import ru.strict.models.User;
import ru.strict.models.UserOnRole;
import ru.strict.db.core.entities.EntityRoleuser;
import ru.strict.db.core.entities.EntityUser;
import ru.strict.db.core.entities.EntityUserOnRole;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа EntityUserOnRole и DtoUserOnRole
 */
public class MapperDtoUserOnRole<ID> extends MapperDtoBase<ID, EntityUserOnRole<ID>, UserOnRole<ID>> {

    private MapperDtoBase<ID, EntityUser<ID>, User<ID>> mapperUser;
    private MapperDtoBase<ID, EntityRoleuser<ID>, Roleuser<ID>> mapperRoleuser;

    public MapperDtoUserOnRole(){
        this.mapperUser = null;
        this.mapperRoleuser = null;
    }

    public MapperDtoUserOnRole(MapperDtoBase<ID, EntityUser<ID>, User<ID>> mapperUser
            , MapperDtoBase<ID, EntityRoleuser<ID>, Roleuser<ID>> mapperRoleuser){
        this.mapperUser = mapperUser;
        this.mapperRoleuser = mapperRoleuser;
    }

    @Override
    protected EntityUserOnRole<ID> implementMap(UserOnRole<ID> dto) {
        EntityUserOnRole<ID> entity = new EntityUserOnRole();
        entity.setId(dto.getId());
        entity.setRoleId(dto.getRoleId());
        Optional.ofNullable(mapperRoleuser).ifPresent((mapper) -> entity.setRole(mapper.map(dto.getRole())));
        entity.setUserId(dto.getUserId());
        Optional.ofNullable(mapperUser).ifPresent((mapper) -> entity.setUser(mapper.map(dto.getUser())));
        return entity;
    }

    @Override
    protected UserOnRole<ID> implementMap(EntityUserOnRole<ID> entity) {
        UserOnRole<ID> dto = new UserOnRole();
        dto.setId(entity.getId());
        dto.setRoleId(entity.getRoleId());
        Optional.ofNullable(mapperRoleuser).ifPresent((mapper) -> dto.setRole(mapper.map(entity.getRole())));
        dto.setUserId(entity.getUserId());
        Optional.ofNullable(mapperUser).ifPresent((mapper) -> dto.setUser(mapper.map(entity.getUser())));
        return dto;
    }
}
