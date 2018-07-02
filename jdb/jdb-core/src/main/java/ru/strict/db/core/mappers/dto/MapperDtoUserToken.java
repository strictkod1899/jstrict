package ru.strict.db.core.mappers.dto;

import ru.strict.db.core.dto.*;
import ru.strict.db.core.entities.EntityJWTUserToken;
import ru.strict.db.core.entities.EntityProfile;
import ru.strict.db.core.entities.EntityRoleuser;
import ru.strict.db.core.entities.EntityUser;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа EntityUser и DtoUserToken
 */
public class MapperDtoUserToken<E extends EntityUser, DTO extends DtoUserToken>
        extends MapperDtoUser<E, DTO> {

    private MapperDtoBase<EntityJWTUserToken, DtoJWTUserToken> mapperToken;

    public MapperDtoUserToken(){
        super();
        this.mapperToken = null;
    }

    public MapperDtoUserToken(MapperDtoBase<EntityRoleuser, DtoRoleuser> mapperRoleuser,
                              MapperDtoBase<EntityProfile, DtoProfile> mapperProfile,
                              MapperDtoBase<EntityJWTUserToken, DtoJWTUserToken> mapperToken){
        super(mapperRoleuser, mapperProfile);
        this.mapperToken = mapperToken;
    }

    @Override
    protected EntityUser implementMap(DtoUserToken dto) {
        EntityUser baseEntity = super.implementMap(dto);

        EntityUser entity = new EntityUser();
        entity.setId(baseEntity.getId());
        entity.setUsername(baseEntity.getUsername());
        entity.setRolesuser(baseEntity.getRolesuser());
        entity.setProfile(baseEntity.getProfile());
        entity.setPasswordEncode(baseEntity.getPasswordEncode());
        Optional.ofNullable(mapperToken).ifPresent((mapper) ->
                dto.getTokens().stream().forEach(token -> entity.addToken(mapper.map((DtoJWTUserToken) token))));
        return entity;
    }

    @Override
    protected DtoUserToken implementMap(EntityUser entity) {
        DtoUser baseDto = super.implementMap(entity);

        DtoUserToken dto = new DtoUserToken();
        dto.setId(baseDto.getId());
        dto.setUsername(baseDto.getUsername());
        dto.setRolesuser(baseDto.getRolesuser());
        dto.setProfile(baseDto.getProfile());
        dto.setPasswordEncode(baseDto.getPasswordEncode());
        Optional.ofNullable(mapperToken).ifPresent((mapper) ->
                entity.getTokens().stream().forEach(token -> dto.addToken(mapper.map((EntityJWTUserToken) token))));
        return dto;
    }
}
