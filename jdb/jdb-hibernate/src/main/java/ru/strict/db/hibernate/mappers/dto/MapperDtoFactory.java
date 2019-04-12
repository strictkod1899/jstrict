package ru.strict.db.hibernate.mappers.dto;

import ru.strict.db.core.dto.*;
import ru.strict.db.hibernate.entities.*;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.utils.UtilClass;

/**
 * Фабрика создания маппер-классов
 */
public class MapperDtoFactory{

    public <E extends EntityBase<Long>, DTO extends DtoBase<Long>>
            MapperDtoBase<Long, E, DTO> instance(Class<E> entityClass, Class<DTO> dtoClass) {
        MapperDtoBase mapper = null;

        if(UtilClass.isEquals(entityClass, EntityCountry.class) && UtilClass.isEquals(dtoClass,DtoCountry.class)){
            mapper = createMapperCountry();
        } else if(UtilClass.isEquals(entityClass, EntityCity.class) && UtilClass.isEquals(dtoClass, DtoCity.class)){
            mapper = createMapperCity();
        } else if(UtilClass.isEquals(entityClass, EntityRoleuser.class) && UtilClass.isEquals(dtoClass, DtoRoleuser.class)){
            mapper = createMapperRoleuser();
        } else if(UtilClass.isEquals(entityClass, EntityUser.class) && UtilClass.isEquals(dtoClass, DtoUserBase.class)){
            mapper = createMapperUserBase();
        } else if(UtilClass.isEquals(entityClass, EntityUser.class) && UtilClass.isEquals(dtoClass, DtoUser.class)){
            mapper = createMapperUser();
        } else if(UtilClass.isEquals(entityClass, EntityUser.class) && UtilClass.isEquals(dtoClass, DtoUserWithToken.class)){
            mapper = createMapperUserWithToken();
        } else if(UtilClass.isEquals(entityClass, EntityUserOnRole.class) && UtilClass.isEquals(dtoClass, DtoUserOnRole.class)){
            mapper = createMapperUserOnRole();
        } else if(UtilClass.isEquals(entityClass, EntityProfile.class) && UtilClass.isEquals(dtoClass, DtoProfile.class)){
            mapper = createMapperProfile();
        } else if(UtilClass.isEquals(entityClass, EntityProfileInfo.class) && UtilClass.isEquals(dtoClass, DtoProfileInfo.class)){
            mapper = createMapperProfileInfo();
        } else if(UtilClass.isEquals(entityClass, EntityJWTToken.class) && UtilClass.isEquals(dtoClass, DtoJWTToken.class)){
            mapper = createMapperJWTToken();
        }else if(UtilClass.isEquals(entityClass, EntityFileStorage.class) && UtilClass.isEquals(dtoClass, DtoFileStorageBase.class)){
            mapper = createMapperFileStorageBase();
        } else if(UtilClass.isEquals(entityClass, EntityFileStorage.class) && UtilClass.isEquals(dtoClass, DtoFileStoragePath.class)){
            mapper = createMapperFileStoragePath();
        } else if(UtilClass.isEquals(entityClass, EntityFileStorage.class) && UtilClass.isEquals(dtoClass, DtoFileStorageContent.class)){
            mapper = createMapperFileStorageContent();
        } else if(UtilClass.isEquals(entityClass, EntityFileStorage.class) && UtilClass.isEquals(dtoClass, DtoFileStorage.class)){
            mapper = createMapperFileStorage();
        }

        return mapper;
    }

    private MapperDtoBase<Long, EntityCountry, DtoCountry<Long>> createMapperCountry(){
        MapperDtoBase<Long, EntityCity, DtoCity<Long>> mapperCity = new MapperDtoCity();
        return new MapperDtoCountry(mapperCity);
    }

    private MapperDtoBase<Long, EntityCity, DtoCity<Long>> createMapperCity(){
        MapperDtoBase<Long, EntityCountry, DtoCountry<Long>> mapperCountry = new MapperDtoCountry();
        return new MapperDtoCity(mapperCountry);
    }

