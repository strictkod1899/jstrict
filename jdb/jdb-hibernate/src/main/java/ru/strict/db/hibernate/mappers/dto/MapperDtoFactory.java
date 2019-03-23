package ru.strict.db.hibernate.mappers.dto;

import ru.strict.db.core.dto.*;
import ru.strict.db.hibernate.entities.*;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.mappers.dto.MapperDtoCity;
import ru.strict.db.core.mappers.dto.MapperDtoCountry;
import ru.strict.db.core.mappers.dto.MapperDtoUserOnRole;

/**
 * Фабрика создания маппер-классов
 */
public class MapperDtoFactory<ID>{

    public <E extends EntityBase<ID>, DTO extends DtoBase<ID>>
    MapperDtoBase<ID, E, DTO> instance(Class<E> entityClass, Class<DTO> dtoClass) {
        MapperDtoBase mapper = null;

        if(entityClass == EntityCountry.class && dtoClass == DtoCountry.class){
            mapper = createMapperCountry();
        } else if(entityClass == EntityCity.class && dtoClass == DtoCity.class){
            mapper = createMapperCity();
        } else if(entityClass == EntityRoleuser.class && dtoClass == DtoRoleuser.class){
            mapper = createMapperRoleuser();
        } else if(entityClass == EntityUser.class && dtoClass == DtoUserBase.class){
            mapper = createMapperUserBase();
        } else if(entityClass == EntityUser.class && dtoClass == DtoUser.class){
            mapper = createMapperUser();
        } else if(entityClass == EntityUser.class && dtoClass == DtoUserToken.class){
            mapper = createMapperUserToken();
        } else if(entityClass == EntityUserOnRole.class && dtoClass == DtoUserOnRole.class){
            mapper = createMapperUserOnRole();
        } else if(entityClass == EntityProfile.class && dtoClass == DtoProfile.class){
            mapper = createMapperProfile();
        } else if(entityClass == EntityProfileInfo.class && dtoClass == DtoProfileInfo.class){
            mapper = createMapperProfileInfo();
        } else if(entityClass == EntityJWTToken.class && dtoClass == DtoRoleuser.class){
            mapper = createMapperJWTToken();
        }else if(entityClass == EntityFileStorage.class && dtoClass == DtoFileStorageBase.class){
            mapper = createMapperFileStorageBase();
        } else if(entityClass == EntityFileStorage.class && dtoClass == DtoFileStoragePath.class){
            mapper = createMapperFileStoragePath();
        } else if(entityClass == EntityFileStorage.class && dtoClass == DtoFileStorageContent.class){
            mapper = createMapperFileStorageContent();
        } else if(entityClass == EntityFileStorage.class && dtoClass == DtoFileStorage.class){
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
