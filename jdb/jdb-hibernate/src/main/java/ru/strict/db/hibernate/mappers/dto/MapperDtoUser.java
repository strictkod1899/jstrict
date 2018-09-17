package ru.strict.db.hibernate.mappers.dto;

import ru.strict.db.core.dto.DtoUser;
import ru.strict.db.core.dto.DtoUserBase;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.hibernate.entities.EntityUser;

/**
 * Двухсторонний маппинг объектов типа EntityUser и DtoUser
 */
public class MapperDtoUser<ID> extends MapperDtoBase<ID, EntityUser<ID>, DtoUser<ID>> {

    private MapperDtoBase<ID, EntityUser<ID>, DtoUserBase<ID>> mapperBase;

    public MapperDtoUser(){
        super();
        mapperBase = null;
    }

    public MapperDtoUser(MapperDtoBase<ID, EntityUser<ID>, DtoUserBase<ID>> mapperBase) {
        this.mapperBase = mapperBase;
    }

    @Override
    protected EntityUser<ID> implementMap(DtoUser<ID> dto) {
        EntityUser<ID> baseEntity = mapperBase.map(dto);

        EntityUser<ID> entity = new EntityUser();
        entity.setId(baseEntity.getId());
        entity.setUsername(baseEntity.getUsername());
        entity.setEmail(baseEntity.getEmail());
        entity.setBlocked(baseEntity.isBlocked());
        entity.setDeleted(baseEntity.isDeleted());
        entity.setConfirmEmail(baseEntity.isConfirmEmail());
        entity.setRolesuser(baseEntity.getRolesuser());
        entity.setProfile(baseEntity.getProfile());
        entity.setPasswordEncode(dto.getPasswordEncode());
        return entity;
    }

    @Override
    protected DtoUser<ID> implementMap(EntityUser<ID> entity) {
        DtoUserBase<ID> baseDto = mapperBase.map(entity);

        DtoUser<ID> dto = new DtoUser();
        dto.setId(baseDto.getId());
        dto.setUsername(baseDto.getUsername());
        dto.setEmail(baseDto.getEmail());
        dto.setBlocked(baseDto.isBlocked());
        dto.setDeleted(baseDto.isDeleted());
        dto.setConfirmEmail(baseDto.isConfirmEmail());
        dto.setRolesuser(baseDto.getRolesuser());
        dto.setProfile(baseDto.getProfile());
        dto.setPasswordEncode(entity.getPasswordEncode());
        return dto;
    }
}
