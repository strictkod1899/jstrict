package ru.strict.db.core.mappers.dto;

import ru.strict.models.Roleuser;
import ru.strict.models.ServiceOnRole;
import ru.strict.db.core.entities.EntityRoleuser;
import ru.strict.db.core.entities.EntityServiceOnRole;
import ru.strict.models.IModelProvider;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа EntityServiceOnRole и DtoServiceOnRole
 */
public class MapperDtoServiceOnRole<ID, SERVICE> extends MapperDtoBase<ID, EntityServiceOnRole<ID, SERVICE>, ServiceOnRole<ID, SERVICE>> {

    private IModelProvider<SERVICE> serviceProvider;
    private MapperDtoBase<ID, EntityRoleuser<ID>, Roleuser<ID>> mapperRoleuser;

    public MapperDtoServiceOnRole(){
        this.serviceProvider = null;
        this.mapperRoleuser = null;
    }

    public MapperDtoServiceOnRole(IModelProvider<SERVICE> serviceProvider,
                                  MapperDtoBase<ID, EntityRoleuser<ID>, Roleuser<ID>> mapperRoleuser){
        this.serviceProvider = serviceProvider;
        this.mapperRoleuser = mapperRoleuser;
    }

    @Override
    protected EntityServiceOnRole<ID, SERVICE> implementMap(ServiceOnRole<ID, SERVICE> dto) {
        EntityServiceOnRole<ID, SERVICE> entity = new EntityServiceOnRole();
        entity.setId(dto.getId());
        entity.setRoleId(dto.getRoleId());
        entity.setServiceId(dto.getServiceId());
        Optional.ofNullable(mapperRoleuser).ifPresent((mapper) -> entity.setRole(mapper.map(dto.getRole())));
        entity.setServiceProvider(serviceProvider);
        return entity;
    }

    @Override
    protected ServiceOnRole<ID, SERVICE> implementMap(EntityServiceOnRole<ID, SERVICE> entity) {
        ServiceOnRole<ID, SERVICE> dto = new ServiceOnRole();
        dto.setId(entity.getId());
        dto.setRoleId(entity.getRoleId());
        dto.setServiceId(entity.getServiceId());
        Optional.ofNullable(mapperRoleuser).ifPresent((mapper) -> dto.setRole(mapper.map(entity.getRole())));
        entity.setServiceProvider(serviceProvider);
        return dto;
    }
}
