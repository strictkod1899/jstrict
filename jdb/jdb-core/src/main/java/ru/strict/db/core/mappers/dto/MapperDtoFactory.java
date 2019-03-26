package ru.strict.db.core.mappers.dto;

import ru.strict.db.core.dto.*;
import ru.strict.db.core.entities.*;
import ru.strict.utils.UtilClass;

/**
 * Фабрика создания маппер-классов
 */
public class MapperDtoFactory<ID>{

    public <E extends EntityBase<ID>, DTO extends DtoBase<ID>>
            MapperDtoBase<ID, E, DTO> instance(Class<E> entityClass, Class<DTO> dtoClass) {
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
        } else if(UtilClass.isEquals(entityClass, EntityJWTToken.class) && UtilClass.isEquals(dtoClass, DtoRoleuser.class)){
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

    private MapperDtoBase<ID, EntityUser<ID>, DtoUserWithToken<ID>> createMapperUserWithToken(){
        MapperDtoBase<ID, EntityJWTToken<ID>, DtoJWTToken<ID>> mapperToken = new MapperDtoJWTToken<>();
        return new MapperDtoUserWithToken(createMapperUser(), mapperToken);
    }

    private MapperDtoBase<ID, EntityUserOnRole<ID>, DtoUserOnRole<ID>> createMapperUserOnRole(){
        MapperDtoBase<ID, EntityUser<ID>, DtoUser<ID>> mapperUser = createMapperUser();
        MapperDtoBase<ID, EntityRoleuser<ID>, DtoRoleuser<ID>> mapperRolesser = new MapperDtoRoleuser();
        return new MapperDtoUserOnRole(mapperUser, mapperRolesser);
    }

    private MapperDtoBase<ID, EntityJWTToken<ID>, DtoJWTToken<ID>> createMapperJWTToken(){
        MapperDtoBase<ID, EntityUser<ID>, DtoUserWithToken<ID>> mapperUser = createMapperUserWithToken();
        return new MapperDtoJWTToken(mapperUser);
    }

    private MapperDtoBase<ID, EntityFileStorage<ID>, DtoFileStorageBase<ID>> createMapperFileStorageBase(){
        return new MapperDtoFileStorageBase<>();
    }

    private MapperDtoBase<ID, EntityFileStorage<ID>, DtoFileStoragePath<ID>> createMapperFileStoragePath(){
        return new MapperDtoFileStoragePath<>(createMapperFileStorageBase());
    }

    private MapperDtoBase<ID, EntityFileStorage<ID>, DtoFileStorageContent<ID>> createMapperFileStorageContent(){
        return new MapperDtoFileStorageContent<>(createMapperFileStorageBase());
    }

    private MapperDtoBase<ID, EntityFileStorage<ID>, DtoFileStorage<ID>> createMapperFileStorage(){
        return new MapperDtoFileStorage<>(createMapperFileStoragePath());
    }
}
