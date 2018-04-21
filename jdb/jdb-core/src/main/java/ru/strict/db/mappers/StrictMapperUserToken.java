package ru.strict.db.mappers;

import ru.strict.db.dto.StrictDtoUserToken;
import ru.strict.db.entities.StrictEntityUser;

public class StrictMapperUserToken extends StrictMapperUserBase<StrictDtoUserToken> {

    public StrictMapperUserToken(StrictMapperRoleuser mapperRoleuser){
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
