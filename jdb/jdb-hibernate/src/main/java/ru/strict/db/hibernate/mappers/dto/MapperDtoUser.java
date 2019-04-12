package ru.strict.db.hibernate.mappers.dto;

import ru.strict.db.core.dto.DtoUser;
import ru.strict.db.core.dto.DtoUserBase;
import ru.strict.db.hibernate.entities.EntityUser;
import ru.strict.db.core.mappers.dto.MapperDtoBase;

/**
 * Двухсторонний маппинг объектов типа EntityUser и DtoUser
 */
public class MapperDtoUser extends MapperDtoBase<Long, EntityUser, DtoUser<Long>> {

    private MapperDtoBase<Long, EntityUser, DtoUserBase<Long>> mapperBase;

    public MapperDtoUser(MapperDtoBase<Long, EntityUser, DtoUserBase<Long>> mapperBase) {
        super();
        this.mapperBase = mapperBase;
    }

    @Override
    protected EntityUser implementMap(DtoUser<Long> dto) {
        EntityUser baseEntity = mapperBase.map(dto);

        EntityUser entity = new EntityUser();
        entity.setId(baseEntity.getId());
        entity.setUsername(baseEntity.getUsername());
        entity.setEmail(baseEntity.getEmail());
        entity.setBlocked(baseEntity.isBlocked());
        entity.setDeleted(baseEntity.isDeleted());
        entity.setConfirmEmail(baseEntity.isConfirmEmail());
        entity.setRoles(baseEntity.getRoles());
        entity.setProfile(baseEntity.getProfile());
        entity.setPasswordEncode(dto.getPasswordEncode());
        return entity;
    }

    @Override
    protected DtoUser<Long> implementMap(EntityUser entity) {
        DtoUserBase<Long> baseDto = mapperBase.map(entity);

        DtoUser<Long> dto = new DtoUser();
        dto.setId(baseDto.getId());
        dto.setUsername(baseDto.getUsername());
        dto.setEmail(baseDto.getEmail());
        dto.setBlocked(baseDto.isBlocked());
        dto.setDeleted(baseDto.isDeleted());
        dto.setConfirmEmail(baseDto.isConfirmEmail());
        dto.setRoles(baseDto.getRoles());
        dto.setProfile(baseDto.getProfile());
        dto.setPasswordEncode(entity.getPasswordEncode());
        return dto;
    }
}
