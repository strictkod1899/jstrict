package ru.strict.db.hibernate.mappers.dto;

import ru.strict.models.FileStorageBase;
import ru.strict.models.FileStoragePath;
import ru.strict.db.core.mappers.dto.MapperDtoBase;
import ru.strict.db.hibernate.entities.EntityFileStorage;

/**
 * Двухсторонний маппинг объектов типа EntityFileStorage и DtoFileStoragePath
 */
public class MapperDtoFileStoragePath extends MapperDtoBase<Long, EntityFileStorage, FileStoragePath<Long>> {

    private MapperDtoBase<Long, EntityFileStorage, FileStorageBase<Long>> mapperBase;

    public MapperDtoFileStoragePath(MapperDtoBase<Long, EntityFileStorage, FileStorageBase<Long>> mapperBase) {
        super();
        this.mapperBase = mapperBase;
    }

    @Override
    protected EntityFileStorage implementMap(FileStoragePath<Long> dto) {
        EntityFileStorage baseEntity = mapperBase.map(dto);

        EntityFileStorage entity = new EntityFileStorage();
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
    protected FileStoragePath<Long> implementMap(EntityFileStorage entity) {
        FileStorageBase<Long> baseDto = mapperBase.map(entity);

        FileStoragePath<Long> dto = new FileStoragePath();
        dto.setId(baseDto.getId());
        dto.setFilename(baseDto.getFilename());
        entity.setExtension(baseDto.getExtension());
        entity.setDisplayName(baseDto.getDisplayName());
        dto.setCreateDate(baseDto.getCreateDate());
        dto.setType(baseDto.getType());
        dto.setStatus(baseDto.getStatus());
        dto.setFilePath(entity.getFilePath());
        return dto;
    }
}
