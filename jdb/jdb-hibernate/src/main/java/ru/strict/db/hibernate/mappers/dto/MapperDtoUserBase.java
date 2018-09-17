package ru.strict.db.hibernate.mappers.dto;

import ru.strict.db.core.dto.DtoProfile;
import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.core.dto.DtoUserBase;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.hibernate.entities.EntityProfile;
import ru.strict.db.hibernate.entities.EntityRoleuser;
import ru.strict.db.hibernate.entities.EntityUser;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа EntityUser и DtoUserBase
 */
public class MapperDtoUserBase<ID> extends MapperDtoBase<ID, EntityUser<ID>, DtoUserBase<ID>> {

    private MapperDtoBase<ID, EntityRoleuser<ID>, DtoRoleuser<ID>> mapperRoleuser;
    private MapperDtoBase<ID, EntityProfile<ID>, DtoProfile<ID>> mapperProfile;

    public MapperDtoUserBase(){
        this.mapperRoleuser = null;
        this.mapperProfile = null;
    }

    public MapperDtoUserBase(MapperDtoBase<ID, EntityRoleuser<ID>, DtoRoleuser<ID>> mapperRoleuser
            , MapperDtoBase<ID, EntityProfile<ID>, DtoProfile<ID>> mapperProfile){
        this.mapperRoleuser = mapperRoleuser;
        this.mapperProfile = mapperProfile;
    }

    @Override
    protected EntityUser<ID> implementMap(DtoUserBase<ID> dto) {
        EntityUser<ID> entity = new EntityUser();
        entity.setId(dto.getId());
        entity.setEmail(dto.getEmail());
        entity.setBlocked(dto.isBlocked());
        entity.setDeleted(dto.isDeleted());
        entity.setConfirmEmail(dto.isConfirmEmail());
        entity.setUsername(dto.getUsername());
        Optional.ofNullable(mapperRoleuser).ifPresent((mapper) ->
                dto.getRolesuser().stream().forEach(r -> entity.addRoleuser(mapper.map((DtoRoleuser) r))));
        Optional.ofNullable(mapperProfile).ifPresent((mapper) -> entity.setProfile(mapper.map(dto.getProfile())));
        return entity;
    }

    @Override
    protected DtoUserBase<ID> implementMap(EntityUser<ID> entity) {
        DtoUserBase<ID> dto = new DtoUserBase();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setBlocked(entity.isBlocked());
        dto.setDeleted(entity.isDeleted());
        dto.setConfirmEmail(entity.isConfirmEmail());
        Optional.ofNullable(mapperRoleuser).ifPresent((mapper) ->
                entity.getRolesuser().stream().forEach(r -> dto.addRoleuser(mapper.map((EntityRoleuser) r))));
        Optional.ofNullable(mapperProfile).ifPresent((mapper) -> dto.setProfile(mapper.map(entity.getProfile())));
        return dto;
    }
}
