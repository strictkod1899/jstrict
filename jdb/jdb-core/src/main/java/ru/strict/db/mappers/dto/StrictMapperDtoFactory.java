package ru.strict.db.mappers.dto;

import ru.strict.db.dto.*;
import ru.strict.db.entities.*;

public class StrictMapperDtoFactory {

    public static StrictMapperDtoBase<StrictEntityRoleuser, StrictDtoRoleuser> createMapperRoleuser(){
        StrictMapperDtoUser mapperUser = new StrictMapperDtoUser(
                                new StrictMapperDtoRoleuser()
                                , new StrictMapperDtoProfile());
        return new StrictMapperDtoRoleuser(mapperUser);
    }

    public static StrictMapperDtoBase<StrictEntityUser, StrictDtoUser> createMapperUser(){
        return new StrictMapperDtoUser(
                createMapperRoleuser()
                , new StrictMapperDtoProfile());
    }

    public static StrictMapperDtoBase<StrictEntityUserOnRole, StrictDtoUserOnRole> createMapperUserOnRole(){
        return new StrictMapperDtoUserOnRole(createMapperUser(), createMapperRoleuser());
    }

    public static StrictMapperDtoBase<StrictEntityProfile, StrictDtoProfile> createMapperProfile(){
        return new StrictMapperDtoProfile(createMapperUser());
    }

    public static StrictMapperDtoBase<StrictEntityProfileInfo, StrictDtoProfileInfo> createMapperProfileInfo(){
        return new StrictMapperDtoProfileInfo(createMapperUser());
    }
}
