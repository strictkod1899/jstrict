package ru.strict.db.mappers.dto;

import ru.strict.db.dto.StrictDtoProfile;
import ru.strict.db.dto.StrictDtoUser;
import ru.strict.db.entities.StrictEntityProfile;
import ru.strict.db.entities.StrictEntityUser;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа StrictEntityProfile и StrictDtoProfile
 */
public class StrictMapperDtoProfile<E extends StrictEntityProfile, DTO extends StrictDtoProfile>
        extends StrictMapperDtoBase<E, DTO> {

    private Optional<StrictMapperDtoBase<StrictEntityUser, StrictDtoUser>> mapperUser;

    public StrictMapperDtoProfile(){
        mapperUser = null;
        mapperUser.empty();
    }

    public StrictMapperDtoProfile(StrictMapperDtoBase<StrictEntityUser, StrictDtoUser> mapperUser){
        this.mapperUser = Optional.ofNullable(mapperUser);
    }

    @Override
    protected StrictEntityProfile implementMap(StrictDtoProfile dto) {
        StrictEntityProfile entity = new StrictEntityProfile();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setMiddlename(dto.getMiddlename());
        entity.setUserId(dto.getUserId());
        mapperUser.ifPresent((mapper) -> entity.setUser(mapper.map(dto.getUser())));
        return entity;
    }

    @Override
    protected StrictDtoProfile implementMap(StrictEntityProfile entity) {
        StrictDtoProfile dto = new StrictDtoProfile();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setMiddlename(entity.getMiddlename());
        dto.setUserId(entity.getUserId());
        mapperUser.ifPresent((mapper) -> dto.setUser(mapper.map(entity.getUser())));
        return dto;
    }
}
