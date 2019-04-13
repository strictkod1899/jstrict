package ru.strict.db.spring.security;

import ru.strict.db.core.dto.DtoProfile;
import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.core.entities.EntityProfile;
import ru.strict.db.core.entities.EntityRoleuser;
import ru.strict.db.core.mappers.dto.MapperDtoBase;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа EntityUserSecurity и DtoUserSecurity
 */
public class MapperDtoUserSecurity<ID> extends MapperDtoBase<ID, EntityUserSecurity<ID>, DtoUserSecurity<ID>> {

    private MapperDtoBase<ID, EntityRoleuser<ID>, DtoRoleuser<ID>> mapperRoleuser;
    private MapperDtoBase<ID, EntityProfile<ID>, DtoProfile<ID>> mapperProfile;

    public MapperDtoUserSecurity(){
        this.mapperRoleuser = null;
        this.mapperProfile = null;
    }

    public MapperDtoUserSecurity(MapperDtoBase<ID, EntityRoleuser<ID>, DtoRoleuser<ID>> mapperRoleuser
            , MapperDtoBase<ID, EntityProfile<ID>, DtoProfile<ID>> mapperProfile){
        this.mapperRoleuser = mapperRoleuser;
        this.mapperProfile = mapperProfile;
    }

    @Override
    protected EntityUserSecurity<ID> implementMap(DtoUserSecurity<ID> dto) {
        EntityUserSecurity<ID> entity = new EntityUserSecurity();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setPasswordEncode(dto.getPasswordEncode());
        entity.setEmail(dto.getEmail());
        entity.setBlocked(dto.isBlocked());
        entity.setDeleted(dto.isDeleted());
        entity.setConfirmEmail(dto.isConfirmEmail());
        Optional.ofNullable(mapperRoleuser).ifPresent((mapper) ->
                dto.getRoles().stream().forEach(r -> entity.addRole(mapper.map(r))));
        Optional.ofNullable(mapperProfile).ifPresent((mapper) ->
                dto.getProfiles().stream().forEach(p -> entity.addProfile(mapper.map(p))));
        return entity;
    }

    @Override
    protected DtoUserSecurity<ID> implementMap(EntityUserSecurity<ID> entity) {
        DtoUserSecurity<ID> dto = new DtoUserSecurity();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setPasswordEncode(entity.getPasswordEncode());
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