    private MapperDtoBase<Long, EntityProfileBase, DtoProfile<Long>> createMapperProfileBase(){
        MapperDtoBase<Long, EntityUser, DtoUserBase<Long>> mapperUser = createMapperUserBase();
        return new MapperDtoProfileBase(mapperUser);
    }

    private MapperDtoBase<Long, EntityProfile, DtoProfile<Long>> createMapperProfile(){
        MapperDtoBase<Long, EntityUser, DtoUserBase<Long>> mapperUser = createMapperUserBase();
        return new MapperDtoProfile(mapperUser);
    }

    private MapperDtoBase<Long, EntityProfileInfo, DtoProfileInfo<Long>> createMapperProfileInfo(){
        MapperDtoBase<Long, EntityCity, DtoCity<Long>> mapperCity = new MapperDtoCity();
        MapperDtoBase<Long, EntityProfileBase, DtoProfile<Long>> mapperProfile = createMapperProfileBase();
        return new MapperDtoProfileInfo(mapperProfile, mapperCity);
    }

    private MapperDtoBase<Long, EntityRoleuser, DtoRoleuser<Long>> createMapperRoleuser(){
        MapperDtoBase<Long, EntityUser, DtoUserBase<Long>> mapperUser = createMapperUserBase();
        return new MapperDtoRoleuser(mapperUser);
    }

    private MapperDtoBase<Long, EntityUser, DtoUserBase<Long>> createMapperUserBase(){
        MapperDtoBase<Long, EntityRoleuser, DtoRoleuser<Long>> mapperRolesser = new MapperDtoRoleuser();
        MapperDtoBase<Long, EntityProfileBase, DtoProfile<Long>> mapperProfile = createMapperProfileBase();
        return new MapperDtoUserBase(mapperRolesser, mapperProfile);
    }

    private MapperDtoBase<Long, EntityUser, DtoUser<Long>> createMapperUser(){
        return new MapperDtoUser(createMapperUserBase());
    }

    private MapperDtoBase<Long, EntityUser, DtoUserWithToken<Long>> createMapperUserWithToken(){
        MapperDtoBase<Long, EntityJWTToken, DtoJWTToken<Long>> mapperToken = new MapperDtoJWTToken();
        return new MapperDtoUserWithToken(createMapperUser(), mapperToken);
    }

    private MapperDtoBase<Long, EntityUserOnRole, DtoUserOnRole<Long>> createMapperUserOnRole(){
        MapperDtoBase<Long, EntityUser, DtoUser<Long>> mapperUser = createMapperUser();
        MapperDtoBase<Long, EntityRoleuser, DtoRoleuser<Long>> mapperRolesser = new MapperDtoRoleuser();
        return new MapperDtoUserOnRole(mapperUser, mapperRolesser);
    }

    private MapperDtoBase<Long, EntityJWTToken, DtoJWTToken<Long>> createMapperJWTToken(){
        MapperDtoBase<Long, EntityUser, DtoUserWithToken<Long>> mapperUser = createMapperUserWithToken();
        return new MapperDtoJWTToken(mapperUser);
    }

    private MapperDtoBase<Long, EntityFileStorage, DtoFileStorageBase<Long>> createMapperFileStorageBase(){
        return new MapperDtoFileStorageBase();
    }

    private MapperDtoBase<Long, EntityFileStorage, DtoFileStoragePath<Long>> createMapperFileStoragePath(){
        return new MapperDtoFileStoragePath(createMapperFileStorageBase());
    }

    private MapperDtoBase<Long, EntityFileStorage, DtoFileStorageContent<Long>> createMapperFileStorageContent(){
        return new MapperDtoFileStorageContent(createMapperFileStorageBase());
    }

    private MapperDtoBase<Long, EntityFileStorage, DtoFileStorage<Long>> createMapperFileStorage(){
        return new MapperDtoFileStorage(createMapperFileStoragePath());
    }
}
