package ru.strict.db.mappers.dto;

import ru.strict.db.dto.StrictDtoProfile;
import ru.strict.db.dto.StrictDtoRoleuser;
import ru.strict.db.dto.StrictDtoUserBase;
import ru.strict.db.entities.StrictEntityProfile;
import ru.strict.db.entities.StrictEntityRoleuser;
import ru.strict.db.entities.StrictEntityUser;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа StrictEntityUser и StrictDtoUserBase
 */
public class StrictMapperDtoUserBase<E extends StrictEntityUser, DTO extends StrictDtoUserBase>
        extends StrictMapperDtoBase<E, DTO> {

    private Optional<StrictMapperDtoBase<StrictEntityRoleuser, StrictDtoRoleuser>> mapperRoleuser;
    private Optional<StrictMapperDtoBase<StrictEntityProfile, StrictDtoProfile>> mapperProfile;

    public StrictMapperDtoUserBase(){
        this.mapperRoleuser = Optional.empty();
        this.mapperProfile = Optional.empty();
    }

    public StrictMapperDtoUserBase(StrictMapperDtoBase<StrictEntityRoleuser, StrictDtoRoleuser> mapperRoleuser
            , StrictMapperDtoBase<StrictEntityProfile, StrictDtoProfile> mapperProfile){
        this.mapperRoleuser = Optional.ofNullable(mapperRoleuser);
        this.mapperProfile = Optional.ofNullable(mapperProfile);
    }

    @Override
    protected StrictEntityUser implementMap(StrictDtoUserBase dto) {
        StrictEntityUser entity = new StrictEntityUser();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        mapperRoleuser.ifPresent((mapper) ->
                dto.getRolesuser().stream().forEach(r -> entity.addRoleuser(mapper.map((StrictDtoRoleuser) r))));
        mapperProfile.ifPresent((mapper) -> entity.setProfile(mapper.map(dto.getProfile())));
        return entity;
    }

    @Override
    protected StrictDtoUserBase implementMap(StrictEntityUser entity) {
        StrictDtoUserBase dto = new StrictDtoUserBase();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        mapperRoleuser.ifPresent((mapper) ->
                entity.getRolesuser().stream().forEach(r -> dto.addRoleuser(mapper.map((StrictEntityRoleuser) r))));
        mapperProfile.ifPresent((mapper) -> dto.setProfile(mapper.map(entity.getProfile())));
        return dto;
    }
}
