package ru.strict.db.core.mappers.dto;

import ru.strict.models.Profile;
import ru.strict.models.Roleuser;
import ru.strict.models.UserBase;
import ru.strict.db.core.entities.EntityProfile;
import ru.strict.db.core.entities.EntityRoleuser;
import ru.strict.db.core.entities.EntityUser;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа EntityUser и DtoUserBase
 */
public class MapperDtoUserBase<ID> extends MapperDtoBase<ID, EntityUser<ID>, UserBase<ID>> {

    private MapperDtoBase<ID, EntityRoleuser<ID>, Roleuser<ID>> mapperRoleuser;
    private MapperDtoBase<ID, EntityProfile<ID>, Profile<ID>> mapperProfile;

    public MapperDtoUserBase(){
        this.mapperRoleuser = null;
        this.mapperProfile = null;
    }

    public MapperDtoUserBase(MapperDtoBase<ID, EntityRoleuser<ID>, Roleuser<ID>> mapperRoleuser,
                             MapperDtoBase<ID, EntityProfile<ID>, Profile<ID>> mapperProfile){
        this.mapperRoleuser = mapperRoleuser;
        this.mapperProfile = mapperProfile;
    }

    @Override
    protected EntityUser<ID> implementMap(UserBase<ID> dto) {
        EntityUser<ID> entity = new EntityUser();
        entity.setId(dto.getId());
        entity.setEmail(dto.getEmail());
        entity.setBlocked(dto.isBlocked());
        entity.setDeleted(dto.isDeleted());
        entity.setConfirmEmail(dto.isConfirmEmail());
        entity.setUsername(dto.getUsername());
        Optional.ofNullable(mapperRoleuser).ifPresent((mapper) ->
                dto.getRoles().stream().forEach(r -> entity.addRole(mapper.map(r))));
        Optional.ofNullable(mapperProfile).ifPresent((mapper) ->
                dto.getProfiles().stream().forEach(p -> entity.addProfile(mapper.map(p))));
        return entity;
    }

    @Override
    protected UserBase<ID> implementMap(EntityUser<ID> entity) {
        UserBase<ID> dto = new UserBase();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setBlocked(entity.isBlocked());
        dto.setDeleted(entity.isDeleted());
        dto.setConfirmEmail(entity.isConfirmEmail());
        Optional.ofNullable(mapperRoleuser).ifPresent((mapper) ->
                entity.getRoles().stream().forEach(r -> dto.addRole(mapper.map(r))));
        Optional.ofNullable(mapperProfile).ifPresent((mapper) ->
                entity.getProfiles().stream().forEach(p -> dto.addProfile(mapper.map(p))));
        return dto;
    }
}
