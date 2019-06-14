package ru.strict.db.hibernate.mappers.dto;

import ru.strict.models.Profile;
import ru.strict.models.Roleuser;
import ru.strict.models.UserBase;
import ru.strict.db.hibernate.entities.EntityProfile;
import ru.strict.db.hibernate.entities.EntityRoleuser;
import ru.strict.db.hibernate.entities.EntityUser;
import ru.strict.db.core.mappers.dto.MapperDtoBase;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа EntityUser и DtoUserBase
 */
public class MapperDtoUserBase extends MapperDtoBase<Long, EntityUser, UserBase<Long>> {

    private MapperDtoBase<Long, EntityRoleuser, Roleuser<Long>> mapperRoleuser;
    private MapperDtoBase<Long, EntityProfile, Profile<Long>> mapperProfile;

    public MapperDtoUserBase(){
        this.mapperRoleuser = null;
        this.mapperProfile = null;
    }

    public MapperDtoUserBase(MapperDtoBase<Long, EntityRoleuser, Roleuser<Long>> mapperRoleuser,
                             MapperDtoBase<Long, EntityProfile, Profile<Long>> mapperProfile){
        this.mapperRoleuser = mapperRoleuser;
        this.mapperProfile = mapperProfile;
    }

    @Override
    protected EntityUser implementMap(UserBase<Long> dto) {
        EntityUser entity = new EntityUser();
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
    protected UserBase<Long> implementMap(EntityUser entity) {
        UserBase<Long> dto = new UserBase();
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
