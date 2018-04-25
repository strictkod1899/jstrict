package ru.strict.db.mappers.dto;

import ru.strict.db.dto.StrictDtoUserToken;
import ru.strict.db.entities.StrictEntityUser;

/**
 * Двухсторонний маппинг объектов типа StrictEntityUser и StrictDtoUserToken
 */
public class StrictMapperDtoUserToken extends StrictMapperDtoUserBase<StrictDtoUserToken> {

    public StrictMapperDtoUserToken(StrictMapperDtoRoleuser mapperRoleuser){
        super(mapperRoleuser);
    }

    @Override
    protected StrictEntityUser implementMap(StrictDtoUserToken dto) {
        StrictEntityUser entity = super.implementMap(dto);
        entity.setToken(dto.getToken());
        return entity;
    }

    @Override
    protected StrictDtoUserToken implementMap(StrictEntityUser entity) {
        StrictDtoUserToken dto = super.implementMap(entity);
        dto.setToken(entity.getToken());
        return dto;
    }
}
