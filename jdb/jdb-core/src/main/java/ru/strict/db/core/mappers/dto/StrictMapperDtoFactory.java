package ru.strict.db.core.mappers.dto;

import ru.strict.db.core.dto.*;
import ru.strict.db.core.entities.*;

/**
 * Фабрика создания маппер-классов
 */
public class StrictMapperDtoFactory {

    /**
     * Создать двухсторонний маппинг объектов типа EntityRoleuser и DtoRoleuser
     * @return
     */
    public static MapperDtoBase<EntityRoleuser, DtoRoleuser> createMapperRoleuser(){
        MapperDtoUser mapperUser = new MapperDtoUser(
                                new MapperDtoRoleuser()
                                , new MapperDtoProfile());
        return new MapperDtoRoleuser(mapperUser);
    }

    /**
     * Создать двухсторонний маппинг объектов типа EntityUser и DtoUser
     * @return
     */
    public static MapperDtoBase<EntityUser, DtoUser> createMapperUser(){
        return new MapperDtoUser(
                createMapperRoleuser()
                , new MapperDtoProfile());
    }

    /**
     * Создать двухсторонний маппинг объектов типа EntityUserOnRole и DtoUserOnRole
     * @return
     */
    public static MapperDtoBase<EntityUserOnRole, DtoUserOnRole> createMapperUserOnRole(){
        return new MapperDtoUserOnRole(createMapperUser(), createMapperRoleuser());
    }

    /**
     * Создать двухсторонний маппинг объектов типа EntityProfile и DtoProfile
     * @return
     */
    public static MapperDtoBase<EntityProfile, DtoProfile> createMapperProfile(){
        return new MapperDtoProfile(createMapperUser());
    }

    /**
     * Создать двухсторонний маппинг объектов типа EntityProfileInfo и DtoProfileInfo
     * @return
     */
    public static MapperDtoBase<EntityProfileInfo, DtoProfileInfo> createMapperProfileInfo(){
        return new MapperDtoProfileInfo(createMapperUser());
    }
}
