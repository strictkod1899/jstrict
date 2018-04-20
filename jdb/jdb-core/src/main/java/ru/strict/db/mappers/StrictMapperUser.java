package ru.strict.db.mappers;

import ru.strict.db.dto.StrictDtoUser;
import ru.strict.db.entities.StrictEntityUser;

public class StrictMapperUser extends StrictBaseMapper<StrictEntityUser, StrictDtoUser>{

    private StrictMapperRoleuser mapperRoleuser;

    public StrictMapperUser(StrictMapperRoleuser mapperRoleuser){
        this.mapperRoleuser = mapperRoleuser;
    }

    @Override
    protected StrictEntityUser implementMap(StrictDtoUser dto) {
        StrictEntityUser entity = new StrictEntityUser();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setPasswordmd5(dto.getPasswordmd5());
        entity.setRoleuser(mapperRoleuser.map(dto.getRoleuser()));
        return entity;
    }

    @Override
    protected StrictDtoUser implementMap(StrictEntityUser entity) {
        StrictDtoUser dto = new StrictDtoUser();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setPasswordmd5(entity.getPasswordmd5());
        dto.setRoleuser(mapperRoleuser.map(entity.getRoleuser()));
        return dto;
    }
}
