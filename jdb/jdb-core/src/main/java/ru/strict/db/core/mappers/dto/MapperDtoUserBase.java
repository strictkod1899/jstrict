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

    private Optional<MapperDtoBase<EntityRoleuser, DtoRoleuser>> mapperRoleuser;
    private Optional<MapperDtoBase<EntityProfile, DtoProfile>> mapperProfile;

    public MapperDtoUserBase(){
        this.mapperRoleuser = Optional.empty();
        this.mapperProfile = Optional.empty();
    }

    public MapperDtoUserBase(MapperDtoBase<EntityRoleuser, DtoRoleuser> mapperRoleuser
            , MapperDtoBase<EntityProfile, DtoProfile> mapperProfile){
        this.mapperRoleuser = Optional.ofNullable(mapperRoleuser);
        this.mapperProfile = Optional.ofNullable(mapperProfile);
    }

    @Override
    protected EntityUser implementMap(DtoUserBase dto) {
        EntityUser entity = new EntityUser();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        mapperRoleuser.ifPresent((mapper) ->
                dto.getRolesuser().stream().forEach(r -> entity.addRoleuser(mapper.map((DtoRoleuser) r))));
        mapperProfile.ifPresent((mapper) -> entity.setProfile(mapper.map(dto.getProfile())));
        return entity;
    }

    @Override
    protected DtoUserBase implementMap(EntityUser entity) {
        DtoUserBase dto = new DtoUserBase();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        mapperRoleuser.ifPresent((mapper) ->
                entity.getRolesuser().stream().forEach(r -> dto.addRoleuser(mapper.map((EntityRoleuser) r))));
        mapperProfile.ifPresent((mapper) -> dto.setProfile(mapper.map(entity.getProfile())));
        return dto;
    }
}
