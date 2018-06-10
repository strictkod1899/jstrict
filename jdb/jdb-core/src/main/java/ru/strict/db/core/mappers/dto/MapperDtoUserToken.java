package ru.strict.db.core.mappers.dto;

import ru.strict.db.core.dto.DtoProfile;
import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.core.dto.DtoUserBase;
import ru.strict.db.core.dto.DtoUserToken;
import ru.strict.db.core.entities.EntityProfile;
import ru.strict.db.core.entities.EntityRoleuser;
import ru.strict.db.core.entities.EntityUser;

/**
 * Двухсторонний маппинг объектов типа EntityUser и DtoUserToken
 */
public class MapperDtoUserToken<E extends EntityUser, DTO extends DtoUserToken>
        extends MapperDtoUserBase<E, DTO> {

    public MapperDtoUserToken(){
        super();
    }

    public MapperDtoUserToken(MapperDtoBase<EntityRoleuser, DtoRoleuser> mapperRoleuser
            , MapperDtoBase<EntityProfile, DtoProfile> mapperProfile){
        super(mapperRoleuser, mapperProfile);
    }

    @Override
    protected EntityUser implementMap(DtoUserToken dto) {
        EntityUser baseEntity = super.implementMap(dto);

        EntityUser entity = new EntityUser();
        entity.setId(baseEntity.getId());
        entity.setUsername(baseEntity.getUsername());
        entity.setRolesuser(baseEntity.getRolesuser());
        entity.setToken(dto.getToken());
        return entity;
    }

    @Override
    protected DtoUserToken implementMap(EntityUser entity) {
        DtoUserBase baseDto = super.implementMap(entity);

        DtoUserToken dto = new DtoUserToken();
        dto.setId(baseDto.getId());
        dto.setUsername(baseDto.getUsername());
        entity.setRolesuser(baseDto.getRolesuser());
        dto.setToken(entity.getToken());
        return dto;
    }
}
