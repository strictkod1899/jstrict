package ru.strict.db.core.mappers.dto;

import ru.strict.models.FileStorageBase;
import ru.strict.db.core.entities.EntityFileStorage;

/**
 * Двухсторонний маппинг объектов типа EntityFileStorage и DtoFileStorageBase
 */
public class MapperDtoFileStorageBase<ID> extends MapperDtoBase<ID, EntityFileStorage<ID>, FileStorageBase<ID>> {

    public MapperDtoFileStorageBase(){}

    @Override
    protected EntityFileStorage<ID> implementMap(FileStorageBase<ID> dto) {
        EntityFileStorage<ID> entity = new EntityFileStorage();
        entity.setId(dto.getId());
        entity.setFilename(dto.getFilename());
        entity.setExtension(dto.getExtension());
        entity.setDisplayName(dto.getDisplayName());
        entity.setCreateDate(dto.getCreateDate());
        entity.setType(dto.getType());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    @Override
    protected FileStorageBase<ID> implementMap(EntityFileStorage<ID> entity) {
        FileStorageBase<ID> dto = new FileStorageBase();
        dto.setId(entity.getId());
        dto.setFilename(entity.getFilename());
        dto.setExtension(entity.getExtension());
        dto.setDisplayName(entity.getDisplayName());
        dto.setCreateDate(entity.getCreateDate());
        dto.setType(entity.getType());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
