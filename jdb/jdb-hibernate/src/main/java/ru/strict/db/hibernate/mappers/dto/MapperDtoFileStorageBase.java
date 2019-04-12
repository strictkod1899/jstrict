package ru.strict.db.hibernate.mappers.dto;

import ru.strict.db.core.dto.DtoFileStorageBase;
import ru.strict.db.hibernate.entities.EntityFileStorage;
import ru.strict.db.core.mappers.dto.MapperDtoBase;

/**
 * Двухсторонний маппинг объектов типа EntityFileStorage и DtoFileStorageBase
 */
public class MapperDtoFileStorageBase extends MapperDtoBase<Long, EntityFileStorage, DtoFileStorageBase<Long>> {

    public MapperDtoFileStorageBase(){}

    @Override
    protected EntityFileStorage implementMap(DtoFileStorageBase<Long> dto) {
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
    protected DtoFileStorageBase<Long> implementMap(EntityFileStorage entity) {
        DtoFileStorageBase<Long> dto = new DtoFileStorageBase();
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
