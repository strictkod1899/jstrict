package ru.strict.db.hibernate.mappers.dto;

import ru.strict.db.core.dto.DtoRoleuser;
import ru.strict.db.core.dto.DtoServiceOnRole;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.core.models.IModelProvider;
import ru.strict.db.hibernate.entities.EntityRoleuser;
import ru.strict.db.hibernate.entities.EntityServiceOnRole;

import java.util.Optional;

/**
 * Двухсторонний маппинг объектов типа EntityServiceOnRole и DtoServiceOnRole
 */
public class MapperDtoServiceOnRole<SERVICE> extends MapperDtoBase<Long, EntityServiceOnRole<SERVICE>, DtoServiceOnRole<Long, SERVICE>> {

    private IModelProvider<SERVICE> serviceProvider;
    private MapperDtoBase<Long, EntityRoleuser, DtoRoleuser<Long>> mapperRoleuser;

    public MapperDtoServiceOnRole(){
        this.serviceProvider = null;
        this.mapperRoleuser = null;
    }

    public MapperDtoServiceOnRole(IModelProvider<SERVICE> serviceProvider,
                                  MapperDtoBase<Long, EntityRoleuser, DtoRoleuser<Long>> mapperRoleuser){
        this.serviceProvider = serviceProvider;
        this.mapperRoleuser = mapperRoleuser;
    }

    @Override
    protected EntityServiceOnRole<SERVICE> implementMap(DtoServiceOnRole<Long, SERVICE> dto) {
        EntityServiceOnRole<SERVICE> entity = new EntityServiceOnRole();
        entity.setId(dto.getId());
        entity.setRoleId(dto.getRoleId());
        entity.setServiceId(dto.getServiceId());
        Optional.ofNullable(mapperRoleuser).ifPresent((mapper) -> entity.setRole(mapper.map(dto.getRole())));
        entity.setServiceProvider(serviceProvider);
        return entity;
    }

    @Override
    protected DtoServiceOnRole<Long, SERVICE> implementMap(EntityServiceOnRole<SERVICE> entity) {
        DtoServiceOnRole<Long, SERVICE> dto = new DtoServiceOnRole();
        dto.setId(entity.getId());
        dto.setRoleId(entity.getRoleId());
        dto.setServiceId(entity.getServiceId());
        Optional.ofNullable(mapperRoleuser).ifPresent((mapper) -> dto.setRole(mapper.map(entity.getRole())));
        entity.setServiceProvider(serviceProvider);
        return dto;
    }
}
