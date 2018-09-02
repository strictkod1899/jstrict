package ru.strict.db.spring.security;

import ru.strict.db.core.dto.DtoProfile;
import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.core.entities.EntityProfile;
import ru.strict.db.core.entities.EntityRoleuser;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.mappers.dto.MapperDtoRoleuser;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа EntityUserSecurity и DtoUserSecurity
 */
public class MapperDtoUserSecurity extends MapperDtoBase<EntityUserSecurity, DtoUserSecurity> {

    private MapperDtoBase<EntityRoleuser, DtoRoleuser> mapperRoleuser;
    private MapperDtoBase<EntityProfile, DtoProfile> mapperProfile;

    public MapperDtoUserSecurity(){
        this.mapperRoleuser = null;
        this.mapperProfile = null;
    }

    public MapperDtoUserSecurity(MapperDtoBase<EntityRoleuser, DtoRoleuser> mapperRoleuser
            , MapperDtoBase<EntityProfile, DtoProfile> mapperProfile){
        this.mapperRoleuser = mapperRoleuser;
        this.mapperProfile = mapperProfile;
    }

    @Override
    protected EntityUserSecurity implementMap(DtoUserSecurity dto) {
        EntityUserSecurity entity = new EntityUserSecurity();
        entity.setId(dto.getId());
        entity.setEmail(dto.getEmail());
        entity.setBlocked(dto.isBlocked());
        entity.setDeleted(dto.isDeleted());
        entity.setConfirmEmail(dto.isConfirmEmail());
        entity.setUsername(dto.getUsername());
        Optional.ofNullable(mapperRoleuser).ifPresent((mapper) ->
                dto.getRolesuser().stream().forEach(r -> entity.addRoleuser(mapper.map((DtoRoleuser) r))));
        Optional.ofNullable(mapperProfile).ifPresent((mapper) -> entity.setProfile(mapper.map(dto.getProfile())));
        entity.setPasswordEncode(dto.getPasswordEncode());
        return entity;
    }

    @Override
    protected DtoUserSecurity implementMap(EntityUserSecurity entity) {
        DtoUserSecurity dto = new DtoUserSecurity();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setBlocked(entity.isBlocked());
        dto.setDeleted(entity.isDeleted());
        dto.setConfirmEmail(entity.isConfirmEmail());
        Optional.ofNullable(mapperRoleuser).ifPresent((mapper) ->
                entity.getRolesuser().stream().forEach(r -> dto.addRoleuser(mapper.map((EntityRoleuser) r))));
        Optional.ofNullable(mapperProfile).ifPresent((mapper) -> dto.setProfile(mapper.map(entity.getProfile())));
        dto.setPasswordEncode(entity.getPasswordEncode());
        return dto;
    }
}
