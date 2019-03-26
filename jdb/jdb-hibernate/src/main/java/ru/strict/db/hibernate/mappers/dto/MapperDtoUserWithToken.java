package ru.strict.db.hibernate.mappers.dto;

import ru.strict.db.core.dto.DtoJWTToken;
import ru.strict.db.core.dto.DtoUser;
import ru.strict.db.core.dto.DtoUserWithToken;
import ru.strict.db.hibernate.entities.EntityJWTToken;
import ru.strict.db.hibernate.entities.EntityUser;
import ru.strict.db.core.mappers.dto.MapperDtoBase;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа EntityUser и DtoUserToken
 */
public class MapperDtoUserWithToken<ID> extends MapperDtoBase<ID, EntityUser<ID>, DtoUserWithToken<ID>> {

    private MapperDtoBase<ID, EntityUser<ID>, DtoUser<ID>> mapperBase;
    private MapperDtoBase<ID, EntityJWTToken<ID>, DtoJWTToken<ID>> mapperToken;

    public MapperDtoUserWithToken() {
        super();
    }

    public MapperDtoUserWithToken(MapperDtoBase<ID, EntityUser<ID>, DtoUser<ID>> mapperBase,
                                  MapperDtoBase<ID, EntityJWTToken<ID>, DtoJWTToken<ID>> mapperToken) {
        this.mapperBase = mapperBase;
        this.mapperToken = mapperToken;
    }

    @Override
    protected EntityUser<ID> implementMap(DtoUserWithToken<ID> dto) {
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
    protected DtoUserWithToken<ID> implementMap(EntityUser<ID> entity) {
        DtoUser<ID> baseDto = mapperBase.map(entity);

        DtoUserWithToken<ID> dto = new DtoUserWithToken();
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
