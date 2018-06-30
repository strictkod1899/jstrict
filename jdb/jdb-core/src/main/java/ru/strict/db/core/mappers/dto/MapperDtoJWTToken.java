package ru.strict.db.core.mappers.dto;

import ru.strict.db.core.dto.DtoJWTToken;
import ru.strict.db.core.entities.EntityJWTToken;

/**
 * Двухсторонний маппинг объектов типа EntityJWTToken и DtoJWTToken
 */
public class MapperDtoJWTToken<E extends EntityJWTToken, DTO extends DtoJWTToken> extends MapperDtoBase<E, DTO> {

    public MapperDtoJWTToken(){}

    @Override
    protected EntityJWTToken implementMap(DtoJWTToken dto) {
        EntityJWTToken entity = new EntityJWTToken();
        entity.setId(dto.getId());
        entity.setAccessToken(dto.getAccessToken());
        entity.setRefreshToken(dto.getRefreshToken());
        entity.setExpireTimeAccess(dto.getExpireTimeAccess());
        entity.setExpireTimeRefresh(dto.getExpireTimeRefresh());
        entity.setIssuedAt(dto.getIssuedAt());
        entity.setIssuer(dto.getIssuer());
        entity.setSubject(dto.getSubject());
        entity.setNotBefore(dto.getNotBefore());
        entity.setAudience(dto.getAudience());
        entity.setSecret(dto.getSecret());
        entity.setAlgorithm(dto.getAlgorithm());
        entity.setType(dto.getType());
        return entity;
    }

    @Override
    protected DtoJWTToken implementMap(EntityJWTToken entity) {
        DtoJWTToken dto = new DtoJWTToken();
        dto.setId(entity.getId());
        dto.setAccessToken(entity.getAccessToken());
        dto.setRefreshToken(entity.getRefreshToken());
        dto.setExpireTimeAccess(entity.getExpireTimeAccess());
        dto.setExpireTimeRefresh(entity.getExpireTimeRefresh());
        dto.setIssuedAt(entity.getIssuedAt());
        dto.setIssuer(entity.getIssuer());
        dto.setSubject(entity.getSubject());
        dto.setNotBefore(entity.getNotBefore());
        dto.setAudience(entity.getAudience());
        dto.setSecret(entity.getSecret());
        dto.setAlgorithm(entity.getAlgorithm());
        dto.setType(entity.getType());
        return dto;
    }
}
