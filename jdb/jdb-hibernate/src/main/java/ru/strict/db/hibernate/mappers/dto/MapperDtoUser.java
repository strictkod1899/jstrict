package ru.strict.db.hibernate.mappers.dto;

import ru.strict.db.core.dto.DtoProfile;
import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.core.dto.DtoUser;
import ru.strict.db.core.dto.DtoUserBase;
import ru.strict.db.hibernate.entities.EntityProfile;
import ru.strict.db.hibernate.entities.EntityRoleuser;
import ru.strict.db.hibernate.entities.EntityUser;
import ru.strict.db.core.mappers.dto.MapperDtoBase;

/**
 * Двухсторонний маппинг объектов типа EntityUser и DtoUser
 */
public class MapperDtoUser<E extends EntityUser, DTO extends DtoUser>
        extends MapperDtoUserBase<E, DTO> {

    public MapperDtoUser(){
        super();
    }

    public MapperDtoUser(MapperDtoBase<EntityRoleuser, DtoRoleuser> mapperRoleuser
            , MapperDtoBase<EntityProfile, DtoProfile> mapperProfile){
        super(mapperRoleuser, mapperProfile);
    }

    @Override
    protected EntityUser implementMap(DtoUser dto) {
        EntityUser baseEntity = super.implementMap(dto);

        EntityUser entity = new EntityUser();
        entity.setId(baseEntity.getId());
        entity.setUsername(baseEntity.getUsername());
        entity.setRolesuser(baseEntity.getRolesuser());
        entity.setProfile(baseEntity.getProfile());
        entity.setPasswordEncode(dto.getPasswordEncode());
        return entity;
    }

    @Override
    protected DtoUser implementMap(EntityUser entity) {
        DtoUserBase baseDto = super.implementMap(entity);

        DtoUser dto = new DtoUser();
        dto.setId(baseDto.getId());
        dto.setUsername(baseDto.getUsername());
        dto.setRolesuser(baseDto.getRolesuser());
        dto.setProfile(baseDto.getProfile());
        dto.setPasswordEncode(entity.getPasswordEncode());
        return dto;
    }
}