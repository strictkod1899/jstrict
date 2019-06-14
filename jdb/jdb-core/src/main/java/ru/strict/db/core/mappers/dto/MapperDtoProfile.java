package ru.strict.db.core.mappers.dto;

import ru.strict.models.Profile;
import ru.strict.models.UserBase;
import ru.strict.db.core.entities.EntityProfile;
import ru.strict.db.core.entities.EntityUser;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа EntityProfile и DtoProfile
 */
public class MapperDtoProfile<ID>
        extends MapperDtoBase<ID, EntityProfile<ID>, Profile<ID>> {

    private MapperDtoBase<ID, EntityUser<ID>, UserBase<ID>> mapperUser;

    public MapperDtoProfile(){
        mapperUser = null;
    }

    public MapperDtoProfile(MapperDtoBase<ID, EntityUser<ID>, UserBase<ID>> mapperUser){
        this.mapperUser = mapperUser;
    }

    @Override
    protected EntityProfile<ID> implementMap(Profile<ID> dto) {
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
    protected Profile<ID> implementMap(EntityProfile<ID> entity) {
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
