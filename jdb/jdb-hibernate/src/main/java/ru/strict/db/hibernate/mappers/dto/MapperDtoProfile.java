package ru.strict.db.hibernate.mappers.dto;

import ru.strict.models.Profile;
import ru.strict.models.UserBase;
import ru.strict.db.hibernate.entities.EntityProfile;
import ru.strict.db.hibernate.entities.EntityUser;
import ru.strict.db.core.mappers.dto.MapperDtoBase;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа EntityProfile и DtoProfile
 */
public class MapperDtoProfile extends MapperDtoBase<Long, EntityProfile, Profile<Long>> {

    private MapperDtoBase<Long, EntityUser, UserBase<Long>> mapperUser;

    public MapperDtoProfile(){
        mapperUser = null;
    }

    public MapperDtoProfile(MapperDtoBase<Long, EntityUser, UserBase<Long>> mapperUser){
        this.mapperUser = mapperUser;
    }

    @Override
    protected EntityProfile implementMap(Profile<Long> dto) {
        EntityProfile entity = new EntityProfile();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setMiddlename(dto.getMiddlename());
        entity.setUserId(dto.getUserId());
        Optional.ofNullable(mapperUser).ifPresent((mapper) -> entity.setUser(mapper.map(dto.getUser())));
        return entity;
    }

    @Override
    protected Profile<Long> implementMap(EntityProfile entity) {
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
