package ru.strict.db.mappers.dto;

import ru.strict.db.dto.StrictDtoUserBase;
import ru.strict.db.entities.StrictEntityUser;

public class StrictMapperDtoUserBase<T extends StrictDtoUserBase> extends StrictMapperDtoBase<StrictEntityUser, T> {

    private StrictMapperDtoRoleuser mapperRoleuser;

    public StrictMapperDtoUserBase(StrictMapperDtoRoleuser mapperRoleuser){
        this.mapperRoleuser = mapperRoleuser;
    }

    @Override
    protected StrictEntityUser implementMap(T dto) {
        StrictEntityUser entity = new StrictEntityUser();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setRoleuserId(dto.getRoleuserId());
        entity.setRoleuser(mapperRoleuser.map(dto.getRoleuser()));
        return entity;
    }

    @Override
    protected T implementMap(StrictEntityUser entity) {
        T dto = (T) new StrictDtoUserBase();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setRoleuserId(entity.getRoleuserId());
        dto.setRoleuser(mapperRoleuser.map(entity.getRoleuser()));
        return dto;
    }
}
