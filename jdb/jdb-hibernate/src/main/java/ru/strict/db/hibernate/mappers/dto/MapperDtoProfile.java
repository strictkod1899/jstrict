package ru.strict.db.hibernate.mappers.dto;

import ru.strict.db.core.dto.DtoProfile;
import ru.strict.db.core.dto.DtoUser;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.hibernate.entities.EntityProfile;
import ru.strict.db.hibernate.entities.EntityUser;

import java.util.Optional;
import java.util.UUID;

/**
 * Двухсторонний маппинг объектов типа EntityProfile и DtoProfile
 */
public class MapperDtoProfile<E extends EntityProfile, DTO extends DtoProfile>
        extends MapperDtoBase<E, DTO> {

    private MapperDtoBase<EntityUser, DtoUser> mapperUser;

    public MapperDtoProfile(){
        mapperUser = null;
    }

    public MapperDtoProfile(MapperDtoBase<EntityUser, DtoUser> mapperUser){
        this.mapperUser = mapperUser;
    }

    @Override
    protected EntityProfile implementMap(DtoProfile dto) {
        EntityProfile entity = new EntityProfile();
        entity.setId((UUID)dto.getId());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setMiddlename(dto.getMiddlename());
        entity.setUserId((UUID)dto.getUserId());
        Optional.ofNullable(mapperUser).ifPresent((mapper) -> entity.setUser(mapper.map(dto.getUser())));
        return entity;
    }

    @Override
    protected DtoProfile implementMap(EntityProfile entity) {
        DtoProfile dto = new DtoProfile();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setMiddlename(entity.getMiddlename());
        dto.setUserId(entity.getUserId());
        Optional.ofNullable(mapperUser).ifPresent((mapper) -> dto.setUser(mapper.map(entity.getUser())));
        return dto;
    }
}
