package ru.strict.db.hibernate.mappers.dto;

import ru.strict.models.Profile;
import ru.strict.models.UserBase;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.hibernate.entities.EntityProfile;
import ru.strict.db.hibernate.entities.EntityProfileBase;
import ru.strict.db.hibernate.entities.EntityUser;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа EntityProfileBase и DtoProfile
 */
public class MapperDtoProfileBase extends MapperDtoBase<Long, EntityProfileBase, Profile<Long>> {

    private MapperDtoBase<Long, EntityUser, UserBase<Long>> mapperUser;

    public MapperDtoProfileBase(){
        mapperUser = null;
    }

    public MapperDtoProfileBase(MapperDtoBase<Long, EntityUser, UserBase<Long>> mapperUser){
        this.mapperUser = mapperUser;
    }

    @Override
    protected EntityProfileBase implementMap(Profile<Long> dto) {
        EntityProfileBase entity = new EntityProfile();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setMiddlename(dto.getMiddlename());
        entity.setUserId(dto.getUserId());
        Optional.ofNullable(mapperUser).ifPresent((mapper) -> entity.setUser(mapper.map(dto.getUser())));
        return entity;
    }

    @Override
    protected Profile<Long> implementMap(EntityProfileBase entity) {
        Profile dto = new Profile();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setMiddlename(entity.getMiddlename());
        dto.setUserId(entity.getUserId());
        Optional.ofNullable(mapperUser).ifPresent((mapper) -> dto.setUser(mapper.map(entity.getUser())));
        return dto;
    }
}
