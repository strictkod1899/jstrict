package ru.strict.db.mappers;

import ru.strict.db.dto.StrictDtoUser;
import ru.strict.db.entities.StrictEntityUser;

public class StrictMapperUser extends StrictMapperUserBase<StrictDtoUser> {

    public StrictMapperUser(StrictMapperRoleuser mapperRoleuser){
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
