package ru.strict.db.core.mappers.dto;

import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.dto.*;
import ru.strict.db.core.entities.*;
import ru.strict.db.core.mappers.IMapper;
import ru.strict.patterns.IFactory;

/**
 * Фабрика создания маппер-классов
 */
public class MapperDtoFactory<ID, E extends EntityBase<ID>, DTO extends DtoBase<ID>>
        implements IFactory<IMapper, MapperDtoType> {

    @Override
    public MapperDtoBase<ID, E, DTO> instance(MapperDtoType parameter) {
        MapperDtoBase mapper = null;
        switch(parameter){
            case COUNTRY:
                mapper = createMapperCountry();
                break;
            case CITY:
                mapper = createMapperCity();
                break;
            case ROLE_USER:
                mapper = createMapperRoleuser();
                break;
            case USER_BASE:
                mapper = createMapperUserBase();
                break;
            case USER:
                mapper = createMapperUser();
                break;
            case USER_ON_ROLE:
                mapper = createMapperUserOnRole();
                break;
            case PROFILE:
                mapper = createMapperProfile();
                break;
            case PROFILE_INFO:
                mapper = createMapperProfileInfo();
                break;
            case JWT_TOKEN:
                mapper = createMapperJWTToken();
                break;
            case USER_TOKEN:
                mapper = createMapperUserToken();
                break;
        }
        return mapper;
    }

    private MapperDtoBase<ID, EntityCountry<ID>, DtoCountry<ID>> createMapperCountry(){
        MapperDtoBase<ID, EntityCity<ID>, DtoCity<ID>> mapperCity = new MapperDtoCity();
        return new MapperDtoCountry(mapperCity);
    }

    private MapperDtoBase<ID, EntityCity<ID>, DtoCity<ID>> createMapperCity(){
        MapperDtoBase<ID, EntityCountry<ID>, DtoCountry<ID>> mapperCountry = new MapperDtoCountry();
        return new MapperDtoCity(mapperCountry);
    }

    private MapperDtoBase<ID, EntityProfile<ID>, DtoProfile<ID>> createMapperProfile(){
        MapperDtoBase<ID, EntityUser<ID>, DtoUser<ID>> mapperUser = createMapperUser();
        return new MapperDtoProfile(mapperUser);
    }

    private MapperDtoBase<ID, EntityProfileInfo<ID>, DtoProfileInfo<ID>> createMapperProfileInfo(){
        MapperDtoBase<ID, EntityCity<ID>, DtoCity<ID>> mapperCity = new MapperDtoCity();
        return new MapperDtoProfileInfo(createMapperProfile(), mapperCity);
    }

    private MapperDtoBase<ID, EntityRoleuser<ID>, DtoRoleuser<ID>> createMapperRoleuser(){
        MapperDtoBase<ID, EntityUser<ID>, DtoUser<ID>> mapperUser = createMapperUser();
        return new MapperDtoRoleuser(mapperUser);
    }

    private MapperDtoBase<ID, EntityUser<ID>, DtoUserBase<ID>> createMapperUserBase(){
        MapperDtoBase<ID, EntityRoleuser<ID>, DtoRoleuser<ID>> mapperRolesser = new MapperDtoRoleuser();
        MapperDtoBase<ID, EntityProfile<ID>, DtoProfile<ID>> mapperProfile = new MapperDtoProfile();
        return new MapperDtoUserBase(mapperRolesser, mapperProfile);
    }

    private MapperDtoBase<ID, EntityUser<ID>, DtoUser<ID>> createMapperUser(){
        return new MapperDtoUser(createMapperUserBase());
    }

    private MapperDtoBase<ID, EntityUser<ID>, DtoUserToken<ID>> createMapperUserToken(){
        MapperDtoBase<ID, EntityJWTToken<ID>, DtoJWTToken<ID>> mapperToken = new MapperDtoJWTToken<>();
        return new MapperDtoUserToken(createMapperUser(), mapperToken);
    }

    private MapperDtoBase<ID, EntityUserOnRole<ID>, DtoUserOnRole<ID>> createMapperUserOnRole(){
        MapperDtoBase<ID, EntityUser<ID>, DtoUser<ID>> mapperUser = createMapperUser();
        MapperDtoBase<ID, EntityRoleuser<ID>, DtoRoleuser<ID>> mapperRolesser = new MapperDtoRoleuser();
        return new MapperDtoUserOnRole(mapperUser, mapperRolesser);
    }

    private MapperDtoBase<ID, EntityJWTToken<ID>, DtoJWTToken<ID>> createMapperJWTToken(){
        MapperDtoBase<ID, EntityUser<ID>, DtoUserToken<ID>> mapperUser = createMapperUserToken();
        MapperDtoBase<ID, EntityRoleuser<ID>, DtoRoleuser<ID>> mapperRolesser = new MapperDtoRoleuser();
        return new MapperDtoJWTToken(mapperUser, mapperRolesser);
    }
}
