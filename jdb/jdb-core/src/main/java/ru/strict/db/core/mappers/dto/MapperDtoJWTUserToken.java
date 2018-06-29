package ru.strict.db.core.mappers.dto;

import ru.strict.db.core.dto.DtoJWTToken;
import ru.strict.db.core.dto.DtoJWTUserToken;
import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.core.dto.DtoUser;
import ru.strict.db.core.entities.EntityJWTToken;
import ru.strict.db.core.entities.EntityJWTUserToken;
import ru.strict.db.core.entities.EntityRoleuser;
import ru.strict.db.core.entities.EntityUser;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа EntityJWTUserToken и DtoJWTUserToken
 */
public class MapperDtoJWTUserToken<E extends EntityJWTUserToken, DTO extends DtoJWTUserToken> extends MapperDtoJWTToken<E, DTO> {

    private Optional<MapperDtoBase<EntityUser, DtoUser>> mapperUser;
    private Optional<MapperDtoBase<EntityRoleuser, DtoRoleuser>> mapperRoleuser;

    public MapperDtoJWTUserToken(){
        mapperUser = Optional.empty();
        mapperRoleuser = Optional.empty();
    }

    public MapperDtoJWTUserToken(MapperDtoBase<EntityUser, DtoUser> mapperUser,
                                 MapperDtoBase<EntityRoleuser, DtoRoleuser> mapperRoleuser){
        this.mapperUser = Optional.ofNullable(mapperUser);
        this.mapperRoleuser = Optional.ofNullable(mapperRoleuser);
    }

    @Override
    protected EntityJWTUserToken implementMap(DtoJWTUserToken dto) {
        EntityJWTToken baseEntity = super.implementMap(dto);

        EntityJWTUserToken entity = new EntityJWTUserToken();
        entity.setId(baseEntity.getId());
        entity.setAccessToken(baseEntity.getAccessToken());
        entity.setRefreshToken(baseEntity.getRefreshToken());
        entity.setExpireTimeAccess(baseEntity.getExpireTimeAccess());
        entity.setExpireTimeRefresh(baseEntity.getExpireTimeRefresh());
        entity.setIssuedAt(baseEntity.getIssuedAt());
        entity.setIssuer(baseEntity.getIssuer());
        entity.setSubject(baseEntity.getSubject());
        entity.setNotBefore(baseEntity.getNotBefore());
        entity.setAudience(baseEntity.getAudience());
        entity.setSecret(baseEntity.getSecret());
        entity.setAlgorithm(baseEntity.getAlgorithm());
        entity.setType(baseEntity.getType());
        entity.setUserId(dto.getUserId());
        mapperUser.ifPresent((mapper) -> entity.setUser(mapper.map(dto.getUser())));
        entity.setRoleUserId(dto.getRoleUserId());
        mapperRoleuser.ifPresent((mapper) -> entity.setRoleUser(mapper.map(dto.getRoleUser())));
        return entity;
    }

    @Override
    protected DtoJWTUserToken implementMap(EntityJWTUserToken entity) {
        DtoJWTToken baseDto = super.implementMap(entity);

        DtoJWTUserToken dto = new DtoJWTUserToken();
        dto.setId(baseDto.getId());
        dto.setAccessToken(baseDto.getAccessToken());
        dto.setRefreshToken(baseDto.getRefreshToken());
        dto.setExpireTimeAccess(baseDto.getExpireTimeAccess());
        dto.setExpireTimeRefresh(baseDto.getExpireTimeRefresh());
        dto.setIssuedAt(baseDto.getIssuedAt());
        dto.setIssuer(baseDto.getIssuer());
        dto.setSubject(baseDto.getSubject());
        dto.setNotBefore(baseDto.getNotBefore());
        dto.setAudience(baseDto.getAudience());
        dto.setSecret(baseDto.getSecret());
        dto.setAlgorithm(baseDto.getAlgorithm());
        dto.setType(baseDto.getType());
        dto.setUserId(entity.getUserId());
        mapperUser.ifPresent((mapper) -> dto.setUser(mapper.map(entity.getUser())));
        dto.setRoleUserId(entity.getRoleUserId());
        mapperRoleuser.ifPresent((mapper) -> dto.setRoleUser(mapper.map(entity.getRoleUser())));
        return dto;
    }
}
