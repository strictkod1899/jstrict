package ru.strict.db.core.mappers.dto;

import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.core.dto.DtoServiceOnRole;
import ru.strict.db.core.entities.EntityRoleuser;
import ru.strict.db.core.entities.EntityServiceOnRole;
import ru.strict.db.core.models.IEnumProvider;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа EntityServiceOnRole и DtoServiceOnRole
 */
public class MapperDtoServiceOnRole<ID, SERVICE> extends MapperDtoBase<ID, EntityServiceOnRole<ID, SERVICE>, DtoServiceOnRole<ID, SERVICE>> {

    private IEnumProvider<ID, SERVICE> serviceProvider;
    private MapperDtoBase<ID, EntityRoleuser<ID>, DtoRoleuser<ID>> mapperRoleuser;

    public MapperDtoServiceOnRole(){
        this.serviceProvider = null;
        this.mapperRoleuser = null;
    }

    public MapperDtoServiceOnRole(IEnumProvider<ID, SERVICE> serviceProvider,
                                  MapperDtoBase<ID, EntityRoleuser<ID>, DtoRoleuser<ID>> mapperRoleuser){
        this.serviceProvider = serviceProvider;
        this.mapperRoleuser = mapperRoleuser;
    }

    @Override
    protected EntityServiceOnRole<ID, SERVICE> implementMap(DtoServiceOnRole<ID, SERVICE> dto) {
        EntityServiceOnRole<ID, SERVICE> entity = new EntityServiceOnRole();
        entity.setId(dto.getId());
        entity.setRoleId(dto.getRoleId());
        Optional.ofNullable(mapperRoleuser).ifPresent((mapper) -> entity.setRole(mapper.map(dto.getRole())));
        entity.setServiceProvider(serviceProvider);
        return entity;
    }

    @Override
    protected DtoServiceOnRole<ID, SERVICE> implementMap(EntityServiceOnRole<ID, SERVICE> entity) {
        DtoServiceOnRole<ID, SERVICE> dto = new DtoServiceOnRole();
        dto.setId(entity.getId());
        dto.setRoleId(entity.getRoleId());
        Optional.ofNullable(mapperRoleuser).ifPresent((mapper) -> dto.setRole(mapper.map(entity.getRole())));
        entity.setServiceProvider(serviceProvider);
        return dto;
    }
}
