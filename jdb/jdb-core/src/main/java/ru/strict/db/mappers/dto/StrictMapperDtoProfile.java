package ru.strict.db.mappers.dto;

import ru.strict.db.dto.StrictDtoProfile;
import ru.strict.db.dto.StrictDtoUser;
import ru.strict.db.entities.StrictEntityProfile;
import ru.strict.db.entities.StrictEntityUser;

/**
 * Двухсторонний маппинг объектов типа StrictEntityProfile и StrictDtoProfile
 */
public class StrictMapperDtoProfile< E extends StrictEntityProfile, DTO extends StrictDtoProfile>
        extends StrictMapperDtoBase<E, DTO> {

    private StrictMapperDtoUser mapperUser;

    public StrictMapperDtoProfile(StrictMapperDtoUser mapperUser){
        this.mapperUser = mapperUser;
    }

    @Override
    protected StrictEntityProfile implementMap(StrictDtoProfile dto) {
        StrictEntityProfile entity = new StrictEntityProfile();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setMiddlename(dto.getMiddlename());
        entity.setUserId(dto.getUserId());
        entity.setUser((StrictEntityUser) mapperUser.map(dto.getUser()));
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
        dto.setUser((StrictDtoUser) mapperUser.map(entity.getUser()));
        return dto;
    }
}
