package ru.strict.db.hibernate.mappers.dto;

import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.core.dto.DtoUserBase;
import ru.strict.db.hibernate.entities.EntityRoleuser;
import ru.strict.db.hibernate.entities.EntityUser;
import ru.strict.db.core.mappers.dto.MapperDtoBase;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа EntityRoleuser и DtoRoleuser
 */
public class MapperDtoRoleuser extends MapperDtoBase<Long, EntityRoleuser, DtoRoleuser<Long>> {

    private MapperDtoBase<Long, EntityUser, DtoUserBase<Long>> mapperUser;

    public MapperDtoRoleuser(){
        mapperUser = null;
    }

    public MapperDtoRoleuser(MapperDtoBase<Long, EntityUser, DtoUserBase<Long>> mapperUser){
        this.mapperUser = mapperUser;
    }

    @Override
    protected EntityRoleuser implementMap(DtoRoleuser<Long> dto) {
        EntityRoleuser entity = new EntityRoleuser();
        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());
        entity.setCode(dto.getCode());
        Optional.ofNullable(mapperUser).ifPresent((mapper) ->
                dto.getUsers().stream().forEach((r) -> entity.addUser(mapper.map(r))));
        return entity;
    }

    @Override
    protected DtoRoleuser<Long> implementMap(EntityRoleuser entity) {
        DtoRoleuser<Long> dto = new DtoRoleuser();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setCode(entity.getCode());
        Optional.ofNullable(mapperUser).ifPresent((mapper) ->
                entity.getUsers().stream().forEach(r -> dto.addUser(mapper.map(r))));
        return dto;
    }
}
