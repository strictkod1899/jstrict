package ru.strict.db.core.mappers.dto;

import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.core.dto.DtoUser;
import ru.strict.db.core.entities.EntityRoleuser;
import ru.strict.db.core.entities.EntityUser;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа EntityRoleuser и DtoRoleuser
 */
public class MapperDtoRoleuser extends MapperDtoBase<EntityRoleuser, DtoRoleuser> {

    private MapperDtoBase<EntityUser, DtoUser> mapperUser;

    public MapperDtoRoleuser(){
        mapperUser = null;
    }

    public MapperDtoRoleuser(MapperDtoBase<EntityUser, DtoUser> mapperUser){
        this.mapperUser = mapperUser;
    }

    @Override
    protected EntityRoleuser implementMap(DtoRoleuser dto) {
        EntityRoleuser entity = new EntityRoleuser();
        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());
        entity.setCode(dto.getCode());
        Optional.ofNullable(mapperUser).ifPresent((mapper) ->
                dto.getUsers().stream().forEach((r) -> entity.addUser(mapper.map((DtoUser) r))));
        return entity;
    }

    @Override
    protected DtoRoleuser implementMap(EntityRoleuser entity) {
        DtoRoleuser dto = new DtoRoleuser();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setCode(entity.getCode());
        Optional.ofNullable(mapperUser).ifPresent((mapper) ->
                entity.getUsers().stream().forEach(r -> dto.addUser(mapper.map((EntityUser) r))));
        return dto;
    }
}
