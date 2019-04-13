package ru.strict.db.hibernate.mappers.dto;

import ru.strict.db.core.dto.DtoProfile;
import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.core.dto.DtoUserBase;
import ru.strict.db.hibernate.entities.EntityProfile;
import ru.strict.db.hibernate.entities.EntityProfileBase;
import ru.strict.db.hibernate.entities.EntityRoleuser;
import ru.strict.db.hibernate.entities.EntityUser;
import ru.strict.db.core.mappers.dto.MapperDtoBase;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа EntityUser и DtoUserBase
 */
public class MapperDtoUserBase extends MapperDtoBase<Long, EntityUser, DtoUserBase<Long>> {

    private MapperDtoBase<Long, EntityRoleuser, DtoRoleuser<Long>> mapperRoleuser;
    private MapperDtoBase<Long, EntityProfile, DtoProfile<Long>> mapperProfile;

    public MapperDtoUserBase(){
        this.mapperRoleuser = null;
        this.mapperProfile = null;
    }

    public MapperDtoUserBase(MapperDtoBase<Long, EntityRoleuser, DtoRoleuser<Long>> mapperRoleuser,
                             MapperDtoBase<Long, EntityProfile, DtoProfile<Long>> mapperProfile){
        this.mapperRoleuser = mapperRoleuser;
        this.mapperProfile = mapperProfile;
    }

    @Override
    protected EntityUser implementMap(DtoUserBase<Long> dto) {
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
    protected DtoUserBase<Long> implementMap(EntityUser entity) {
        DtoUserBase<Long> dto = new DtoUserBase();
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
