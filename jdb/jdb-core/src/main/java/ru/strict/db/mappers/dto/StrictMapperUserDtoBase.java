package ru.strict.db.mappers.dto;

import ru.strict.db.dto.StrictDtoUserBase;
import ru.strict.db.entities.StrictEntityUser;

public class StrictMapperUserDtoBase<T extends StrictDtoUserBase> extends StrictMapperDtoBase<StrictEntityUser, T> {

    private StrictMapperDtoRoleuser mapperRoleuser;

    public StrictMapperUserDtoBase(StrictMapperDtoRoleuser mapperRoleuser){
        this.mapperRoleuser = mapperRoleuser;
    }

    @Override
    protected StrictEntityUser implementMap(T dto) {
        StrictEntityUser entity = new StrictEntityUser();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setRoleuser(mapperRoleuser.map(dto.getRoleuser()));
        return entity;
    }

    @Override
    protected T implementMap(StrictEntityUser entity) {
        T dto = (T) new StrictDtoUserBase();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setRoleuser(mapperRoleuser.map(entity.getRoleuser()));
        return dto;
    }
}
