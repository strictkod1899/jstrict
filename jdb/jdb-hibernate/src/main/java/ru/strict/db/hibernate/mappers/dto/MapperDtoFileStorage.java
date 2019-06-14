package ru.strict.db.hibernate.mappers.dto;

import ru.strict.models.FileStorage;
import ru.strict.models.FileStoragePath;
import ru.strict.db.hibernate.entities.EntityFileStorage;
import ru.strict.db.core.mappers.dto.MapperDtoBase;

/**
 * Двухсторонний маппинг объектов типа EntityFileStorage и DtoFileStorage
 */
public class MapperDtoFileStorage extends MapperDtoBase<Long, EntityFileStorage, FileStorage<Long>> {

    private MapperDtoBase<Long, EntityFileStorage, FileStoragePath<Long>> mapperBase;

    public MapperDtoFileStorage(MapperDtoBase<Long, EntityFileStorage, FileStoragePath<Long>> mapperBase) {
        super();
        this.mapperBase = mapperBase;
    }

    @Override
    protected EntityFileStorage implementMap(FileStorage<Long> dto) {
        EntityFileStorage baseEntity = mapperBase.map(dto);

        EntityFileStorage entity = new EntityFileStorage();
        entity.setId(baseEntity.getId());
        entity.setFilename(baseEntity.getFilename());
        entity.setExtension(baseEntity.getExtension());
        entity.setDisplayName(baseEntity.getDisplayName());
        entity.setCreateDate(baseEntity.getCreateDate());
        entity.setType(baseEntity.getType());
        entity.setStatus(baseEntity.getStatus());
        entity.setFilePath(baseEntity.getFilePath());
        entity.setContent(dto.getContent());
        return entity;
    }

    @Override
    protected FileStorage<Long> implementMap(EntityFileStorage entity) {
        FileStoragePath<Long> baseDto = mapperBase.map(entity);

        FileStorage<Long> dto = new FileStorage();
        dto.setId(baseDto.getId());
        dto.setFilename(baseDto.getFilename());
        entity.setExtension(baseDto.getExtension());
        entity.setDisplayName(baseDto.getDisplayName());
        dto.setCreateDate(baseDto.getCreateDate());
        dto.setType(baseDto.getType());
        dto.setStatus(baseDto.getStatus());
        dto.setFilePath(baseDto.getFilePath());
        dto.setContent(entity.getContent());
        return dto;
    }
}
