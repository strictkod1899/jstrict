package ru.strict.db.core.mappers.dto;

import ru.strict.db.core.dto.DtoProfile;
import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.core.dto.DtoUserBase;
import ru.strict.db.core.entities.EntityProfile;
import ru.strict.db.core.entities.EntityRoleuser;
import ru.strict.db.core.entities.EntityUser;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа EntityUser и DtoUserBase
 */
public class MapperDtoUserBase<E extends EntityUser, DTO extends DtoUserBase>
        extends MapperDtoBase<E, DTO> {

    private MapperDtoBase<EntityRoleuser, DtoRoleuser> mapperRoleuser;
    private MapperDtoBase<EntityProfile, DtoProfile> mapperProfile;

    public MapperDtoUserBase(){
        this.mapperRoleuser = null;
        this.mapperProfile = null;
    }

    public MapperDtoUserBase(MapperDtoBase<EntityRoleuser, DtoRoleuser> mapperRoleuser
            , MapperDtoBase<EntityProfile, DtoProfile> mapperProfile){
        this.mapperRoleuser = mapperRoleuser;
        this.mapperProfile = mapperProfile;
    }

    @Override
    protected EntityUser implementMap(DtoUserBase dto) {
        EntityUser entity = new EntityUser();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        Optional.ofNullable(mapperRoleuser).ifPresent((mapper) ->
                dto.getRolesuser().stream().forEach(r -> entity.addRoleuser(mapper.map((DtoRoleuser) r))));
        Optional.ofNullable(mapperProfile).ifPresent((mapper) -> entity.setProfile(mapper.map(dto.getProfile())));
        return entity;
    }

    @Override
    protected DtoUserBase implementMap(EntityUser entity) {
        DtoUserBase dto = new DtoUserBase();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        Optional.ofNullable(mapperRoleuser).ifPresent((mapper) ->
                entity.getRolesuser().stream().forEach(r -> dto.addRoleuser(mapper.map((EntityRoleuser) r))));
        Optional.ofNullable(mapperProfile).ifPresent((mapper) -> dto.setProfile(mapper.map(entity.getProfile())));
        return dto;
    }
}
