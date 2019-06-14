package ru.strict.db.hibernate.mappers.dto;

import ru.strict.models.JWTToken;
import ru.strict.models.User;
import ru.strict.models.UserWithToken;
import ru.strict.db.hibernate.entities.EntityJWTToken;
import ru.strict.db.hibernate.entities.EntityUser;
import ru.strict.db.core.mappers.dto.MapperDtoBase;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа EntityUser и DtoUserToken
 */
public class MapperDtoUserWithToken extends MapperDtoBase<Long, EntityUser, UserWithToken<Long>> {

    private MapperDtoBase<Long, EntityUser, User<Long>> mapperBase;
    private MapperDtoBase<Long, EntityJWTToken, JWTToken<Long>> mapperToken;

    public MapperDtoUserWithToken() {
        super();
    }

    public MapperDtoUserWithToken(MapperDtoBase<Long, EntityUser, User<Long>> mapperBase,
                                  MapperDtoBase<Long, EntityJWTToken, JWTToken<Long>> mapperToken) {
        this.mapperBase = mapperBase;
        this.mapperToken = mapperToken;
    }

    @Override
    protected EntityUser implementMap(UserWithToken<Long> dto) {
        EntityUser baseEntity = mapperBase.map(dto);

        EntityUser entity = new EntityUser();
        entity.setId(baseEntity.getId());
        entity.setUsername(baseEntity.getUsername());
        entity.setEmail(baseEntity.getEmail());
        entity.setBlocked(baseEntity.isBlocked());
        entity.setDeleted(baseEntity.isDeleted());
        entity.setConfirmEmail(baseEntity.isConfirmEmail());
        entity.setRoles(baseEntity.getRoles());
        entity.setProfiles(baseEntity.getProfiles());
        entity.setPasswordEncode(baseEntity.getPasswordEncode());
        Optional.ofNullable(mapperToken).ifPresent((mapper) ->
                dto.getTokens().stream().forEach(token -> entity.addToken(mapper.map(token))));
        return entity;
    }

    @Override
    protected UserWithToken<Long> implementMap(EntityUser entity) {
        User<Long> baseDto = mapperBase.map(entity);

        UserWithToken<Long> dto = new UserWithToken();
        dto.setId(baseDto.getId());
        dto.setUsername(baseDto.getUsername());
        dto.setEmail(baseDto.getEmail());
        dto.setBlocked(baseDto.isBlocked());
        dto.setDeleted(baseDto.isDeleted());
        dto.setConfirmEmail(baseDto.isConfirmEmail());
        dto.setRoles(baseDto.getRoles());
        dto.setProfiles(baseDto.getProfiles());
        dto.setPasswordEncode(baseDto.getPasswordEncode());
        Optional.ofNullable(mapperToken).ifPresent((mapper) ->
                entity.getTokens().stream().forEach(token -> dto.addToken(mapper.map(token))));
        return dto;
    }
}
