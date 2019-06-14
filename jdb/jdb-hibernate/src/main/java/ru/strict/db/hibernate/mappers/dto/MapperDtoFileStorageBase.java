package ru.strict.db.hibernate.mappers.dto;

import ru.strict.models.FileStorageBase;
import ru.strict.db.hibernate.entities.EntityFileStorage;
import ru.strict.db.core.mappers.dto.MapperDtoBase;

/**
 * Двухсторонний маппинг объектов типа EntityFileStorage и DtoFileStorageBase
 */
public class MapperDtoFileStorageBase extends MapperDtoBase<Long, EntityFileStorage, FileStorageBase<Long>> {

    public MapperDtoFileStorageBase(){}

    @Override
    protected EntityFileStorage implementMap(FileStorageBase<Long> dto) {
        EntityFileStorage entity = new EntityFileStorage();
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
    protected FileStorageBase<Long> implementMap(EntityFileStorage entity) {
        FileStorageBase<Long> dto = new FileStorageBase();
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
