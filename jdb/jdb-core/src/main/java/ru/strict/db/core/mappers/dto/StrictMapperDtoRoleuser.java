package ru.strict.db.core.mappers.dto;

import ru.strict.db.core.dto.StrictDtoRoleuser;
import ru.strict.db.core.dto.StrictDtoUser;
import ru.strict.db.core.entities.StrictEntityRoleuser;
import ru.strict.db.core.entities.StrictEntityUser;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа StrictEntityRoleuser и StrictDtoRoleuser
 */
public class StrictMapperDtoRoleuser extends StrictMapperDtoBase<StrictEntityRoleuser, StrictDtoRoleuser> {

    private Optional<StrictMapperDtoBase<StrictEntityUser, StrictDtoUser>> mapperUser;

    public StrictMapperDtoRoleuser(){
        mapperUser = Optional.empty();
    }

    public StrictMapperDtoRoleuser(StrictMapperDtoBase<StrictEntityUser, StrictDtoUser> mapperUser){
        this.mapperUser = Optional.ofNullable(mapperUser);
    }

    @Override
    protected StrictEntityRoleuser implementMap(StrictDtoRoleuser dto) {
        StrictEntityRoleuser entity = new StrictEntityRoleuser();
        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());
        entity.setSymbols(dto.getSymbols());
        mapperUser.ifPresent((mapper) ->
                dto.getUsers().stream().forEach((r) -> entity.addUser(mapper.map((StrictDtoUser) r))));
        return entity;
    }

    @Override
    protected StrictDtoRoleuser implementMap(StrictEntityRoleuser entity) {
        StrictDtoRoleuser dto = new StrictDtoRoleuser();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setSymbols(entity.getSymbols());
        mapperUser.ifPresent((mapper) ->
                entity.getUsers().stream().forEach(r -> dto.addUser(mapper.map((StrictEntityUser) r))));
        return dto;
    }
}
