package ru.strict.db.core.mappers.dto;

import ru.strict.db.core.entities.EntityUser;
import ru.strict.models.User;
import ru.strict.models.UserBase;

/**
 * Двухсторонний маппинг объектов типа EntityUser и DtoUser
 */
public class MapperDtoUser<ID> extends MapperDtoBase<ID, EntityUser<ID>, User<ID>> {

    private MapperDtoBase<ID, EntityUser<ID>, UserBase<ID>> mapperBase;

    public MapperDtoUser(MapperDtoBase<ID, EntityUser<ID>, UserBase<ID>> mapperBase) {
        super();
        this.mapperBase = mapperBase;
    }

    @Override
    protected EntityUser<ID> implementMap(User<ID> dto) {
        EntityUser<ID> baseEntity = mapperBase.map(dto);

        EntityUser<ID> entity = new EntityUser();
        entity.setId(baseEntity.getId());
        entity.setUsername(baseEntity.getUsername());
        entity.setEmail(baseEntity.getEmail());
        entity.setBlocked(baseEntity.isBlocked());
        entity.setDeleted(baseEntity.isDeleted());
        entity.setConfirmEmail(baseEntity.isConfirmEmail());
        entity.setRoles(baseEntity.getRoles());
        entity.setProfiles(baseEntity.getProfiles());
        entity.setPasswordEncode(dto.getPasswordEncode());
        return entity;
    }

    @Override
    protected User<ID> implementMap(EntityUser<ID> entity) {
        UserBase<ID> baseDto = mapperBase.map(entity);

        User<ID> dto = new User();
        dto.setId(baseDto.getId());
        dto.setUsername(baseDto.getUsername());
        dto.setEmail(baseDto.getEmail());
        dto.setBlocked(baseDto.isBlocked());
        dto.setDeleted(baseDto.isDeleted());
        dto.setConfirmEmail(baseDto.isConfirmEmail());
        dto.setRoles(baseDto.getRoles());
        dto.setProfiles(baseDto.getProfiles());
        dto.setPasswordEncode(entity.getPasswordEncode());
        return dto;
    }
}
