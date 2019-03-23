package ru.strict.db.core.mappers.dto;

import ru.strict.db.core.dto.*;
import ru.strict.db.core.entities.*;
import ru.strict.utils.UtilReflection;

/**
 * Фабрика создания маппер-классов
 */
public class MapperDtoFactory<ID>{

    public <E extends EntityBase<ID>, DTO extends DtoBase<ID>>
            MapperDtoBase<ID, E, DTO> instance(Class<E> entityClass, Class<DTO> dtoClass) {
        MapperDtoBase mapper = null;

        if(UtilReflection.isInstanceOf(entityClass, EntityCountry.class)
                && UtilReflection.isInstanceOf(dtoClass, DtoCountry.class)){
            mapper = createMapperCountry();
        } else if(UtilReflection.isInstanceOf(entityClass, EntityCity.class)
                && UtilReflection.isInstanceOf(dtoClass, DtoCity.class)){
            mapper = createMapperCity();
        } else if(UtilReflection.isInstanceOf(entityClass, EntityRoleuser.class)
                && UtilReflection.isInstanceOf(dtoClass, DtoRoleuser.class)){
            mapper = createMapperRoleuser();
        } else if(UtilReflection.isInstanceOf(entityClass, EntityUser.class)
                && UtilReflection.isInstanceOf(dtoClass, DtoUserBase.class)){
            mapper = createMapperUserBase();
        } else if(UtilReflection.isInstanceOf(entityClass, EntityUser.class)
                && UtilReflection.isInstanceOf(dtoClass, DtoUser.class)){
            mapper = createMapperUser();
        } else if(UtilReflection.isInstanceOf(entityClass, EntityUser.class)
                && UtilReflection.isInstanceOf(dtoClass, DtoUserToken.class)){
            mapper = createMapperUserToken();
        } else if(UtilReflection.isInstanceOf(entityClass, EntityUserOnRole.class)
                && UtilReflection.isInstanceOf(dtoClass, DtoUserOnRole.class)){
            mapper = createMapperUserOnRole();
        } else if(UtilReflection.isInstanceOf(entityClass, EntityProfile.class)
                && UtilReflection.isInstanceOf(dtoClass, DtoProfile.class)){
            mapper = createMapperProfile();
        } else if(UtilReflection.isInstanceOf(entityClass, EntityProfileInfo.class)
                && UtilReflection.isInstanceOf(dtoClass, DtoProfileInfo.class)){
            mapper = createMapperProfileInfo();
        } else if(UtilReflection.isInstanceOf(entityClass, EntityJWTToken.class)
                && UtilReflection.isInstanceOf(dtoClass, DtoRoleuser.class)){
            mapper = createMapperJWTToken();
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
