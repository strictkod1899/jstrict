package ru.strict.db.mappers.dto;

import ru.strict.db.dto.*;
import ru.strict.db.entities.*;

/**
 * Фабрика создания маппер-классов
 */
public class StrictMapperDtoFactory {

    /**
     * Создать двухсторонний маппинг объектов типа StrictEntityRoleuser и StrictDtoRoleuser
     * @return
     */
    public static StrictMapperDtoBase<StrictEntityRoleuser, StrictDtoRoleuser> createMapperRoleuser(){
        StrictMapperDtoUser mapperUser = new StrictMapperDtoUser(
                                new StrictMapperDtoRoleuser()
                                , new StrictMapperDtoProfile());
        return new StrictMapperDtoRoleuser(mapperUser);
    }

    /**
     * Создать двухсторонний маппинг объектов типа StrictEntityUser и StrictDtoUser
     * @return
     */
    public static StrictMapperDtoBase<StrictEntityUser, StrictDtoUser> createMapperUser(){
        return new StrictMapperDtoUser(
                createMapperRoleuser()
                , new StrictMapperDtoProfile());
    }

    /**
     * Создать двухсторонний маппинг объектов типа StrictEntityUserOnRole и StrictDtoUserOnRole
     * @return
     */
    public static StrictMapperDtoBase<StrictEntityUserOnRole, StrictDtoUserOnRole> createMapperUserOnRole(){
        return new StrictMapperDtoUserOnRole(createMapperUser(), createMapperRoleuser());
    }

    /**
     * Создать двухсторонний маппинг объектов типа StrictEntityProfile и StrictDtoProfile
     * @return
     */
    public static StrictMapperDtoBase<StrictEntityProfile, StrictDtoProfile> createMapperProfile(){
        return new StrictMapperDtoProfile(createMapperUser());
    }

    /**
     * Создать двухсторонний маппинг объектов типа StrictEntityProfileInfo и StrictDtoProfileInfo
     * @return
     */
    public static StrictMapperDtoBase<StrictEntityProfileInfo, StrictDtoProfileInfo> createMapperProfileInfo(){
        return new StrictMapperDtoProfileInfo(createMapperUser());
    }
}
