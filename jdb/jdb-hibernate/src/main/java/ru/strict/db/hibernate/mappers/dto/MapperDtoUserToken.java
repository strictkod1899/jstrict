package ru.strict.db.hibernate.mappers.dto;

import ru.strict.db.core.dto.*;
import ru.strict.db.hibernate.entities.EntityJWTToken;
import ru.strict.db.hibernate.entities.EntityProfile;
import ru.strict.db.hibernate.entities.EntityRoleuser;
import ru.strict.db.hibernate.entities.EntityUser;
import ru.strict.db.core.mappers.dto.MapperDtoBase;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа EntityUser и DtoUserToken
 */
public class MapperDtoUserToken<E extends EntityUser, DTO extends DtoUserToken>
        extends MapperDtoUser<E, DTO> {

    private MapperDtoBase<EntityJWTToken, DtoJWTToken> mapperToken;

    public MapperDtoUserToken(){
        super();
        this.mapperToken = null;
    }

    public MapperDtoUserToken(MapperDtoBase<EntityRoleuser, DtoRoleuser> mapperRoleuser,
                              MapperDtoBase<EntityProfile, DtoProfile> mapperProfile,
                              MapperDtoBase<EntityJWTToken, DtoJWTToken> mapperToken){
        super(mapperRoleuser, mapperProfile);
        this.mapperToken = mapperToken;
    }

    @Override
    protected EntityUser implementMap(DtoUserToken dto) {
        EntityUser baseEntity = super.implementMap(dto);

        EntityUser entity = new EntityUser();
        entity.setId(baseEntity.getId());
        entity.setUsername(baseEntity.getUsername());
        entity.setEmail(baseEntity.getEmail());
        entity.setBlocked(baseEntity.isBlocked());
        entity.setDeleted(baseEntity.isDeleted());
        entity.setConfirmEmail(baseEntity.isConfirmEmail());
        entity.setRolesuser(baseEntity.getRolesuser());
        entity.setProfile(baseEntity.getProfile());
        entity.setPasswordEncode(baseEntity.getPasswordEncode());
        Optional.ofNullable(mapperToken).ifPresent((mapper) ->
                dto.getTokens().stream().forEach(token -> entity.addToken(mapper.map((DtoJWTToken) token))));
        return entity;
    }

    @Override
    protected DtoUserToken implementMap(EntityUser entity) {
        DtoUser baseDto = super.implementMap(entity);

        DtoUserToken dto = new DtoUserToken();
        dto.setId(baseDto.getId());
        dto.setUsername(baseDto.getUsername());
        dto.setEmail(baseDto.getEmail());
        dto.setBlocked(baseDto.isBlocked());
        dto.setDeleted(baseDto.isDeleted());
        dto.setConfirmEmail(baseDto.isConfirmEmail());
        dto.setRolesuser(baseDto.getRolesuser());
        dto.setProfile(baseDto.getProfile());
        dto.setPasswordEncode(baseDto.getPasswordEncode());
        Optional.ofNullable(mapperToken).ifPresent((mapper) ->
                entity.getTokens().stream().forEach(token -> dto.addToken(mapper.map(token))));
        return dto;
    }
}
