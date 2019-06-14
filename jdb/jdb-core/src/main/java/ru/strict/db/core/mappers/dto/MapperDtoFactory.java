package ru.strict.db.core.mappers.dto;

import ru.strict.db.core.entities.*;
import ru.strict.models.*;
import ru.strict.utils.UtilClass;

/**
 * Фабрика создания маппер-классов
 */
public class MapperDtoFactory<ID>{

    public <E extends EntityBase<ID>, DTO extends DtoBase<ID>>
            MapperDtoBase<ID, E, DTO> instance(Class<E> entityClass, Class<DTO> dtoClass) {
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

    private MapperDtoBase<ID, EntityCountry<ID>, Country<ID>> createMapperCountry(){
        MapperDtoBase<ID, EntityCity<ID>, City<ID>> mapperCity = new MapperDtoCity();
        return new MapperDtoCountry(mapperCity);
    }

    private MapperDtoBase<ID, EntityCity<ID>, City<ID>> createMapperCity(){
        MapperDtoBase<ID, EntityCountry<ID>, Country<ID>> mapperCountry = new MapperDtoCountry();
        return new MapperDtoCity(mapperCountry);
    }

    private MapperDtoBase<ID, EntityProfile<ID>, Profile<ID>> createMapperProfile(){
        MapperDtoBase<ID, EntityUser<ID>, User<ID>> mapperUser = createMapperUser();
        return new MapperDtoProfile(mapperUser);
    }

    private MapperDtoBase<ID, EntityProfileInfo<ID>, ProfileDetails<ID>> createMapperProfileInfo(){
        MapperDtoBase<ID, EntityCity<ID>, City<ID>> mapperCity = new MapperDtoCity();
        return new MapperDtoProfileInfo(createMapperProfile(), mapperCity);
    }

    private MapperDtoBase<ID, EntityRoleuser<ID>, Roleuser<ID>> createMapperRoleuser(){
        MapperDtoBase<ID, EntityUser<ID>, User<ID>> mapperUser = createMapperUser();
        return new MapperDtoRoleuser(mapperUser);
    }

    private MapperDtoBase<ID, EntityUser<ID>, UserBase<ID>> createMapperUserBase(){
        MapperDtoBase<ID, EntityRoleuser<ID>, Roleuser<ID>> mapperRolesser = new MapperDtoRoleuser();
        MapperDtoBase<ID, EntityProfile<ID>, Profile<ID>> mapperProfile = new MapperDtoProfile();
        return new MapperDtoUserBase(mapperRolesser, mapperProfile);
    }

    private MapperDtoBase<ID, EntityUser<ID>, User<ID>> createMapperUser(){
        return new MapperDtoUser(createMapperUserBase());
    }

    private MapperDtoBase<ID, EntityUser<ID>, UserWithToken<ID>> createMapperUserWithToken(){
        MapperDtoBase<ID, EntityJWTToken<ID>, JWTToken<ID>> mapperToken = new MapperDtoJWTToken<>();
        return new MapperDtoUserWithToken(createMapperUser(), mapperToken);
    }

    private MapperDtoBase<ID, EntityUserOnRole<ID>, UserOnRole<ID>> createMapperUserOnRole(){
        MapperDtoBase<ID, EntityUser<ID>, User<ID>> mapperUser = createMapperUser();
        MapperDtoBase<ID, EntityRoleuser<ID>, Roleuser<ID>> mapperRolesser = new MapperDtoRoleuser();
        return new MapperDtoUserOnRole(mapperUser, mapperRolesser);
    }

    private MapperDtoBase<ID, EntityJWTToken<ID>, JWTToken<ID>> createMapperJWTToken(){
        MapperDtoBase<ID, EntityUser<ID>, UserWithToken<ID>> mapperUser = createMapperUserWithToken();
        return new MapperDtoJWTToken(mapperUser);
    }

    private MapperDtoBase<ID, EntityFileStorage<ID>, FileStorageBase<ID>> createMapperFileStorageBase(){
        return new MapperDtoFileStorageBase<>();
    }

    private MapperDtoBase<ID, EntityFileStorage<ID>, FileStoragePath<ID>> createMapperFileStoragePath(){
        return new MapperDtoFileStoragePath<>(createMapperFileStorageBase());
    }

    private MapperDtoBase<ID, EntityFileStorage<ID>, FileStorageContent<ID>> createMapperFileStorageContent(){
        return new MapperDtoFileStorageContent<>(createMapperFileStorageBase());
    }

    private MapperDtoBase<ID, EntityFileStorage<ID>, FileStorage<ID>> createMapperFileStorage(){
        return new MapperDtoFileStorage<>(createMapperFileStoragePath());
    }
}
