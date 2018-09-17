package ru.strict.db.hibernate.mappers.dto;

import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.core.dto.DtoUser;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.hibernate.entities.EntityRoleuser;
import ru.strict.db.hibernate.entities.EntityUser;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа EntityRoleuser и DtoRoleuser
 */
public class MapperDtoRoleuser<ID> extends MapperDtoBase<ID, EntityRoleuser<ID>, DtoRoleuser<ID>> {

    private MapperDtoBase<ID, EntityUser<ID>, DtoUser<ID>> mapperUser;

    public MapperDtoRoleuser(){
        mapperUser = null;
    }

    public MapperDtoRoleuser(MapperDtoBase<ID, EntityUser<ID>, DtoUser<ID>> mapperUser){
        this.mapperUser = mapperUser;
    }

    @Override
    protected EntityRoleuser<ID> implementMap(DtoRoleuser<ID> dto) {
        EntityRoleuser<ID> entity = new EntityRoleuser();
        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());
        entity.setCode(dto.getCode());
        Optional.ofNullable(mapperUser).ifPresent((mapper) ->
                dto.getUsers().stream().forEach((r) -> entity.addUser(mapper.map((DtoUser) r))));
        return entity;
    }

    @Override
    protected DtoRoleuser<ID> implementMap(EntityRoleuser<ID> entity) {
        DtoRoleuser<ID> dto = new DtoRoleuser();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setCode(entity.getCode());
        Optional.ofNullable(mapperUser).ifPresent((mapper) ->
                entity.getUsers().stream().forEach(r -> dto.addUser(mapper.map((EntityUser) r))));
        return dto;
    }
}
