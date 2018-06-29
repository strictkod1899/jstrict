package ru.strict.db.core.mappers.dto;

import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.dto.*;
import ru.strict.db.core.entities.*;
import ru.strict.db.core.mappers.IMapper;
import ru.strict.patterns.IFactory;

/**
 * Фабрика создания маппер-классов
 */
public class MapperDtoFactory implements IFactory<IMapper, MapperDtoType> {

    @Override
    public MapperDtoBase instance(MapperDtoType parameter) {
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
            case JWT_USER_TOKEN:
                mapper = createMapperJWTUserToken();
                break;
            case USER_TOKEN:
                mapper = createMapperUserToken();
                break;
        }
        return mapper;
    }

    private MapperDtoBase<EntityCountry, DtoCountry> createMapperCountry(){
        MapperDtoBase<EntityCity, DtoCity> mapperCity = new MapperDtoCity();
        return new MapperDtoCountry(mapperCity);
    }

    private MapperDtoBase<EntityCity, DtoCity> createMapperCity(){
        MapperDtoBase<EntityCountry, DtoCountry> mapperCountry = new MapperDtoCountry();
        return new MapperDtoCity(mapperCountry);
    }

    private MapperDtoBase<EntityProfile, DtoProfile> createMapperProfile(){
        MapperDtoBase<EntityUser, DtoUser> mapperUser = new MapperDtoUser();
        return new MapperDtoProfile(mapperUser);
    }

    private MapperDtoBase<EntityProfileInfo, DtoProfileInfo> createMapperProfileInfo(){
        MapperDtoBase<EntityUser, DtoUser> mapperUser = new MapperDtoUser();
        MapperDtoBase<EntityCity, DtoCity> mapperCity = new MapperDtoCity();
        return new MapperDtoProfileInfo(mapperUser, mapperCity);
    }

    private MapperDtoBase<EntityRoleuser, DtoRoleuser> createMapperRoleuser(){
        MapperDtoBase<EntityUser, DtoUser> mapperUser = new MapperDtoUser();
        return new MapperDtoRoleuser(mapperUser);
    }

    private MapperDtoBase<EntityUser, DtoUser> createMapperUser(){
        MapperDtoBase<EntityRoleuser, DtoRoleuser> mapperRolesser = new MapperDtoRoleuser();
        MapperDtoBase<EntityProfile, DtoProfile> mapperProfile = new MapperDtoProfile();
        return new MapperDtoUser(mapperRolesser, mapperProfile);
    }

    private MapperDtoBase<EntityUser, DtoUser> createMapperUserToken(){
        MapperDtoBase<EntityRoleuser, DtoRoleuser> mapperRolesser = new MapperDtoRoleuser();
        MapperDtoBase<EntityProfile, DtoProfile> mapperProfile = new MapperDtoProfile();
        MapperDtoBase<EntityJWTUserToken, DtoJWTUserToken> mapperToken = new MapperDtoJWTUserToken<>();
        return new MapperDtoUserToken(mapperRolesser, mapperProfile, mapperToken);
    }

    private MapperDtoBase<EntityUserOnRole, DtoUserOnRole> createMapperUserOnRole(){
        MapperDtoBase<EntityUser, DtoUser> mapperUser = new MapperDtoUser();
        MapperDtoBase<EntityRoleuser, DtoRoleuser> mapperRolesser = new MapperDtoRoleuser();
        return new MapperDtoUserOnRole(mapperUser, mapperRolesser);
    }

    private MapperDtoBase<EntityJWTToken, DtoJWTToken> createMapperJWTToken(){
        return new MapperDtoJWTToken();
    }

    private MapperDtoBase<EntityJWTUserToken, DtoJWTUserToken> createMapperJWTUserToken(){
        MapperDtoBase<EntityUser, DtoUser> mapperUser = new MapperDtoUser();
        MapperDtoBase<EntityRoleuser, DtoRoleuser> mapperRolesser = new MapperDtoRoleuser();
        return new MapperDtoJWTUserToken(mapperUser, mapperRolesser);
    }
}
