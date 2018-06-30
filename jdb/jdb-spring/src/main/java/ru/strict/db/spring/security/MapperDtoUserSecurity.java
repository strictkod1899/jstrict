package ru.strict.db.spring.security;

import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.core.entities.EntityRoleuser;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.mappers.dto.MapperDtoRoleuser;

/**
 * Двухсторонний маппинг объектов типа EntityUserSecurity и DtoUserSecurity
 */
public class MapperDtoUserSecurity extends MapperDtoBase<EntityUserSecurity, DtoUserSecurity> {

    private MapperDtoRoleuser mapperRoleuser;

    public MapperDtoUserSecurity(MapperDtoRoleuser mapperRoleuser){
        this.mapperRoleuser = mapperRoleuser;
    }

    @Override
    protected EntityUserSecurity implementMap(DtoUserSecurity dto) {
        EntityUserSecurity entity = new EntityUserSecurity();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        dto.getRolesuser().stream().forEach(r -> entity.addRoleuser(mapperRoleuser.map((DtoRoleuser) r)));
        entity.setPasswordEncode(dto.getPasswordEncode());
        return entity;
    }

    @Override
    protected DtoUserSecurity implementMap(EntityUserSecurity entity) {
        DtoUserSecurity dto = new DtoUserSecurity();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        entity.getRolesuser().stream().forEach(r -> dto.addRoleuser(mapperRoleuser.map((EntityRoleuser) r)));
        dto.setPasswordEncode(entity.getPasswordEncode());
        return dto;
    }
}
