package ru.strict.db.mappers;

import ru.strict.db.dto.StrictDtoUserBase;
import ru.strict.db.entities.StrictEntityUser;

public class StrictMapperUserBase<DTO extends StrictDtoUserBase> extends StrictMapperBase<StrictEntityUser, DTO> {

    private StrictMapperRoleuser mapperRoleuser;

    public StrictMapperUserBase(StrictMapperRoleuser mapperRoleuser){
        this.mapperRoleuser = mapperRoleuser;
    }

    @Override
    protected StrictEntityUser implementMap(DTO dto) {
        StrictEntityUser entity = new StrictEntityUser();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setRoleuser(mapperRoleuser.map(dto.getRoleuser()));
        return entity;
    }

    @Override
    protected DTO implementMap(StrictEntityUser entity) {
        DTO dto = (DTO) new StrictDtoUserBase();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setRoleuser(mapperRoleuser.map(entity.getRoleuser()));
        return dto;
    }
}
