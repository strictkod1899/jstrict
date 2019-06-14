package ru.strict.db.hibernate.mappers.dto;

import ru.strict.models.Roleuser;
import ru.strict.models.User;
import ru.strict.models.UserOnRole;
import ru.strict.db.hibernate.entities.EntityRoleuser;
import ru.strict.db.hibernate.entities.EntityUser;
import ru.strict.db.hibernate.entities.EntityUserOnRole;
import ru.strict.db.core.mappers.dto.MapperDtoBase;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа EntityUserOnRole и DtoUserOnRole
 */
public class MapperDtoUserOnRole extends MapperDtoBase<Long, EntityUserOnRole, UserOnRole<Long>> {

    private MapperDtoBase<Long, EntityUser, User<Long>> mapperUser;
    private MapperDtoBase<Long, EntityRoleuser, Roleuser<Long>> mapperRoleuser;

    public MapperDtoUserOnRole(){
        this.mapperUser = null;
        this.mapperRoleuser = null;
    }

    public MapperDtoUserOnRole(MapperDtoBase<Long, EntityUser, User<Long>> mapperUser
            , MapperDtoBase<Long, EntityRoleuser, Roleuser<Long>> mapperRoleuser){
        this.mapperUser = mapperUser;
        this.mapperRoleuser = mapperRoleuser;
    }

    @Override
    protected EntityUserOnRole implementMap(UserOnRole<Long> dto) {
        EntityUserOnRole entity = new EntityUserOnRole();
        entity.setId(dto.getId());
        entity.setRoleId(dto.getRoleId());
        Optional.ofNullable(mapperRoleuser).ifPresent((mapper) -> entity.setRole(mapper.map(dto.getRole())));
        entity.setUserId(dto.getUserId());
        Optional.ofNullable(mapperUser).ifPresent((mapper) -> entity.setUser(mapper.map(dto.getUser())));
        return entity;
    }

    @Override
    protected UserOnRole<Long> implementMap(EntityUserOnRole entity) {
        UserOnRole<Long> dto = new UserOnRole();
        dto.setId(entity.getId());
        dto.setRoleId(entity.getRoleId());
        Optional.ofNullable(mapperRoleuser).ifPresent((mapper) -> dto.setRole(mapper.map(entity.getRole())));
        dto.setUserId(entity.getUserId());
        Optional.ofNullable(mapperUser).ifPresent((mapper) -> dto.setUser(mapper.map(entity.getUser())));
        return dto;
    }
}
