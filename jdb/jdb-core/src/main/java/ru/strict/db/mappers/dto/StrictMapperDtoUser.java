package ru.strict.db.mappers.dto;

import ru.strict.db.dto.StrictDtoUser;
import ru.strict.db.entities.StrictEntityUser;

public class StrictMapperDtoUser extends StrictMapperDtoUserBase<StrictDtoUser> {

    public StrictMapperDtoUser(StrictMapperDtoRoleuser mapperRoleuser){
        super(mapperRoleuser);
    }

    @Override
    protected StrictEntityUser implementMap(StrictDtoUser dto) {
        StrictEntityUser entity = super.implementMap(dto);
        entity.setPasswordEncode(dto.getPasswordEncode());
        entity.setToken(dto.getToken());
        return entity;
    }

    @Override
    protected StrictDtoUser implementMap(StrictEntityUser entity) {
        StrictDtoUser dto = super.implementMap(entity);
        dto.setPasswordEncode(entity.getPasswordEncode());
        dto.setToken(entity.getToken());
        return dto;
    }
}
