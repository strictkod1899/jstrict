package ru.strict.db.hibernate.mappers.dto;

import ru.strict.models.User;
import ru.strict.models.UserBase;
import ru.strict.db.hibernate.entities.EntityUser;
import ru.strict.db.core.mappers.dto.MapperDtoBase;

/**
 * Двухсторонний маппинг объектов типа EntityUser и DtoUser
 */
public class MapperDtoUser extends MapperDtoBase<Long, EntityUser, User<Long>> {

    private MapperDtoBase<Long, EntityUser, UserBase<Long>> mapperBase;

    public MapperDtoUser(MapperDtoBase<Long, EntityUser, UserBase<Long>> mapperBase) {
        super();
        this.mapperBase = mapperBase;
    }

    @Override
    protected EntityUser implementMap(User<Long> dto) {
        EntityUser baseEntity = mapperBase.map(dto);

        EntityUser entity = new EntityUser();
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
    protected User<Long> implementMap(EntityUser entity) {
        UserBase<Long> baseDto = mapperBase.map(entity);

        User<Long> dto = new User();
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
