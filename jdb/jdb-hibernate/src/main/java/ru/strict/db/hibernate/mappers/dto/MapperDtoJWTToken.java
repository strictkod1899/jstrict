package ru.strict.db.hibernate.mappers.dto;

import ru.strict.db.core.dto.DtoJWTToken;
import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.core.dto.DtoUser;
import ru.strict.db.hibernate.entities.EntityJWTToken;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.hibernate.entities.EntityRoleuser;
import ru.strict.db.hibernate.entities.EntityUser;

import java.util.Optional;
import java.util.UUID;

/**
 * Двухсторонний маппинг объектов типа EntityJWTToken и DtoJWTToken
 */
public class MapperDtoJWTToken<E extends EntityJWTToken, DTO extends DtoJWTToken> extends MapperDtoBase<E, DTO> {

    private MapperDtoBase<EntityUser, DtoUser> mapperUser;
    private MapperDtoBase<EntityRoleuser, DtoRoleuser> mapperRoleuser;

    public MapperDtoJWTToken(){
        mapperUser = null;
        mapperRoleuser = null;
    }

    public MapperDtoJWTToken(MapperDtoBase<EntityUser, DtoUser> mapperUser,
                             MapperDtoBase<EntityRoleuser, DtoRoleuser> mapperRoleuser){
        this.mapperUser = mapperUser;
        this.mapperRoleuser = mapperRoleuser;
    }

    @Override
    protected EntityJWTToken implementMap(DtoJWTToken dto) {
        EntityJWTToken entity = new EntityJWTToken();
        entity.setId((UUID)dto.getId());
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
        entity.setUserId((UUID)dto.getUserId());
        Optional.ofNullable(mapperUser).ifPresent((mapper) -> entity.setUser(mapper.map(dto.getUser())));
        entity.setRoleUserId((UUID)dto.getRoleUserId());
        Optional.ofNullable(mapperRoleuser).ifPresent((mapper) -> entity.setRoleUser(mapper.map(dto.getRoleUser())));
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
        Optional.ofNullable(mapperUser).ifPresent((mapper) -> dto.setUser(mapper.map(entity.getUser())));
        dto.setRoleUserId(entity.getRoleUserId());
        Optional.ofNullable(mapperRoleuser).ifPresent((mapper) -> dto.setRoleUser(mapper.map(entity.getRoleUser())));
        return dto;
    }
}
