package ru.strict.db.mappers.dto;

import ru.strict.db.dto.StrictDtoRoleuser;
import ru.strict.db.dto.StrictDtoUserBase;
import ru.strict.db.entities.StrictEntityRoleuser;
import ru.strict.db.entities.StrictEntityUser;

/**
 * Двухсторонний маппинг объектов типа StrictEntityUser и StrictDtoUserBase
 */
public class StrictMapperDtoUserBase
        extends StrictMapperDtoBase<StrictEntityUser, StrictDtoUserBase> {

    private StrictMapperDtoRoleuser mapperRoleuser;

    public StrictMapperDtoUserBase(StrictMapperDtoRoleuser mapperRoleuser){
        this.mapperRoleuser = mapperRoleuser;
    }

    @Override
    protected StrictEntityUser implementMap(StrictDtoUserBase dto) {
        StrictEntityUser entity = new StrictEntityUser();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        dto.getRolesuser().stream().forEach(r -> entity.addRoleuser(mapperRoleuser.map((StrictDtoRoleuser) r)));
        return entity;
    }

    @Override
    protected StrictDtoUserBase implementMap(StrictEntityUser entity) {
        StrictDtoUserBase dto = new StrictDtoUserBase();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        entity.getRolesuser().stream().forEach(r -> dto.addRoleuser(mapperRoleuser.map((StrictEntityRoleuser) r)));
        return dto;
    }
}
