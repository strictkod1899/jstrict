package ru.strict.db.core.mappers.dto;

import ru.strict.db.core.common.MapperDtoType;
import ru.strict.db.core.dto.*;
import ru.strict.db.core.entities.*;
import ru.strict.db.core.mappers.IMapper;
import ru.strict.patterns.IFactory;

/**
 * Фабрика создания маппер-классов
 */
public class StrictMapperDtoFactory implements IFactory<IMapper, MapperDtoType> {

    @Override
    public MapperDtoBase instance(MapperDtoType parameter) {
        MapperDtoBase mapper = null;
        switch(parameter){
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
        }
        return mapper;
    }

    /**
     * Создать двухсторонний маппинг объектов типа EntityRoleuser и DtoRoleuser
     * @return
     */
    private static MapperDtoBase<EntityRoleuser, DtoRoleuser> createMapperRoleuser(){
        MapperDtoUser mapperUser = new MapperDtoUser(
                                new MapperDtoRoleuser()
                                , new MapperDtoProfile());
        return new MapperDtoRoleuser(mapperUser);
    }

    /**
     * Создать двухсторонний маппинг объектов типа EntityUser и DtoUser
     * @return
     */
    private static MapperDtoBase<EntityUser, DtoUser> createMapperUser(){
        return new MapperDtoUser(
                createMapperRoleuser()
                , new MapperDtoProfile());
    }

    /**
     * Создать двухсторонний маппинг объектов типа EntityUserOnRole и DtoUserOnRole
     * @return
     */
    private static MapperDtoBase<EntityUserOnRole, DtoUserOnRole> createMapperUserOnRole(){
        return new MapperDtoUserOnRole(createMapperUser(), createMapperRoleuser());
    }

    /**
     * Создать двухсторонний маппинг объектов типа EntityProfile и DtoProfile
     * @return
     */
    private static MapperDtoBase<EntityProfile, DtoProfile> createMapperProfile(){
        return new MapperDtoProfile(createMapperUser());
    }

    /**
     * Создать двухсторонний маппинг объектов типа EntityProfileInfo и DtoProfileInfo
     * @return
     */
    private static MapperDtoBase<EntityProfileInfo, DtoProfileInfo> createMapperProfileInfo(){
        return new MapperDtoProfileInfo(createMapperUser());
    }
}
