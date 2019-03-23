package ru.strict.db.core.mappers.dto;

import ru.strict.db.core.dto.DtoFileStorageBase;
import ru.strict.db.core.entities.EntityFileStorage;

/**
 * Двухсторонний маппинг объектов типа EntityFileStorage и DtoFileStorageBase
 */
public class MapperDtoFileStorageBase<ID> extends MapperDtoBase<ID, EntityFileStorage<ID>, DtoFileStorageBase<ID>> {

    public MapperDtoFileStorageBase(){}

    @Override
    protected EntityFileStorage<ID> implementMap(DtoFileStorageBase<ID> dto) {
        EntityFileStorage<ID> entity = new EntityFileStorage();
        entity.setId(dto.getId());
        entity.setFilename(dto.getFilename());
        entity.setCreateDate(dto.getCreateDate());
        entity.setType(dto.getType());
        return entity;
    }

    @Override
    protected DtoFileStorageBase<ID> implementMap(EntityFileStorage<ID> entity) {
        DtoFileStorageBase<ID> dto = new DtoFileStorageBase();
        dto.setId(entity.getId());
        dto.setFilename(entity.getFilename());
        dto.setCreateDate(entity.getCreateDate());
        dto.setType(entity.getType());
        return dto;
    }
}
