package ru.strict.db.hibernate.mappers.dto;

import ru.strict.db.hibernate.entities.*;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.models.*;
import ru.strict.utils.UtilClass;

/**
 * Фабрика создания маппер-классов
 */
public class MapperDtoFactory{

    public <E extends EntityBase<Long>, DTO extends DtoBase<Long>>
            MapperDtoBase<Long, E, DTO> instance(Class<E> entityClass, Class<DTO> dtoClass) {
        MapperDtoBase mapper = null;

        if(UtilClass.isEquals(entityClass, EntityCountry.class) && UtilClass.isEquals(dtoClass,Country.class)){
            mapper = createMapperCountry();
        } else if(UtilClass.isEquals(entityClass, EntityCity.class) && UtilClass.isEquals(dtoClass, City.class)){
            mapper = createMapperCity();
        } else if(UtilClass.isEquals(entityClass, EntityRoleuser.class) && UtilClass.isEquals(dtoClass, Roleuser.class)){
            mapper = createMapperRoleuser();
        } else if(UtilClass.isEquals(entityClass, EntityUser.class) && UtilClass.isEquals(dtoClass, UserBase.class)){
            mapper = createMapperUserBase();
        } else if(UtilClass.isEquals(entityClass, EntityUser.class) && UtilClass.isEquals(dtoClass, User.class)){
            mapper = createMapperUser();
        } else if(UtilClass.isEquals(entityClass, EntityUser.class) && UtilClass.isEquals(dtoClass, UserWithToken.class)){
            mapper = createMapperUserWithToken();
        } else if(UtilClass.isEquals(entityClass, EntityUserOnRole.class) && UtilClass.isEquals(dtoClass, UserOnRole.class)){
            mapper = createMapperUserOnRole();
        } else if(UtilClass.isEquals(entityClass, EntityProfile.class) && UtilClass.isEquals(dtoClass, Profile.class)){
            mapper = createMapperProfile();
        } else if(UtilClass.isEquals(entityClass, EntityProfileInfo.class) && UtilClass.isEquals(dtoClass, ProfileDetails.class)){
            mapper = createMapperProfileInfo();
        } else if(UtilClass.isEquals(entityClass, EntityJWTToken.class) && UtilClass.isEquals(dtoClass, JWTToken.class)){
            mapper = createMapperJWTToken();
        }else if(UtilClass.isEquals(entityClass, EntityFileStorage.class) && UtilClass.isEquals(dtoClass, FileStorageBase.class)){
            mapper = createMapperFileStorageBase();
        } else if(UtilClass.isEquals(entityClass, EntityFileStorage.class) && UtilClass.isEquals(dtoClass, FileStoragePath.class)){
            mapper = createMapperFileStoragePath();
        } else if(UtilClass.isEquals(entityClass, EntityFileStorage.class) && UtilClass.isEquals(dtoClass, FileStorageContent.class)){
            mapper = createMapperFileStorageContent();
        } else if(UtilClass.isEquals(entityClass, EntityFileStorage.class) && UtilClass.isEquals(dtoClass, FileStorage.class)){
            mapper = createMapperFileStorage();
        }

        return mapper;
    }

    private MapperDtoBase<Long, EntityCountry, Country<Long>> createMapperCountry(){
        MapperDtoBase<Long, EntityCity, City<Long>> mapperCity = new MapperDtoCity();
        return new MapperDtoCountry(mapperCity);
    }

    private MapperDtoBase<Long, EntityCity, City<Long>> createMapperCity(){
        MapperDtoBase<Long, EntityCountry, Country<Long>> mapperCountry = new MapperDtoCountry();
        return new MapperDtoCity(mapperCountry);
    }

    private MapperDtoBase<Long, EntityProfileBase, Profile<Long>> createMapperProfileBase(){
        MapperDtoBase<Long, EntityUser, UserBase<Long>> mapperUser = createMapperUserBase();
        return new MapperDtoProfileBase(mapperUser);
    }

    private MapperDtoBase<Long, EntityProfile, Profile<Long>> createMapperProfile(){
        MapperDtoBase<Long, EntityUser, UserBase<Long>> mapperUser = createMapperUserBase();
        return new MapperDtoProfile(mapperUser);
    }

    private MapperDtoBase<Long, EntityProfileInfo, ProfileDetails<Long>> createMapperProfileInfo(){
        MapperDtoBase<Long, EntityCity, City<Long>> mapperCity = new MapperDtoCity();
        MapperDtoBase<Long, EntityProfileBase, Profile<Long>> mapperProfile = createMapperProfileBase();
        return new MapperDtoProfileInfo(mapperProfile, mapperCity);
    }

    private MapperDtoBase<Long, EntityRoleuser, Roleuser<Long>> createMapperRoleuser(){
        MapperDtoBase<Long, EntityUser, UserBase<Long>> mapperUser = createMapperUserBase();
        return new MapperDtoRoleuser(mapperUser);
    }

    private MapperDtoBase<Long, EntityUser, UserBase<Long>> createMapperUserBase(){
        MapperDtoBase<Long, EntityRoleuser, Roleuser<Long>> mapperRolesser = new MapperDtoRoleuser();
        MapperDtoBase<Long, EntityProfile, Profile<Long>> mapperProfile = createMapperProfile();
        return new MapperDtoUserBase(mapperRolesser, mapperProfile);
    }

    private MapperDtoBase<Long, EntityUser, User<Long>> createMapperUser(){
        return new MapperDtoUser(createMapperUserBase());
    }

    private MapperDtoBase<Long, EntityUser, UserWithToken<Long>> createMapperUserWithToken(){
        MapperDtoBase<Long, EntityJWTToken, JWTToken<Long>> mapperToken = new MapperDtoJWTToken();
        return new MapperDtoUserWithToken(createMapperUser(), mapperToken);
    }

    private MapperDtoBase<Long, EntityUserOnRole, UserOnRole<Long>> createMapperUserOnRole(){
        MapperDtoBase<Long, EntityUser, User<Long>> mapperUser = createMapperUser();
        MapperDtoBase<Long, EntityRoleuser, Roleuser<Long>> mapperRolesser = new MapperDtoRoleuser();
        return new MapperDtoUserOnRole(mapperUser, mapperRolesser);
    }

    private MapperDtoBase<Long, EntityJWTToken, JWTToken<Long>> createMapperJWTToken(){
        MapperDtoBase<Long, EntityUser, UserWithToken<Long>> mapperUser = createMapperUserWithToken();
        return new MapperDtoJWTToken(mapperUser);
    }

    private MapperDtoBase<Long, EntityFileStorage, FileStorageBase<Long>> createMapperFileStorageBase(){
        return new MapperDtoFileStorageBase();
    }

    private MapperDtoBase<Long, EntityFileStorage, FileStoragePath<Long>> createMapperFileStoragePath(){
        return new MapperDtoFileStoragePath(createMapperFileStorageBase());
    }

    private MapperDtoBase<Long, EntityFileStorage, FileStorageContent<Long>> createMapperFileStorageContent(){
        return new MapperDtoFileStorageContent(createMapperFileStorageBase());
    }

    private MapperDtoBase<Long, EntityFileStorage, FileStorage<Long>> createMapperFileStorage(){
        return new MapperDtoFileStorage(createMapperFileStoragePath());
    }
}
