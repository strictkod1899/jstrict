package ru.strict.db.core.mappers.dto;

import ru.strict.db.core.dto.*;
import ru.strict.db.core.entities.EntityJWTToken;
import ru.strict.db.core.entities.EntityUser;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа EntityUser и DtoUserToken
 */
public class MapperDtoUserToken<ID> extends MapperDtoBase<ID, EntityUser<ID>, DtoUserToken<ID>> {

    private MapperDtoBase<ID, EntityUser<ID>, DtoUser<ID>> mapperBase;
    private MapperDtoBase<ID, EntityJWTToken<ID>, DtoJWTToken<ID>> mapperToken;

    public MapperDtoUserToken(MapperDtoBase<ID, EntityUser<ID>, DtoUser<ID>> mapperBase){
        super();
        this.mapperToken = null;
        this.mapperBase = mapperBase;
    }

    public MapperDtoUserToken(MapperDtoBase<ID, EntityUser<ID>, DtoUser<ID>> mapperBase,
                              MapperDtoBase<ID, EntityJWTToken<ID>, DtoJWTToken<ID>> mapperToken) {
        this.mapperBase = mapperBase;
        this.mapperToken = mapperToken;
    }

    @Override
    protected EntityUser<ID> implementMap(DtoUserToken<ID> dto) {
        EntityUser<ID> baseEntity = mapperBase.map(dto);

        EntityUser<ID> entity = new EntityUser();
        entity.setId(baseEntity.getId());
        entity.setUsername(baseEntity.getUsername());
        entity.setEmail(baseEntity.getEmail());
        entity.setBlocked(baseEntity.isBlocked());
        entity.setDeleted(baseEntity.isDeleted());
        entity.setConfirmEmail(baseEntity.isConfirmEmail());
        entity.setRoles(baseEntity.getRoles());
        entity.setProfile(baseEntity.getProfile());
        entity.setPasswordEncode(baseEntity.getPasswordEncode());
        Optional.ofNullable(mapperToken).ifPresent((mapper) ->
                dto.getTokens().stream().forEach(token -> entity.addToken(mapper.map(token))));
        return entity;
    }

    @Override
    protected DtoUserToken<ID> implementMap(EntityUser<ID> entity) {
        DtoUser<ID> baseDto = mapperBase.map(entity);

        DtoUserToken<ID> dto = new DtoUserToken();
        dto.setId(baseDto.getId());
        dto.setUsername(baseDto.getUsername());
        dto.setEmail(baseDto.getEmail());
        dto.setBlocked(baseDto.isBlocked());
        dto.setDeleted(baseDto.isDeleted());
        dto.setConfirmEmail(baseDto.isConfirmEmail());
        dto.setRoles(baseDto.getRoles());
        dto.setProfile(baseDto.getProfile());
        dto.setPasswordEncode(baseDto.getPasswordEncode());
        Optional.ofNullable(mapperToken).ifPresent((mapper) ->
                entity.getTokens().stream().forEach(token -> dto.addToken(mapper.map(token))));
        return dto;
    }
}
