package ru.strict.db.core.mappers.dto;

import ru.strict.models.FileStorageBase;
import ru.strict.models.FileStoragePath;
import ru.strict.db.core.entities.EntityFileStorage;

/**
 * Двухсторонний маппинг объектов типа EntityFileStorage и DtoFileStoragePath
 */
public class MapperDtoFileStoragePath<ID> extends MapperDtoBase<ID, EntityFileStorage<ID>, FileStoragePath<ID>> {

    private MapperDtoBase<ID, EntityFileStorage<ID>, FileStorageBase<ID>> mapperBase;

    public MapperDtoFileStoragePath(MapperDtoBase<ID, EntityFileStorage<ID>, FileStorageBase<ID>> mapperBase) {
        super();
        this.mapperBase = mapperBase;
    }

    @Override
    protected EntityFileStorage<ID> implementMap(FileStoragePath<ID> dto) {
        EntityFileStorage<ID> baseEntity = mapperBase.map(dto);

        EntityFileStorage<ID> entity = new EntityFileStorage();
        entity.setId(baseEntity.getId());
        entity.setFilename(baseEntity.getFilename());
        entity.setExtension(baseEntity.getExtension());
        entity.setDisplayName(baseEntity.getDisplayName());
        entity.setCreateDate(baseEntity.getCreateDate());
        entity.setType(baseEntity.getType());
        entity.setStatus(baseEntity.getStatus());
        entity.setFilePath(dto.getFilePath());
        return entity;
    }

    @Override
    protected FileStoragePath<ID> implementMap(EntityFileStorage<ID> entity) {
        FileStorageBase<ID> baseDto = mapperBase.map(entity);

        FileStoragePath<ID> dto = new FileStoragePath();
        dto.setId(baseDto.getId());
        dto.setFilename(baseDto.getFilename());
        dto.setExtension(baseDto.getExtension());
        dto.setDisplayName(baseDto.getDisplayName());
        dto.setCreateDate(baseDto.getCreateDate());
        dto.setType(baseDto.getType());
        dto.setStatus(baseDto.getStatus());
        dto.setFilePath(entity.getFilePath());
        return dto;
    }
}
