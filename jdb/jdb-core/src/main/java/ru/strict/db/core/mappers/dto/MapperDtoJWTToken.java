package ru.strict.db.core.mappers.dto;

import ru.strict.db.core.dto.DtoJWTToken;
import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.core.dto.DtoUserToken;
import ru.strict.db.core.entities.EntityJWTToken;
import ru.strict.db.core.entities.EntityRoleuser;
import ru.strict.db.core.entities.EntityUser;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа EntityJWTToken и DtoJWTToken
 */
public class MapperDtoJWTToken<ID> extends MapperDtoBase<ID, EntityJWTToken<ID>, DtoJWTToken<ID>> {

    private MapperDtoBase<ID, EntityUser<ID>, DtoUserToken<ID>> mapperUser;
    private MapperDtoBase<ID, EntityRoleuser<ID>, DtoRoleuser<ID>> mapperRoleuser;

    public MapperDtoJWTToken(){
        mapperUser = null;
        mapperRoleuser = null;
    }

    public MapperDtoJWTToken(MapperDtoBase<ID, EntityUser<ID>, DtoUserToken<ID>> mapperUser,
                                 MapperDtoBase<ID, EntityRoleuser<ID>, DtoRoleuser<ID>> mapperRoleuser){
        this.mapperUser = mapperUser;
        this.mapperRoleuser = mapperRoleuser;
    }

    @Override
    protected EntityJWTToken<ID> implementMap(DtoJWTToken<ID> dto) {
        EntityJWTToken<ID> entity = new EntityJWTToken();
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
        entity.setUserId(dto.getUserId());
        Optional.ofNullable(mapperUser).ifPresent((mapper) -> entity.setUser(mapper.map(dto.getUser())));
        entity.setRoleUserId(dto.getRoleUserId());
        Optional.ofNullable(mapperRoleuser).ifPresent((mapper) -> entity.setRoleUser(mapper.map(dto.getRoleUser())));
        return entity;
    }

    @Override
    protected DtoJWTToken<ID> implementMap(EntityJWTToken<ID> entity) {
        DtoJWTToken<ID> dto = new DtoJWTToken();
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
        Optional.ofNullable(mapperUser).ifPresent((mapper) -> dto.setUser(mapper.map(entity.getUser())));
        dto.setRoleUserId(entity.getRoleUserId());
        Optional.ofNullable(mapperRoleuser).ifPresent((mapper) -> dto.setRoleUser(mapper.map(entity.getRoleUser())));
        return dto;
    }
}
